package alura.com.usuarioflix.controller;

import alura.com.usuarioflix.Dto.UsuarioDto;
import alura.com.usuarioflix.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.ws.rs.HeaderParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastro(@RequestBody @Valid UsuarioDto dto, UriComponentsBuilder builder) {

        UsuarioDto cadastro = usuarioService.cadastro(dto);

        URI uri = builder.path("/usuario/{id}").buildAndExpand(cadastro.getId()).toUri();
        return ResponseEntity.created(uri).body(cadastro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualiza(@RequestBody @Valid UsuarioDto dto, @PathVariable Long id) {

        UsuarioDto atualizar = usuarioService.atualizar(dto, id);

        return ResponseEntity.ok(atualizar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> acharPorId(@PathVariable Long id) {

        UsuarioDto usuarioDto = usuarioService.acharPorId(id);

        return ResponseEntity.ok(usuarioDto);
    }

    @GetMapping
    public Page<UsuarioDto> paginacao(Pageable pageable) {

        return usuarioService.paginacao(pageable);
    }


    @PatchMapping("/{id}/confirmado")
    @CircuitBreaker(name = "atualizaVideo", fallbackMethod = "videoAssistidoComAutorizacaoPendente")
    public void confirmarVideo(@PathVariable Long id) {

        usuarioService.confirmarVideo(id);
    }

    //fallBackMethod (plano B) precisa ter a mesma assinatura do metodo confirmarVideo, pode se adicionar uma exception apenas no segundo parametro
    public void videoAssistidoComAutorizacaoPendente(Long id, Exception e) {
        usuarioService.alteraStatus(id);
    }


}

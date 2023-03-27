package alura.com.videoscategorias.Controller.controllers;

import alura.com.videoscategorias.Controller.Service.implement.CategoriasCategoriaServiceImpl;
import alura.com.videoscategorias.Controller.dto.CategoriasDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasCategoriaServiceImpl service;


    @PostMapping
    public ResponseEntity<CategoriasDto> cadastro(@RequestBody @Valid CategoriasDto dto, UriComponentsBuilder builder) {


        CategoriasDto cadastro = service.cadastro(dto);

        URI uri = builder.path("/categorias/{id}").buildAndExpand(cadastro.getId()).toUri();
        return ResponseEntity.created(uri).body(cadastro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriasDto> atualiza(@PathVariable Long id, @RequestBody @Valid CategoriasDto dto) {

        CategoriasDto categoriasDto = service.atualiza(id, dto);

        return ResponseEntity.ok(categoriasDto);
    }

    @GetMapping
    public Page<CategoriasDto> paginacao(Pageable pageable) {

        return service.paginacao(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDto> getById(@PathVariable Long id) {

        CategoriasDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){

        service.delete(id);
      return ResponseEntity.noContent().build();
    }

}






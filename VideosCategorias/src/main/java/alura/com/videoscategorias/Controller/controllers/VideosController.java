package alura.com.videoscategorias.Controller.controllers;

import alura.com.videoscategorias.Controller.Service.implement.VideosServiceImpl;
import alura.com.videoscategorias.Controller.dto.StatusDto;
import alura.com.videoscategorias.Controller.dto.VideoDto;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosServiceImpl service;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<VideoDto> cadastrarVideo(@RequestBody @Valid VideoDto dto, UriComponentsBuilder builder) {

        VideoDto detalhesVideosDto = service.cadastrarVideo(dto);

        URI uri = builder.path("/videos/{id}").buildAndExpand(detalhesVideosDto.getId()).toUri();


        rabbitTemplate.convertAndSend("videosCategorias.ex","",detalhesVideosDto);
        return ResponseEntity.created(uri).body(detalhesVideosDto);

    }

    @PutMapping("/{id}/assistido")
    public void atualizaStatus(@PathVariable Long id) {

        service.atualizaStatus(id);
    }

    @GetMapping("/porta")
    public String retornaPorta(@Value("${local.server.port}") String porta) {


        return String.format("Deu bom porta " + porta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> atualizarVideo(@PathVariable Long id,
                                                   @RequestBody @Valid VideoDto dto) {

        VideoDto detalhesVideosDto = service.atualizarVideo(dto, id);
        return ResponseEntity.ok(detalhesVideosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getById(@PathVariable Long id) {

        VideoDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public Page<VideoDto> getAll(@RequestParam(required = false)
                                 String titulo, @PageableDefault(sort = "titulo",
            direction = Sort.Direction.ASC) Pageable pageable) {

        return service.findAll(titulo, pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VideoDto> deleteById(@PathVariable Long id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

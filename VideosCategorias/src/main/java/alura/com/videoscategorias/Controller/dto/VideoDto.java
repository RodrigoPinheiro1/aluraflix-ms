package alura.com.videoscategorias.Controller.dto;

import alura.com.videoscategorias.Controller.entities.StatusVideo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VideoDto {


    private Long id;

    @NotNull
    @NotBlank
    private String titulo;
    @NotNull
    @NotBlank
    private String descricao;
    @NotNull
    @NotBlank
    private String url;

    private StatusVideo statusVideo;

    @NotNull
    private Long categoriasId;
    private Boolean enable;
}

package alura.com.videoscategorias.Controller.dto;

import alura.com.videoscategorias.Controller.entities.StatusVideo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public VideoDto(String titulo, String descricao, String url, StatusVideo statusVideo, Long categoriasId, Boolean enable) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.statusVideo = statusVideo;
        this.categoriasId = categoriasId;
        this.enable = enable;
    }
}

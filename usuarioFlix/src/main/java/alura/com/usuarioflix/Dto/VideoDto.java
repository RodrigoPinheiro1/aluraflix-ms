package alura.com.usuarioflix.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VideoDto {

    private Long id;

    private String titulo;
    private String descricao;
    private String url;

    private StatusVideo statusVideo;

    private Long categoriasId;
    private Boolean enable;

}

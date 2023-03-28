package alura.com.usuarioflix.Dto;

import alura.com.usuarioflix.model.Permissao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDto {

    private Long id;
    @NotBlank
    @NotNull
    private String nome;
    private Permissao permissao;
    @NotNull

    private LocalDate dataNascimento;
    @NotNull
    private Long videoId;
}

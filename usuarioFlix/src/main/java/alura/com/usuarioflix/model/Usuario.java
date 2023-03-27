package alura.com.usuarioflix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class Usuario extends Pessoa{

    @Enumerated(EnumType.STRING)
    private Permissao permissao;

    private Long videoId;


}

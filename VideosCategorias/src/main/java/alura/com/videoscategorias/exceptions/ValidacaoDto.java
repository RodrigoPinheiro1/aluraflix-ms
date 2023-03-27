package alura.com.videoscategorias.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ValidacaoDto {


    private String campo;
    private String erro;


}

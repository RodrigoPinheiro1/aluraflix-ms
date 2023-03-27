package alura.com.videoscategorias.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MessageCustomGlobalException {
    private LocalDateTime data;
    private Integer status;
    private String message;

    private List<ValidacaoDto> erros = new ArrayList<>();

    public MessageCustomGlobalException(LocalDateTime data, Integer status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }
}

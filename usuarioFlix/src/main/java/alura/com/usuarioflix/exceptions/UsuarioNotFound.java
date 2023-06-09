package alura.com.usuarioflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class UsuarioNotFound extends RuntimeException {


    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsuarioNotFound.class)
    public ResponseEntity<MessageGlobalException> usuarioNotFound() {

        MessageGlobalException messageGlobalException = new MessageGlobalException(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "usuario nao existe");

        return new ResponseEntity<>(messageGlobalException, HttpStatus.NOT_FOUND);
    }


}

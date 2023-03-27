package alura.com.videoscategorias.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductNotFoundException  extends RuntimeException{

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<MessageCustomGlobalException> productNotFound (ProductNotFoundException  productNotFoundException){

        MessageCustomGlobalException exception = new MessageCustomGlobalException(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),"produto não encontrado");

        return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);

    }

}

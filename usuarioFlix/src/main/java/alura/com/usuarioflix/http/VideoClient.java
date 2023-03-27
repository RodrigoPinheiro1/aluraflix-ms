package alura.com.usuarioflix.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("videos-ms")
public interface VideoClient {


    @RequestMapping(method = RequestMethod.PUT, value = "/videos/{id}/assistido")
     void atualizaVideo(@PathVariable Long id);

}

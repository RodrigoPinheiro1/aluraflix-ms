package alura.com.usuarioflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UsuarioFlixApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioFlixApplication.class, args);
    }

}

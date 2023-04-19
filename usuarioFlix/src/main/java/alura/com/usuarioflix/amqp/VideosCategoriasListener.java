package alura.com.usuarioflix.amqp;

import alura.com.usuarioflix.Dto.VideoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class VideosCategoriasListener {


    @RabbitListener(queues = "usuario")
    public void recebeMensagem(VideoDto videoDto) {

        System.out.println(videoDto.toString());

    }



}

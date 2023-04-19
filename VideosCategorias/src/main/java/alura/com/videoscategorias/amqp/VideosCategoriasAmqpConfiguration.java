package alura.com.videoscategorias.amqp;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideosCategoriasAmqpConfiguration {


    @Bean
    public Jackson2JsonMessageConverter messageConverter () {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory connectionFactory) {

        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> iniciaAdmin(RabbitAdmin rabbitAdmin) {

        return event -> rabbitAdmin.initialize();
    }


    @Bean
    public RabbitTemplate rabbitTemplate (ConnectionFactory connectionFactory,Jackson2JsonMessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public FanoutExchange fanoutExchange  () {

        return new FanoutExchange("videosCategorias.ex");
    }





}

package alura.com.usuarioflix.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioAmqpConfiguration {

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
    public RabbitTemplate rabbitTemplate (ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue filaUsuario () {

        return QueueBuilder.nonDurable("usuario").build();
    }


    @Bean
    public FanoutExchange fanoutExchange () {

        return ExchangeBuilder.fanoutExchange("videosCategorias.ex")
                .build();
    }

    @Bean
    public Binding bindingUsuario (FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(filaUsuario()).to(fanoutExchange);
    }



}

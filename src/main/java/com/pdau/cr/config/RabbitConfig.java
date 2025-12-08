package com.pdau.cr.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String DENUNCIA_EXCHANGE = "denuncia.creada.exchange";
    public static final String DENUNCIA_ROUTING_KEY = "denuncia.creada";
    public static final String DENUNCIA_QUEUE = "reporte.denuncia.queue";

    public static final String ESTADO_QUEUE = "reporte.denuncia.estado.queue";
    public static final String ESTADO_EXCHANGE = "denuncia.estado.exchange";
    public static final String ESTADO_ROUTING_KEY = "denuncia.estado.actualizado";

    @Bean
    public Queue denunciaQueue() {
        return new Queue(DENUNCIA_QUEUE, true);
    }

    @Bean
    public TopicExchange denunciaExchange() {
        return new TopicExchange(DENUNCIA_EXCHANGE);
    }

    @Bean
    public Binding denunciaBinding() {
        return BindingBuilder.bind(denunciaQueue())
                .to(denunciaExchange())
                .with(DENUNCIA_ROUTING_KEY);
    }

    @Bean
    public Queue estadoQueue() {
        return new Queue(ESTADO_QUEUE, true);
    }

    @Bean
    public TopicExchange estadoExchange() {
        return new TopicExchange(ESTADO_EXCHANGE);
    }

    @Bean
    public Binding estadoBinding() {
        return BindingBuilder.bind(estadoQueue())
                .to(estadoExchange())
                .with(ESTADO_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
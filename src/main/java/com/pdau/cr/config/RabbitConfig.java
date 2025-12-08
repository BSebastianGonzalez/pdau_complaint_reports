package com.pdau.cr.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String DENUNCIA_EXCHANGE = "denuncia.creada.exchange";
    public static final String DENUNCIA_ROUTING_KEY = "denuncia.creada";
    public static final String DENUNCIA_QUEUE = "reporte.denuncia.queue";

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
    public JacksonJsonMessageConverter jackson2JsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

}
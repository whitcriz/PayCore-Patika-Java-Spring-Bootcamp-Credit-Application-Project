package com.paycore.patika.credit_application_system.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "apply_queue";
    public static final String EXCHANGE = "apply_exchange";
    public static final String ROUTING_KEY = "apply_routingKey";

    public static final String QUEUE_SCORE = "result_queue";
    public static final String EXCHANGE_SCORE = "result_exchange";
    public static final String ROUTING_KEY_SCORE = "result_routingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Queue queue_score() {
        return new Queue(QUEUE_SCORE);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }


    @Bean
    public DirectExchange exchange_score() { return new DirectExchange(EXCHANGE_SCORE);}


    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding binding_score(Queue queue_score, DirectExchange exchange_score) {
        return BindingBuilder.bind(queue_score).to(exchange_score).with(ROUTING_KEY_SCORE);
    }


    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}

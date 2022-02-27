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

    public static final String QUEUE = "customer_queue";
    public static final String EXCHANGE = "customer_exchange";
    public static final String ROUTING_KEY = "customer_routingKey";

    public static final String QUEUE_SCORE = "score_queue";
    public static final String EXCHANGE_SCORE = "score_exchange";
    public static final String ROUTING_KEY_SCORE = "score_routingKey";



    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Queue queue_score() {
        return new Queue(QUEUE_SCORE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }


    @Bean
    public TopicExchange exchange_score() { return new TopicExchange(EXCHANGE_SCORE);}


    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding binding_score(Queue queue_score, TopicExchange exchange_score) {
        return BindingBuilder.bind(queue_score).to(exchange_score).with(ROUTING_KEY);
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

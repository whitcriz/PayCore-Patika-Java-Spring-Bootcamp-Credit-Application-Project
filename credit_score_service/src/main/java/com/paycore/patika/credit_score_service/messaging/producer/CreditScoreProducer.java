package com.paycore.patika.credit_score_service.messaging.producer;

import com.paycore.patika.credit_score_service.config.RabbitMQConfig;
import com.paycore.patika.credit_score_service.model.entity.Customer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/messaging/publish")
public class CreditScoreProducer {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/credit-score")
    public String publishCreditScore(Customer customer) {
        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, customer);
        return "Success";
    }

}
package com.service.servicecoupon.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {


    @Value("${rabbit.login.exchange.name}")
    private String signupCouponExchangeName;

    @Value("${rabbit.login.queue.name}")
    private String signupCouponQueueName;

    @Value("${rabbit.login.routing.key}")
    private String signupCouponRoutingKey;


    @Value("${rabbit.login.queue.dlq.name}")
    private String signupCouponDlqQueueName;

    @Value("${rabbit.login.routing.dlq.key}")
    private String signupCouponDlqRoutingKey;
    // DLX 설정

    // DLQ 설정
    @Bean
    Queue dlqQueue() {
        return QueueBuilder.durable(signupCouponDlqQueueName).build();
    }

    @Bean
    DirectExchange signupCouponExchange() {
        return new DirectExchange(signupCouponExchangeName);
    }

    @Bean
    Binding dlqBinding() {
        return BindingBuilder.bind(dlqQueue()).to(signupCouponExchange())
            .with(signupCouponDlqRoutingKey);
    }

    @Bean
    Queue signupCouponQueue() {
        return QueueBuilder.durable(signupCouponQueueName)
            .withArgument("x-dead-letter-exchange", signupCouponExchangeName)
            .withArgument("x-dead-letter-routing-key", signupCouponDlqRoutingKey)
            .build();
    }

    @Bean
    Binding signupCouponBinding() {
        return BindingBuilder.bind(signupCouponQueue()).to(signupCouponExchange())
            .with(signupCouponRoutingKey);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}

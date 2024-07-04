//package com.service.servicecoupon.config;
//
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class RabbitConfig {
//
//    @Value("${rabbit.login.exchange.name}")
//    private String signupCouponExchangeName;
//    @Value("${rabbit.login.queue.name}")
//    private String signupCouponQueueName;
//    @Value("${rabbit.login.routing.key}")
//    private String signupCouponRoutingKey;
//
//    @Bean
//    DirectExchange signupCouponExchange() {
//        return new DirectExchange(signupCouponExchangeName);
//    }
//
//    @Bean
//    Queue signupCouponQueue() {
//        return new Queue(signupCouponQueueName);
//    }
//
//    @Bean
//    Binding signupCouponBinding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(signupCouponRoutingKey);
//    }
//
//    @Bean
//    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        return rabbitTemplate;
//    }
//
//    @Bean
//    ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        return objectMapper;
//    }
//}

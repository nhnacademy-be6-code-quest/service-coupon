package com.service.servicecoupon.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
    private final String rabbitHost;
    private final int rabbitPort;
    private final String rabbitUsername;
    private final String rabbitPassword;


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

    @Value("${rabbit.use.coupon.exchange.name}")
    private String useCouponExchangeName;

    @Value("${rabbit.use.coupon.queue.name}")
    private String useCouponQueueName;

    @Value("${rabbit.use.coupon.routing.key}")
    private String useCouponRoutingKey;

    @Value("${rabbit.use.coupon.dlq.queue.name}")
    private String useCouponDlqQueueName;

    @Value("${rabbit.use.coupon.dlq.routing.key}")
    private String useCouponDlqRoutingKey;

    @Value("${rabbit.refund.coupon.exchange.name}")
    private String refundCouponExchangeName;

    @Value("${rabbit.refund.coupon.queue.name}")
    private String refundCouponQueueName;

    @Value("${rabbit.refund.coupon.routing.key}")
    private String refundCouponRoutingKey;

    @Value("${rabbit.refund.coupon.dlq.queue.name}")
    private String refundCouponDlqQueueName;

    @Value("${rabbit.refund.coupon.dlq.routing.key}")
    private String refundCouponDlqRoutingKey;

    private static final String DLQ_EXCHANGE = "x-dead-letter-exchange";
    private static final String DLQ_ROUTING = "x-dead-letter-routing-key";
    @Bean
    Queue refundCouponQueue(){
        return QueueBuilder.durable(refundCouponQueueName)
            .withArgument(DLQ_EXCHANGE, refundCouponExchangeName)
            .withArgument(DLQ_ROUTING, refundCouponDlqRoutingKey)
            .build();
    }
    @Bean
    DirectExchange refundCouponExchange(){
        return new DirectExchange(refundCouponExchangeName);
    }
    @Bean
    Binding refundCouponBinding(){
        return BindingBuilder.bind(refundCouponQueue()).to(refundCouponExchange()).with(refundCouponRoutingKey);
    }
    @Bean
    Queue dlqRefundCouponQueue(){
        return QueueBuilder.durable(refundCouponDlqQueueName).build();
    }
    @Bean
    Binding refundCouponDlqBinding(){
        return BindingBuilder.bind(dlqRefundCouponQueue()).to(refundCouponExchange()).with(refundCouponDlqRoutingKey);
    }


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
            .withArgument(DLQ_EXCHANGE, signupCouponExchangeName)
            .withArgument(DLQ_ROUTING, signupCouponDlqRoutingKey)
            .build();
    }

    @Bean
    Binding signupCouponBinding() {
        return BindingBuilder.bind(signupCouponQueue()).to(signupCouponExchange())
            .with(signupCouponRoutingKey);
    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitHost, rabbitPort);
        connectionFactory.setUsername(rabbitUsername);
        connectionFactory.setPassword(rabbitPassword);
        return connectionFactory;
    }
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    DirectExchange useCouponExchange() {
        return new DirectExchange(useCouponExchangeName);
    }

    @Bean
    Queue useCouponQueue() {
        return QueueBuilder.durable(useCouponQueueName)
            .withArgument(DLQ_EXCHANGE, useCouponExchangeName)
            .withArgument(DLQ_ROUTING, useCouponDlqRoutingKey).build();
    }

    @Bean
    Queue useCouponDlqQueue() {
        return QueueBuilder.durable(useCouponDlqQueueName).build();
    }

    @Bean
    Binding useCouponBinding() {
        return BindingBuilder.bind(useCouponQueue()).to(useCouponExchange())
            .with(useCouponRoutingKey);
    }

    @Bean
    Binding useCouponDlqBinding() {
        return BindingBuilder.bind(useCouponDlqQueue()).to(useCouponExchange())
            .with(useCouponDlqRoutingKey);
    }

}

package site.donghyeon.bank.infrastructure.messaging.rabbitmq.deposit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepositRabbitMQConfig {

    public static final String DEPOSIT_EXCHANGE = "deposit.exchange";
    public static final String DEPOSIT_QUEUE = "deposit.queue";
    public static final String DEPOSIT_ROUTING_KEY = "account";

    @Bean
    public TopicExchange depositExchange() {
        return new TopicExchange(DEPOSIT_EXCHANGE);
    }

    @Bean
    public Queue depositQueue() {
        return QueueBuilder.durable(DEPOSIT_QUEUE).build();
    }

    @Bean
    public Binding depositBinding() {
        return BindingBuilder
                .bind(depositQueue())
                .to(depositExchange())
                .with("%s.*".formatted(DEPOSIT_ROUTING_KEY));
    }
}

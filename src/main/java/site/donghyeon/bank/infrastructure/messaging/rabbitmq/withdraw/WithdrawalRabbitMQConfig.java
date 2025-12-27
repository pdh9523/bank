package site.donghyeon.bank.infrastructure.messaging.rabbitmq.withdraw;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WithdrawalRabbitMQConfig {

    public static final String WITHDRAWAL_EXCHANGE = "withdrawal.exchange";
    public static final String WITHDRAWAL_QUEUE = "withdrawal.queue";
    public static final String WITHDRAWAL_ROUTING_KEY = "account";

    @Bean
    public TopicExchange withdrawalExchange() {
        return new TopicExchange(WITHDRAWAL_EXCHANGE);
    }

    @Bean
    public Queue withdrawalQueue() {
        return QueueBuilder.durable(WITHDRAWAL_QUEUE).build();
    }

    @Bean
    public Binding withdrawalBinding() {
        return BindingBuilder
                .bind(withdrawalQueue())
                .to(withdrawalExchange())
                .with("%s.*".formatted(WITHDRAWAL_ROUTING_KEY));
    }
}

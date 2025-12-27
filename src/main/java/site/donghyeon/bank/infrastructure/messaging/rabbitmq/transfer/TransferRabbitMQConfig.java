package site.donghyeon.bank.infrastructure.messaging.rabbitmq.transfer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferRabbitMQConfig {
    public static final String TRANSFER_EXCHANGE = "transfer.exchange";
    public static final String TRANSFER_QUEUE = "transfer.queue";
    public static final String TRANSFER_ROUTING_KEY = "account";

    @Bean
    public TopicExchange transferExchange() {
        return new TopicExchange(TRANSFER_EXCHANGE);
    }

    @Bean
    public Queue transferQueue() {
        return QueueBuilder.durable(TRANSFER_QUEUE).build();
    }

    @Bean
    public Binding transferBinding() {
        return BindingBuilder
                .bind(transferQueue())
                .to(transferExchange())
                .with("%s.*".formatted(TRANSFER_ROUTING_KEY));
    }
}

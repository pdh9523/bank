package site.donghyeon.bank.infrastructure.messaging.rabbitmq.deposit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.donghyeon.bank.application.account.executor.DepositExecutor;
import site.donghyeon.bank.application.account.task.DepositTask;

@Component
public class DepositCommandListener {

    private final DepositExecutor depositExecutor;

    public DepositCommandListener(DepositExecutor depositExecutor) {
        this.depositExecutor = depositExecutor;
    }

    @RabbitListener(queues = DepositRabbitMQConfig.DEPOSIT_QUEUE)
    public void handle(DepositCommandMessage msg) {
        System.out.println("handler");
        depositExecutor.execute(DepositTask.from(msg));
    }
}

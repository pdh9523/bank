package site.donghyeon.bank.infrastructure.messaging.rabbitmq.transfer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.donghyeon.bank.application.account.executor.TransferExecutor;
import site.donghyeon.bank.application.account.task.TransferTask;

@Component
public class TransferListener {

    private TransferExecutor transferExecutor;

    public TransferListener (TransferExecutor transferExecutor) {
        this.transferExecutor = transferExecutor;
    }

    @RabbitListener(queues = TransferRabbitMQConfig.TRANSFER_QUEUE)
    public void handle(TransferMessage msg) {
        transferExecutor.execute(TransferTask.from(msg));
    }
}

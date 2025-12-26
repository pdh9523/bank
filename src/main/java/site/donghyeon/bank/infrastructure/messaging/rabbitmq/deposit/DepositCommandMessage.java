package site.donghyeon.bank.infrastructure.messaging.rabbitmq.deposit;

import site.donghyeon.bank.application.account.task.DepositTask;

import java.util.UUID;

public record DepositCommandMessage(
        UUID txId,
        UUID toAccountId,
        long amount
) {
    public static DepositCommandMessage from(DepositTask task) {
        return new DepositCommandMessage(
                task.txId(),
                task.toAccountId(),
                task.amount().amount()
        );
    }
}

package site.donghyeon.bank.application.account.task;

import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.infrastructure.messaging.rabbitmq.withdraw.WithdrawalMessage;

import java.util.UUID;

public record WithdrawalTask(
        UUID txId,
        UUID fromAccountId,
        Money amount
) {
    public static WithdrawalTask from(WithdrawalMessage msg) {
        return new WithdrawalTask(
                msg.txId(),
                msg.fromAccountId(),
                new Money(msg.amount())
        );
    }
}

package site.donghyeon.bank.infrastructure.messaging.rabbitmq.withdraw;

import java.util.UUID;

public record WithdrawalMessage(
        UUID txId,
        UUID fromAccountId,
        long amount
) {

}

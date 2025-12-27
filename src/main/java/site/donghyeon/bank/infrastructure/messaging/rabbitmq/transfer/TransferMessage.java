package site.donghyeon.bank.infrastructure.messaging.rabbitmq.transfer;

import java.util.UUID;

public record TransferMessage(
        UUID txId,
        UUID fromAccountId,
        UUID toAccountId,
        long amount
) {
}

package site.donghyeon.bank.application.account.transaction.query;

import java.util.UUID;

public record TransactionEventQuery(
        UUID userId,
        UUID accountId,
        UUID eventId
) {
}

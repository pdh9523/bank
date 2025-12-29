package site.donghyeon.bank.presentation.account.request;

import site.donghyeon.bank.application.account.transaction.query.TransactionEventQuery;

import java.util.UUID;

public record TransactionEventRequest(
        UUID userId,
        UUID accountId,
        UUID eventId
) {
    public static TransactionEventRequest of(UUID userId, UUID accountId, UUID eventId) {
        return new TransactionEventRequest(userId, accountId, eventId);
    }

    public TransactionEventQuery toQuery() {
        return new TransactionEventQuery(userId, accountId, eventId);
    }
}

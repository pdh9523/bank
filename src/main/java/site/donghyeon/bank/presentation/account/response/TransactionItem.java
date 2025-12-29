package site.donghyeon.bank.presentation.account.response;

import site.donghyeon.bank.application.account.transaction.view.TransactionView;
import site.donghyeon.bank.domain.accountTransaction.enums.TransactionType;

import java.time.Instant;
import java.util.UUID;

public record TransactionItem(
        UUID eventId,
        Instant createdAt,
        TransactionType type,
        long amount,
        long balanceAfter
) {
    public static TransactionItem from(TransactionView view) {
        return new TransactionItem(
                view.eventId(),
                view.createdAt(),
                view.type(),
                view.amount().amount(),
                view.balanceAfter().amount()
        );
    }
}
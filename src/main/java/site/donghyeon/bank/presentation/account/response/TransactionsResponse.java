package site.donghyeon.bank.presentation.account.response;

import site.donghyeon.bank.application.account.transaction.result.TransactionsResult;
import site.donghyeon.bank.application.account.transaction.view.TransactionView;
import site.donghyeon.bank.domain.accountTransaction.enums.TransactionType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record TransactionsResponse(
        List<TransactionItem> accountTransactions,
        int page,
        int size,
        long total
) {

    public static TransactionsResponse from(TransactionsResult result) {
        return new TransactionsResponse(
                result.accountTransactions().stream()
                        .map(TransactionItem::from)
                        .toList(),
                result.page(),
                result.size(),
                result.total()
        );
    }
}

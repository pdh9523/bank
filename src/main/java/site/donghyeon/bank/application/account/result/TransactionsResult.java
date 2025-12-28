package site.donghyeon.bank.application.account.result;

import site.donghyeon.bank.application.account.view.TransactionsView;

import java.util.List;

public record TransactionsResult(
        List<TransactionsView> accountTransactions,
        int page,
        int size,
        long total
) {
}

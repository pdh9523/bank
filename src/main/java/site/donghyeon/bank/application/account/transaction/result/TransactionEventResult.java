package site.donghyeon.bank.application.account.transaction.result;

import site.donghyeon.bank.application.account.transaction.view.TransactionView;

import java.util.List;

public record TransactionEventResult(
    List<TransactionView> accountTransactions
) {
}

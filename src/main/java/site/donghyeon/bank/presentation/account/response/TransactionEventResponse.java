package site.donghyeon.bank.presentation.account.response;

import site.donghyeon.bank.application.account.transaction.result.TransactionEventResult;

import java.util.List;

public record TransactionEventResponse(
        List<TransactionItem> accountTransactions
) {

    public static TransactionEventResponse from(TransactionEventResult result) {
        return new TransactionEventResponse(
            result.accountTransactions().stream()
                    .map(TransactionItem::from)
                    .toList()
        );
    }
}

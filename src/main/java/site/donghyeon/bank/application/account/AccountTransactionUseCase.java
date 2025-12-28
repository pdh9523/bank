package site.donghyeon.bank.application.account;

import site.donghyeon.bank.application.account.query.TransactionsQuery;
import site.donghyeon.bank.application.account.result.TransactionsResult;

public interface AccountTransactionUseCase {
    TransactionsResult getTransactions(TransactionsQuery query);
}

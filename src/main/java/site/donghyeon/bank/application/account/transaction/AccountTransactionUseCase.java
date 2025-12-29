package site.donghyeon.bank.application.account.transaction;

import site.donghyeon.bank.application.account.transaction.query.AccountLimitQuery;
import site.donghyeon.bank.application.account.transaction.query.TransactionEventQuery;
import site.donghyeon.bank.application.account.transaction.query.TransactionsQuery;
import site.donghyeon.bank.application.account.transaction.result.AccountLimitResult;
import site.donghyeon.bank.application.account.transaction.result.TransactionEventResult;
import site.donghyeon.bank.application.account.transaction.result.TransactionsResult;

public interface AccountTransactionUseCase {
    TransactionsResult getTransactions(TransactionsQuery query);
    AccountLimitResult getAccountLimit(AccountLimitQuery query);
    TransactionEventResult getTransactionEvent(TransactionEventQuery query);
}

package site.donghyeon.bank.application.account;

import site.donghyeon.bank.application.account.query.AccountLimitQuery;
import site.donghyeon.bank.application.account.query.TransactionsQuery;
import site.donghyeon.bank.application.account.result.AccountLimitResult;
import site.donghyeon.bank.application.account.result.TransactionsResult;

public interface AccountTransactionUseCase {
    TransactionsResult getTransactions(TransactionsQuery query);
    AccountLimitResult getAccountLimit(AccountLimitQuery query);
}

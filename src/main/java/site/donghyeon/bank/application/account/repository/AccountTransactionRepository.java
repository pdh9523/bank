package site.donghyeon.bank.application.account.repository;

import site.donghyeon.bank.application.account.query.TransactionsQuery;
import site.donghyeon.bank.application.account.result.TransactionsResult;
import site.donghyeon.bank.domain.accountTransaction.AccountTransaction;

import java.util.UUID;

public interface AccountTransactionRepository {
    TransactionsResult findByAccountId(TransactionsQuery query);
    AccountTransaction save(AccountTransaction tx);
    boolean existsByEventId(UUID eventId);
}

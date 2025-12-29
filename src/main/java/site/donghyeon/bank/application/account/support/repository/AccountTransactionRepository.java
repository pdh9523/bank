package site.donghyeon.bank.application.account.support.repository;

import site.donghyeon.bank.application.account.transaction.query.TransactionsQuery;
import site.donghyeon.bank.application.account.transaction.result.TransactionsResult;
import site.donghyeon.bank.application.account.transaction.view.TransactionView;
import site.donghyeon.bank.domain.accountTransaction.AccountTransaction;

import java.util.List;
import java.util.UUID;

public interface AccountTransactionRepository {
    TransactionsResult findByAccountId(TransactionsQuery query);
    List<TransactionView> findByAccountIdAndEventId(UUID accountId, UUID eventId);
    AccountTransaction save(AccountTransaction tx);
    boolean existsByEventId(UUID eventId);
}

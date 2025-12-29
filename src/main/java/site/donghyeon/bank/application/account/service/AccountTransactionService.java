package site.donghyeon.bank.application.account.service;

import org.springframework.stereotype.Service;
import site.donghyeon.bank.application.account.AccountTransactionUseCase;
import site.donghyeon.bank.application.account.query.TransactionsQuery;
import site.donghyeon.bank.application.account.repository.AccountRepository;
import site.donghyeon.bank.application.account.repository.AccountTransactionRepository;
import site.donghyeon.bank.application.account.result.TransactionsResult;
import site.donghyeon.bank.domain.account.exception.AccountAccessDeniedException;

@Service
public class AccountTransactionService implements AccountTransactionUseCase {

    private final AccountRepository accountRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    public AccountTransactionService(
            AccountRepository accountRepository,
            AccountTransactionRepository accountTransactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public TransactionsResult getTransactions(TransactionsQuery query) {
        if (!accountRepository.existsByUserIdAndAccountId(query.userId(), query.accountId())) {
            throw new AccountAccessDeniedException();
        }
        return accountTransactionRepository.findByAccountId(query);
    }
}

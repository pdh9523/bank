package site.donghyeon.bank.application.account.service;

import org.springframework.stereotype.Service;
import site.donghyeon.bank.application.account.AccountTransactionUseCase;
import site.donghyeon.bank.application.account.query.TransactionsQuery;
import site.donghyeon.bank.application.account.repository.AccountTransactionRepository;
import site.donghyeon.bank.application.account.result.TransactionsResult;

@Service
public class AccountTransactionService implements AccountTransactionUseCase {

    private final AccountTransactionRepository accountTransactionRepository;

    public AccountTransactionService(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public TransactionsResult getTransactions(TransactionsQuery query) {
        return accountTransactionRepository.findByAccountId(query);
    }
}

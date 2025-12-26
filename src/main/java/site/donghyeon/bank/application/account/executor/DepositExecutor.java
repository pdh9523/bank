package site.donghyeon.bank.application.account.executor;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import site.donghyeon.bank.application.account.repository.AccountRepository;
import site.donghyeon.bank.application.account.repository.AccountTransactionRepository;
import site.donghyeon.bank.application.account.task.DepositTask;
import site.donghyeon.bank.domain.account.Account;
import site.donghyeon.bank.domain.accountTransaction.AccountTransaction;

@Component
@Transactional
public class DepositExecutor {

    private final AccountRepository accountRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    public DepositExecutor(
            AccountRepository accountRepository,
            AccountTransactionRepository accountTransactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    public void execute(DepositTask task) {
        // 1. 멱등성 처리
        System.out.println(1);
        if (accountTransactionRepository.existsById(task.txId())) return;

        // 2. 입금
        System.out.println(2);
        Account account = accountRepository.findById(task.toAccountId());
        account.deposit(task.amount());

        // 3. 거래 내역 생성
        System.out.println(3);
        AccountTransaction tx = AccountTransaction.deposit(task.txId(), task.toAccountId(), task.amount());

        // 4. DB 저장
        System.out.println(4);
        accountRepository.save(account);
        accountTransactionRepository.save(tx);
    }
}

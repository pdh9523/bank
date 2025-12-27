package site.donghyeon.bank.application.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.donghyeon.bank.application.account.AccountOperationUseCase;
import site.donghyeon.bank.application.account.cache.WithdrawalLimitCache;
import site.donghyeon.bank.application.account.command.DepositCommand;
import site.donghyeon.bank.application.account.command.WithdrawalCommand;
import site.donghyeon.bank.application.account.exception.AccountNotFoundException;
import site.donghyeon.bank.application.account.exception.WithdrawalLimitExceededException;
import site.donghyeon.bank.application.account.repository.AccountRepository;
import site.donghyeon.bank.application.account.result.DepositResult;
import site.donghyeon.bank.application.account.result.WithdrawalResult;
import site.donghyeon.bank.application.account.task.DepositTask;
import site.donghyeon.bank.application.account.task.WithdrawalTask;
import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.domain.account.Account;
import site.donghyeon.bank.domain.account.exception.InsufficientBalanceException;
import site.donghyeon.bank.infrastructure.messaging.rabbitmq.deposit.DepositPublisher;
import site.donghyeon.bank.infrastructure.messaging.rabbitmq.withdraw.WithdrawalPublisher;

import java.util.UUID;

@Service
public class AccountOperationService implements AccountOperationUseCase {

    private final static Money WITHDRAWAL_LIMIT = new Money(1_000_000);

    private final AccountRepository accountRepository;
    private final WithdrawalLimitCache withdrawalLimitCache;
    private final DepositPublisher depositPublisher;
    private final WithdrawalPublisher withdrawalPublisher;

    public AccountOperationService(
            WithdrawalLimitCache withdrawalLimitCache,
            AccountRepository accountRepository,
            DepositPublisher depositPublisher,
            WithdrawalPublisher withdrawalPublisher
    ) {
        this.withdrawalLimitCache = withdrawalLimitCache;
        this.accountRepository = accountRepository;
        this.depositPublisher = depositPublisher;
        this.withdrawalPublisher = withdrawalPublisher;
    }

    @Override
    @Transactional
    public DepositResult deposit(DepositCommand command) {
        DepositTask task = new DepositTask(
                UUID.randomUUID(),
                command.toAccountId(),
                new Money(command.amount())
        );

        if (!accountRepository.existsById(command.toAccountId())) {
            throw new AccountNotFoundException(command.toAccountId());
        }

        depositPublisher.publish(task);
        return DepositResult.from(task);
    }

    @Override
    public WithdrawalResult withdrawal(WithdrawalCommand command) {
        Money withdrawalAmount = new Money(command.amount());
        Money spentLimit = withdrawalLimitCache.checkWithdrawalLimit(command.fromAccountId());
        Money expectedLimit = spentLimit.add(withdrawalAmount);

        // 1. 한도 조회
        if (expectedLimit.exceeded(WITHDRAWAL_LIMIT)) {
            throw new WithdrawalLimitExceededException(spentLimit, withdrawalAmount);
        }

        // 2. 잔고 조회
        Account account = accountRepository.findById(command.fromAccountId())
                .orElseThrow(() -> new AccountNotFoundException(command.fromAccountId()));

        if (withdrawalAmount.exceeded(account.getBalance())) {
            throw new InsufficientBalanceException(account.getBalance(), withdrawalAmount);
        }

        // 3. 출금 요청 (pub)
        WithdrawalTask task = new WithdrawalTask(
                UUID.randomUUID(),
                command.fromAccountId(),
                withdrawalAmount
        );
        withdrawalPublisher.publish(task);

        return new WithdrawalResult(task.txId());
    }
}

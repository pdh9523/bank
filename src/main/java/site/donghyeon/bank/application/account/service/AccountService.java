package site.donghyeon.bank.application.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.donghyeon.bank.application.account.AccountUseCase;
import site.donghyeon.bank.application.account.command.CloseAccountCommand;
import site.donghyeon.bank.application.account.command.OpenAccountCommand;
import site.donghyeon.bank.application.account.exception.AccountNotFoundException;
import site.donghyeon.bank.application.account.result.OpenAccountResult;
import site.donghyeon.bank.domain.account.Account;
import site.donghyeon.bank.application.account.repository.AccountRepository;
import site.donghyeon.bank.infrastructure.messaging.rabbitmq.deposit.DepositPublisher;

import java.util.UUID;

@Service
public class AccountService implements AccountUseCase {

    private final AccountRepository accountRepository;
    private final DepositPublisher depositPublisher;

    public AccountService(
            AccountRepository accountRepository,
            DepositPublisher depositPublisher
    ) {
        this.accountRepository = accountRepository;
        this.depositPublisher = depositPublisher;
    }

    @Override
    @Transactional
    public OpenAccountResult openAccount(OpenAccountCommand command) {
        return OpenAccountResult.from(
                accountRepository.save(
                        Account.open(UUID.randomUUID(), command.userId())
                )
        );
    }

    @Override
    public void closeAccount(CloseAccountCommand command) {
        Account account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new AccountNotFoundException(command.accountId()));

        //TODO: 잔액 검증(선택)
        account.close(command.userId());

        accountRepository.save(account);
    }


}

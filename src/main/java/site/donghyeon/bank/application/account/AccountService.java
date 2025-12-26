package site.donghyeon.bank.application.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.donghyeon.bank.application.account.command.CloseAccountCommand;
import site.donghyeon.bank.application.account.command.DepositCommand;
import site.donghyeon.bank.application.account.command.OpenAccountCommand;
import site.donghyeon.bank.application.account.result.DepositResult;
import site.donghyeon.bank.application.account.result.OpenAccountResult;
import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.domain.account.Account;
import site.donghyeon.bank.application.account.repository.AccountRepository;
import site.donghyeon.bank.infrastructure.messaging.rabbitmq.deposit.DepositCommandPublisher;

import java.util.UUID;

@Service
public class AccountService implements AccountUseCase {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
        Account account = accountRepository.findById(command.accountId());
        //TODO: 잔액 검증(선택)
        account.close(command.userId());

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public DepositResult deposit(DepositCommand command) {
        Account account = accountRepository.findById(command.accountId());

        account.deposit(new Money(command.amount()));

        return DepositResult.from(
                accountRepository.save(account)
        );
    }
}

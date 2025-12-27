package site.donghyeon.bank.application.account;

import site.donghyeon.bank.application.account.command.CloseAccountCommand;
import site.donghyeon.bank.application.account.command.DepositCommand;
import site.donghyeon.bank.application.account.command.OpenAccountCommand;
import site.donghyeon.bank.application.account.command.WithdrawalCommand;
import site.donghyeon.bank.application.account.result.DepositResult;
import site.donghyeon.bank.application.account.result.OpenAccountResult;
import site.donghyeon.bank.application.account.result.WithdrawalResult;

public interface AccountUseCase {
    OpenAccountResult openAccount(OpenAccountCommand command);
    void closeAccount(CloseAccountCommand command);
    DepositResult deposit(DepositCommand command);
    WithdrawalResult withdrawal(WithdrawalCommand command);
}

package site.donghyeon.bank.application.account;

import site.donghyeon.bank.application.account.command.CloseAccountCommand;
import site.donghyeon.bank.application.account.command.OpenAccountCommand;
import site.donghyeon.bank.application.account.result.OpenAccountResult;

public interface AccountUseCase {
    OpenAccountResult openAccount(OpenAccountCommand command);
    void closeAccount(CloseAccountCommand command);
}

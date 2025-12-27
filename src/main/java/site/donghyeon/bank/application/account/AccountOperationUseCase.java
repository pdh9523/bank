package site.donghyeon.bank.application.account;

import site.donghyeon.bank.application.account.command.DepositCommand;
import site.donghyeon.bank.application.account.command.TransferCommand;
import site.donghyeon.bank.application.account.command.WithdrawalCommand;
import site.donghyeon.bank.application.account.result.DepositResult;
import site.donghyeon.bank.application.account.result.TransferResult;
import site.donghyeon.bank.application.account.result.WithdrawalResult;

public interface AccountOperationUseCase {
    DepositResult deposit(DepositCommand command);
    WithdrawalResult withdrawal(WithdrawalCommand command);
    TransferResult transfer(TransferCommand command);
}

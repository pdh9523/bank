package site.donghyeon.bank.application.account.exception;

import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.common.exception.ApplicationException;

public class WithdrawalLimitExceededException extends ApplicationException {
    public WithdrawalLimitExceededException(Money spentLimit, Money requested) {
        super("Withdrawal limit exceeded, (spent: %d, requested: %d)"
                .formatted(
                        spentLimit.amount(),
                        requested.amount()
                )
        );
    }
}

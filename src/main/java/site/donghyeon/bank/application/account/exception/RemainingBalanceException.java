package site.donghyeon.bank.application.account.exception;

import site.donghyeon.bank.common.domain.Money;

public class RemainingBalanceException extends RuntimeException {
    public RemainingBalanceException(Money balance) {
        super("This account remain balance, balance: %d".formatted(balance.amount()));
    }
}

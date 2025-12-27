package site.donghyeon.bank.domain.account.exception;

import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.common.exception.DomainException;

public class InsufficientBalanceException extends DomainException {
    public InsufficientBalanceException(Money balance, Money requested) {
        super(
                "Insufficient balance, (available: %d, requested: %d)"
                        .formatted(
                                balance.amount(),
                                requested.amount()
                        )
        );
    }
}

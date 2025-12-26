package site.donghyeon.bank.domain.account.exception;

import site.donghyeon.bank.common.exception.DomainException;

public class InsufficientBalanceException extends DomainException {
    public InsufficientBalanceException(long balance, long requested) {
        super("Insufficient balance, (available: %d, requested: %d)".formatted(balance, requested));
    }
}

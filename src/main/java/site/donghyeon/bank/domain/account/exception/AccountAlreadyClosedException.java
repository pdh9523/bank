package site.donghyeon.bank.domain.account.exception;

import site.donghyeon.bank.common.exception.DomainException;

import java.util.UUID;

public class AccountAlreadyClosedException extends DomainException {
    public AccountAlreadyClosedException(UUID accountId) {
        super("Account already closed, Id: %s".formatted(accountId));
    }
}

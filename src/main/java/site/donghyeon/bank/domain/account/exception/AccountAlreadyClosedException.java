package site.donghyeon.bank.domain.account.exception;

import site.donghyeon.bank.common.exception.ConflictException;

import java.util.UUID;

public class AccountAlreadyClosedException extends ConflictException {
    public AccountAlreadyClosedException(UUID accountId) {
        super("Account already closed, Id: %s".formatted(accountId));
    }
}

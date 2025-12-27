package site.donghyeon.bank.application.account.exception;

import site.donghyeon.bank.common.exception.ApplicationException;

import java.util.UUID;

public class AccountNotFoundException extends ApplicationException {
    public AccountNotFoundException(UUID id) {
        super("Account not found, id: %s".formatted(id));
    }
}

package site.donghyeon.bank.infrastructure.jpa.account.exception;

import site.donghyeon.bank.common.exception.InfraException;

import java.util.UUID;

public class AccountNotFoundException extends InfraException {
    public AccountNotFoundException(UUID id) {
        super("Account not found, id: %s".formatted(id));
    }
}

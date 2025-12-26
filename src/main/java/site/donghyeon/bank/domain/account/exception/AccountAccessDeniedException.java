package site.donghyeon.bank.domain.account.exception;

import site.donghyeon.bank.common.exception.DomainException;

public class AccountAccessDeniedException extends DomainException {
    public AccountAccessDeniedException() {
        super("Account access denied");
    }
}

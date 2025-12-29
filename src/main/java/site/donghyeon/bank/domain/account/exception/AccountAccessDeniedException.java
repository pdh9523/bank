package site.donghyeon.bank.domain.account.exception;

import site.donghyeon.bank.common.exception.ForbiddenException;

public class AccountAccessDeniedException extends ForbiddenException {
    public AccountAccessDeniedException() {
        super("Account access denied");
    }
}

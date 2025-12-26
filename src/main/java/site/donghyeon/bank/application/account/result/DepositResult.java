package site.donghyeon.bank.application.account.result;

import site.donghyeon.bank.domain.account.Account;

import java.util.UUID;

public record DepositResult(
        UUID accountId,
        long balance
) {
    public static DepositResult from(Account account) {
        return new DepositResult(
                account.getAccountId(),
                account.getBalance().amount()
        );
    }
}

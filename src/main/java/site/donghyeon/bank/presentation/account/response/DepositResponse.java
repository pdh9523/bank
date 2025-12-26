package site.donghyeon.bank.presentation.account.response;

import site.donghyeon.bank.application.account.result.DepositResult;

import java.util.UUID;

public record DepositResponse(
        UUID accountId,
        long balance
) {
    public static DepositResponse from(DepositResult result) {
        return new DepositResponse(
                result.accountId(),
                result.balance()
        );
    }
}

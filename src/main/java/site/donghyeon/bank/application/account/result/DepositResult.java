package site.donghyeon.bank.application.account.result;

import site.donghyeon.bank.application.account.task.DepositTask;

import java.util.UUID;

public record DepositResult(
        UUID txId
) {
    public static DepositResult from(DepositTask task) {
        return new DepositResult(
                task.txId()
        );
    }
}

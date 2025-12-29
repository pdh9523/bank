package site.donghyeon.bank.application.account.command;

import java.util.UUID;

public record WithdrawalCommand(
        UUID userId,
        UUID fromAccountId,
        long amount
) {
}

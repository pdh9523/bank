package site.donghyeon.bank.application.account.command;

import java.util.UUID;

public record TransferCommand(
        UUID fromAccountId,
        UUID toAccountId,
        long amount
) {
}

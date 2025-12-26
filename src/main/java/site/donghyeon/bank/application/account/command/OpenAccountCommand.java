package site.donghyeon.bank.application.account.command;

import java.util.UUID;

public record OpenAccountCommand(
        UUID userId
) {
}

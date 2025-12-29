package site.donghyeon.bank.application.account.query;

import site.donghyeon.bank.domain.accountTransaction.enums.LimitType;

import java.util.UUID;

public record AccountLimitQuery(
        UUID userId,
        UUID accountId,
        LimitType type
) {
}

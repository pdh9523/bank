package site.donghyeon.bank.application.account.query;

import java.util.UUID;

public record TransactionsQuery(
        UUID accountId,
        int page,
        int size
) {
}

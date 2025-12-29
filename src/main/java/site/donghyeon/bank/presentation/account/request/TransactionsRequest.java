package site.donghyeon.bank.presentation.account.request;

import site.donghyeon.bank.application.account.query.TransactionsQuery;
import site.donghyeon.bank.common.exception.BadRequestException;

import java.util.UUID;

public record TransactionsRequest(
        Integer page,
        Integer size
) {
    public TransactionsQuery toQuery(UUID userId, UUID accountId) {
        if (accountId == null) {
            throw new BadRequestException("accountId is null");
        }

        return new TransactionsQuery(
                userId,
                accountId,
                this.page == null ? 0 : page,
                this.size == null ? 20 : size
        );
    }
}

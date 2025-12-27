package site.donghyeon.bank.application.account.exception;

import site.donghyeon.bank.common.domain.Money;

public class TransferLimitExceededException extends RuntimeException {
    public TransferLimitExceededException(Money spentLimit, Money requested) {
        super("Transfer limit exceeded, (spent: %d, requested: %d)"
                .formatted(
                        spentLimit.amount(),
                        requested.amount()
                )
        );
    }
}

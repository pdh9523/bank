package site.donghyeon.bank.application.account.cache;

import site.donghyeon.bank.common.domain.Money;

import java.util.UUID;

public interface TransferLimitCache {
    Money checkTransferLimit(UUID accountId);
    boolean tryConsume(UUID accountId, Money amount, Money limit);
    void rollback(UUID accountId, Money amount);
}

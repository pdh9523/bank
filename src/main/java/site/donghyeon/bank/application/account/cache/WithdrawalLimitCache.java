package site.donghyeon.bank.application.account.cache;

import site.donghyeon.bank.common.domain.Money;

import java.util.UUID;

public interface WithdrawalLimitCache {
    Money checkWithdrawalLimit(UUID accountId);
    void increase(UUID accountId, Money amount);
}

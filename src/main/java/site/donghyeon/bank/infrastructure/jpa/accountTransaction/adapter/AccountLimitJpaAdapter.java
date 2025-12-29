package site.donghyeon.bank.infrastructure.jpa.accountTransaction.adapter;

import org.springframework.stereotype.Repository;
import site.donghyeon.bank.common.domain.Money;
import site.donghyeon.bank.common.utils.TimePolicy;
import site.donghyeon.bank.domain.accountTransaction.enums.LimitType;
import site.donghyeon.bank.domain.accountTransaction.enums.TransactionType;

import java.time.Instant;
import java.util.UUID;

@Repository
public class AccountLimitJpaAdapter {

    private final AccountTransactionJpaRepository accountTransactionJpaRepository;

    public AccountLimitJpaAdapter(AccountTransactionJpaRepository accountTransactionJpaRepository) {
        this.accountTransactionJpaRepository = accountTransactionJpaRepository;
    }

    public Money readDailyLimit(UUID accountId, LimitType type) {
        TransactionType txType = type.getTransactionType();

        Instant from = TimePolicy.startOfToday();

        Instant to = TimePolicy.startOfTomorrow();

        long sum = accountTransactionJpaRepository.sumDailyAmount(
                accountId,
                txType,
                from,
                to
        );

        return new Money(-sum);
    }
}

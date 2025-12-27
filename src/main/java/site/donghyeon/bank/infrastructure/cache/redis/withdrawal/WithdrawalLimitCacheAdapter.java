package site.donghyeon.bank.infrastructure.cache.redis.withdrawal;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import site.donghyeon.bank.application.account.cache.WithdrawalLimitCache;
import site.donghyeon.bank.common.domain.Money;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class WithdrawalLimitCacheAdapter implements WithdrawalLimitCache {

    private static final Duration TTL = Duration.ofDays(1);

    private final StringRedisTemplate stringRedisTemplate;

    public WithdrawalLimitCacheAdapter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Money checkWithdrawalLimit(UUID accountId) {
        String key = keyOf(accountId);

        String value = stringRedisTemplate.opsForValue().get(key);

        long used = value == null ? 0L : Long.parseLong(value);

        return new Money(used);
    }

    @Override
    public void increase(UUID accountId, Money amount) {
        String key = keyOf(accountId);
        Long newValue = stringRedisTemplate.opsForValue()
                .increment(key, amount.amount());

        if (newValue.equals(amount.amount())) {
            stringRedisTemplate.expire(key,TTL);
        }
    }

    private String keyOf(UUID accountId) {
        return "withdrawal:daily:%s:%s"
                .formatted(accountId, LocalDate.now());
    }
}

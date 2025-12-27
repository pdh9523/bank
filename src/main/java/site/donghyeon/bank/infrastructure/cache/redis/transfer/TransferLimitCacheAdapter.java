package site.donghyeon.bank.infrastructure.cache.redis.transfer;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import site.donghyeon.bank.application.account.cache.TransferLimitCache;
import site.donghyeon.bank.common.domain.Money;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class TransferLimitCacheAdapter implements TransferLimitCache {

    private static final Duration TTL = Duration.ofDays(1);

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisScript<Long> tryConsumeLimitScript;

    public TransferLimitCacheAdapter(
            StringRedisTemplate stringRedisTemplate,
            RedisScript<Long> tryConsumeLimitScript
    ) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.tryConsumeLimitScript = tryConsumeLimitScript;
    }

    @Override
    public Money checkTransferLimit(UUID accountId) {
        String key = keyOf(accountId);

        String value = stringRedisTemplate.opsForValue().get(key);

        long used = value == null ? 0L : Long.parseLong(value);

        return new Money(used);
    }

    @Override
    public boolean tryConsume(UUID accountId, Money amount, Money limit) {
        String key = keyOf(accountId);

        Long result = stringRedisTemplate.execute(
                tryConsumeLimitScript,
                List.of(key),
                String.valueOf(amount.amount()),
                String.valueOf(limit.amount()),
                String.valueOf(TTL.toSeconds())
        );

        // Lua: 1 = 성공, 0 = 한도 초과
        return result != null && result == 1L;
    }

    @Override
    public void rollback(UUID accountId, Money amount) {
        stringRedisTemplate.opsForValue().decrement(keyOf(accountId), amount.amount());
    }

    private String keyOf(UUID accountId) {
        return "transfer:daily:%s:%s"
                .formatted(accountId, LocalDate.now());
    }
}

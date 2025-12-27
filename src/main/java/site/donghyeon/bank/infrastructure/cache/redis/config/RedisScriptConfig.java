package site.donghyeon.bank.infrastructure.cache.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

@Configuration
public class RedisScriptConfig {

    @Bean
    public RedisScript<Long> tryConsumeLimitScript() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setLocation(
                new ClassPathResource("/script/try_consume_limit.lua")
        );
        script.setResultType(Long.class);
        return script;
    }
}

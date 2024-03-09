package net.protsenko.shortLinkService.links;

import net.protsenko.shortLinkService.common.ShortLink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    ReactiveRedisOperations<String, ShortLink> redisOperations(ReactiveRedisConnectionFactory factory) {

        RedisSerializationContext.RedisSerializationContextBuilder<String, ShortLink> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        builder.value(new Jackson2JsonRedisSerializer<>(ShortLink.class));

        RedisSerializationContext<String, ShortLink> context = builder.build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

}

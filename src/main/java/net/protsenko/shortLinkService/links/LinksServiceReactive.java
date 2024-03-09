package net.protsenko.shortLinkService.links;

import net.protsenko.shortLinkService.common.ShortLink;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class LinksServiceReactive implements LinksService{

    private final Long SAVE_TIMEOUT_MS = 5000L;

    private final ReactiveRedisOperations<String, ShortLink> operations;

    public LinksServiceReactive(ReactiveRedisOperations<String, ShortLink> operations) {
        this.operations = operations;
    }

    @Override
    public void save(ShortLink link) {
        operations.opsForValue().set(link.getCode(), link, Duration.ofMillis(SAVE_TIMEOUT_MS)).subscribe();
    }

    @Override
    public void remove(String code) {
        operations.delete(code).subscribe();
    }

    @Override
    public Mono<ShortLink> get(Mono<String> code) {
        return code.flatMap(operations.opsForValue()::get);
    }

    @Override
    public Mono<ShortLink> randomPull() {
        return get(randomKey());
    }

    @Override
    public Mono<String> randomKey() {
        return operations.randomKey();
    }
}

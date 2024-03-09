package net.protsenko.shortLinkService.links;

import net.protsenko.shortLinkService.common.ShortLink;
import reactor.core.publisher.Mono;

public interface LinksService {

    void save(ShortLink link);

    void remove(String code);

    Mono<ShortLink> get(Mono<String> code);

    Mono<ShortLink> randomPull();

    Mono<String> randomKey();



}

package net.protsenko.shortLinkService;

import net.protsenko.shortLinkService.links.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class ShortLinkServiceApplication {

	private final LinksService linksService;

	@Autowired
	public ShortLinkServiceApplication(LinksService linksService) {
		this.linksService = linksService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShortLinkServiceApplication.class, args);
	}


	@GetMapping
	public Mono<Integer> code() {
		return linksService.randomPull().then(Mono.just(Thread.activeCount()));
	}
}

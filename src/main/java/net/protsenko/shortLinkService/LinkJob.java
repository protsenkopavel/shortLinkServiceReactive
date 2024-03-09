package net.protsenko.shortLinkService;

import net.protsenko.shortLinkService.common.ShortLink;
import net.protsenko.shortLinkService.links.LinksService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
@EnableScheduling
public class LinkJob {

    private final LinksService linksService;
    private final Queue<String> linksQueue;

    public LinkJob(LinksService linksService, Queue<String> linksQueue) {
        this.linksService = linksService;
        this.linksQueue = linksQueue;
    }

    @Scheduled(fixedRate = 1000L)
    public void freeLink() {
        linksService.randomPull().doOnSuccess(shortLink -> {
            if (shortLink != null) {
                linksService.remove(shortLink.getCode());
                linksQueue.add(shortLink.getCode());
            }
        }).subscribe(System.out::println);
    }

    @Scheduled(fixedRate = 500L)
    public void bookLink() {
        String code = linksQueue.poll();
        if (code != null) {
            linksService.save(new ShortLink(code, "gmail.com"));
        }
    }

    @Scheduled(fixedRate = 500L)
    public void useLink() {
        linksService.randomPull().subscribe(System.out::println);
    }
}

package net.protsenko.shortLinkService.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.Queue;

@Configuration
public class LinksConfiguration {

    @Bean
    Queue<String> linksQueue() {
        Queue<String> res = new LinkedList<>();

        String base = "qwertyuiopasdfghjklzxcvbnm0123456789";
        for (int a = 0; a < base.length(); a++) {
            for (int b = 0; b < base.length(); b++) {
                for (int c = 0; c < base.length(); c++) {
                    res.add(String.valueOf(base.charAt(a)) +
                            base.charAt(b) +
                            base.charAt(c));
                }
            }
        }

        return res;
    }

}

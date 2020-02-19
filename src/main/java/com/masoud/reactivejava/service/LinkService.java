package com.masoud.reactivejava.service;

import com.masoud.reactivejava.model.Link;
import com.masoud.reactivejava.repository.LinkRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class LinkService {

    @Value("${app.baseUrl}")
    private String baseUrl;

    //@Autowired
    private LinkRepository linkRepository;

    public Mono<String> shortenLink(String link) {
        String randomKey = RandomStringUtils.randomAlphabetic(6);
        return linkRepository.save(new Link(link, randomKey))
                .map(result -> baseUrl + result.getKey());
//        return Mono.just(baseUrl + randomKey);
    }

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }
}

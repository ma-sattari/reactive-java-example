package com.masoud.reactivejava.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LinkService {

    @Value("${app.baseUrl}")
    private String baseUrl;

    public Mono<String> shortenLink(String link) {
        String randomKey = RandomStringUtils.randomAlphabetic(6);

        return Mono.just(baseUrl + randomKey);
    }
}

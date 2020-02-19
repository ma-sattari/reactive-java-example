package com.masoud.reactivejava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

public class LinkServiceTest {

    private LinkService linkService = new LinkService("http://my-domain.com/");

//    @Value("${app.baseUrl}")
//    private String appUrl;

    @Test
    public void shortenLink() {

        StepVerifier.create(linkService.shortenLink("https://spring.io"))
                .expectNextMatches(result -> result != null && result.length() > 0
                && result.startsWith("http://my-domain.com/"))
                .expectComplete()
                .verify();
    }
}
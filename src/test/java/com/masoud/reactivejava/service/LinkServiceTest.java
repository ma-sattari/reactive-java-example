package com.masoud.reactivejava.service;

import com.masoud.reactivejava.model.Link;
import com.masoud.reactivejava.repository.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkServiceTest {

    private LinkRepository linkRepository = mock(LinkRepository.class);
    private LinkService linkService = new LinkService("http://my-domain.com/", linkRepository);

    @Before
    public void setup() {
        when(linkRepository.save(any())).thenAnswer(invocationOnMock -> Mono.just((Link) invocationOnMock.getArguments()[0]));
    }

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
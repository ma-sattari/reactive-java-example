package com.masoud.reactivejava.controller;

import com.masoud.reactivejava.model.Link;
import com.masoud.reactivejava.service.LinkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest(controllers = LinkController.class)
public class LinkControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LinkService linkService;

    @Test
    public void shortenLink() {
        when(linkService.shortenLink("https://spring.io")).thenReturn(Mono.just("http://localhost:8080/asdf1234"));
        webTestClient.post()
                .uri("/link")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"link\":\"https://spring.io\"}")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.shortenedLink")
                .value(val -> assertThat(val).isEqualTo("http://localhost:8080/asdf1234"));
    }

    @Test
    public void redirectsToOriginalLink() {
        when(linkService.getOriginalLink("asdf1234")).thenReturn(Mono.just(new Link("https//spring.io","asdf1234")));
        webTestClient.get()
                .uri("/asdf1234")
                .exchange()
                .expectStatus()
                .isPermanentRedirect()
                .expectHeader()
                .value("Location", location -> assertThat(location).isEqualTo("https//spring.io"));

    }
}

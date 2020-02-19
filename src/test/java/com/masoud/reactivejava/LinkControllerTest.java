package com.masoud.reactivejava;

import com.masoud.reactivejava.controller.LinkController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@WebFluxTest(controllers = LinkController.class)
public class LinkControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shortenLink(){
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
}

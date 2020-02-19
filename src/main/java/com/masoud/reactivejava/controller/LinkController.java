package com.masoud.reactivejava.controller;

import com.masoud.reactivejava.model.CreateLinkRequest;
import com.masoud.reactivejava.model.CreateLinkResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LinkController {

    @PostMapping("/link")
    Mono<CreateLinkResponse> create (@RequestBody CreateLinkRequest createLinkRequest){

        return Mono.just(new CreateLinkResponse("http://localhost:8080/asdf1234"));
    }
}

package com.masoud.reactivejava.controller;

import com.masoud.reactivejava.model.CreateLinkRequest;
import com.masoud.reactivejava.model.CreateLinkResponse;
import com.masoud.reactivejava.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/link")
    public Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest createLinkRequest) {
        return linkService.shortenLink(createLinkRequest.getLink())
                .map(CreateLinkResponse::new);
//        return Mono.just(new CreateLinkResponse("http://localhost:8080/asdf1234"));
    }
}

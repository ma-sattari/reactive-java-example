package com.masoud.reactivejava.controller;

import com.masoud.reactivejava.model.CreateLinkRequest;
import com.masoud.reactivejava.model.CreateLinkResponse;
import com.masoud.reactivejava.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{key}")
    public Mono<ResponseEntity<Object>> getLink (@PathVariable String key){
        return linkService.getOriginalLink(key)
                .map(link -> ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                .header("Location",link.getOriginalLink())
                .build())
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}

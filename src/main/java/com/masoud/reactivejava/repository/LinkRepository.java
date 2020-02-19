package com.masoud.reactivejava.repository;

import com.masoud.reactivejava.model.Link;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LinkRepository {

    Mono<Link> save(Link link);

    Mono<Link> findByKey(String key);
}

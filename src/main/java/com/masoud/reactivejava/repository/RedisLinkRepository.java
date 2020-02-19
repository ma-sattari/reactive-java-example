package com.masoud.reactivejava.repository;

import com.masoud.reactivejava.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RedisLinkRepository implements LinkRepository {

    @Autowired
    private ReactiveRedisOperations<String, String> reactiveRedisOperations;

    @Override
    public Mono<Link> save(Link link) {
        return reactiveRedisOperations.opsForValue()
                .set(link.getKey(), link.getOriginalLink())
                .map(__ -> link);
    }

    @Override
    public Mono<Link> findByKey(String key) {
        return reactiveRedisOperations.opsForValue()
                .get(key)
                .map(result -> new Link(result,key));
    }


}

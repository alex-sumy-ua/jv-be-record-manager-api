package com.northcoders.albummanagerapi.controller;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

@Bean
    public CaffeineCache albumCache1() {
    return new CaffeineCache("albumCache1",
            Caffeine.newBuilder()
                    .expireAfterAccess(20, TimeUnit.SECONDS)
                    .build());
}

@Bean
    public CaffeineCache albumCach2() {
    return new CaffeineCache("albumCache2",
            Caffeine.newBuilder()
                    .expireAfterAccess(5, TimeUnit.SECONDS)
                    .build());
}

}

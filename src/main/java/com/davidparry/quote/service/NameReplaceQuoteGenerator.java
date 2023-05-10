package com.davidparry.quote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class NameReplaceQuoteGenerator implements QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(NameReplaceQuoteGenerator.class);

    private final WebClient webClient;

    public NameReplaceQuoteGenerator(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<String> generateQuote(String name) {
        return webClient.get().retrieve().bodyToMono(Joke.class).map(replaceFunction(name));
    }

    Function<Joke, String> replaceFunction(String name) {
        return joke -> joke.value().replace("Chuck Norris", name);
    }
}

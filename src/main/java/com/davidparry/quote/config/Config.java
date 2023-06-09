package com.davidparry.quote.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Configuration
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    public static String CHUCK_NORRIS_API_URL = "https://api.chucknorris.io/jokes/random?category=dev";

    @Bean
    public WebClient webClient() {
        return WebClient.builder().filter(
                ExchangeFilterFunction.ofResponseProcessor(this::renderApiErrorResponse)
        ).baseUrl(CHUCK_NORRIS_API_URL).build();
    }

    private Mono<ClientResponse> renderApiErrorResponse(ClientResponse clientResponse) {
        if (clientResponse.statusCode().isError()) {
            return clientResponse.bodyToMono(ApiErrorResponse.class).doOnNext(apiErrorResponse -> {
                logger.error("Chuck API gave us this {}", apiErrorResponse);
            }).flatMap(apiErrorResponse -> Mono.error(
                    new ResponseStatusException(HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()))));
        }
        return Mono.just(clientResponse);
    }


}

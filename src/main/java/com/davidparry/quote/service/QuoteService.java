package com.davidparry.quote.service;

import reactor.core.publisher.Mono;

public interface QuoteService {

    Mono<String> generateQuote(String name);
 }

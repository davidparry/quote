package com.davidparry.quote.service

import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Unroll

class NameReplaceQuoteGeneratorSpec extends Specification {

    @Unroll
    def "Happy path to test #data"() {
        WebClient webClient = Mock(WebClient.class)
        String QUOTE_RESPONSE = "Chuck Norris Quote"
        WebClient.RequestHeadersUriSpec headersUriSpec = Mock(WebClient.RequestHeadersUriSpec.class)
        WebClient.ResponseSpec responseSpec = Mock(WebClient.ResponseSpec.class)
        Mono<Joke> mono = Mono.just(new Joke(QUOTE_RESPONSE))

        NameReplaceQuoteGenerator service = new NameReplaceQuoteGenerator(webClient)

        when:
        Mono<String> monoResponse = service.generateQuote("Name Last")
        String quote = monoResponse.block()


        then:
        1 * webClient.get() >> headersUriSpec
        1 * headersUriSpec.retrieve() >> responseSpec
        1 * responseSpec.bodyToMono(Joke.class) >> mono
        quote == "Name Last Quote"



    }

}

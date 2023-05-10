package com.davidparry.quote.service;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

@RegisterReflectionForBinding
public record Joke(String value) {
}

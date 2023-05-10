package com.davidparry.quote.controller;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

@RegisterReflectionForBinding
public record Quote(String quote) {
}

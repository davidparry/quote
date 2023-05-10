package com.davidparry.quote.config;

public record ApiErrorResponse(String status, String error, String message, String path) {
}

package com.web.error;

import com.web.exception.RecordException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.base.base.exception.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class FeignCircuitBreakerErrorDecoder implements ErrorDecoder {

    Environment env;

    @Autowired
    public FeignCircuitBreakerErrorDecoder(Environment env) {
        this.env = env;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.reason() != null && !response.reason().isEmpty()) {
            return new BackendException(response.reason());
        }

        String errorMessage = null;

        try {
            if (response.body() != null) {
                errorMessage = getResponseBody(response);
            } else {
                errorMessage = "Unknown error";
            }
        } catch (IOException e) {
            errorMessage = "Failed to process response body";
        }

        switch (response.status()) {
            case 400:
            case 404:
            case 500:
                return new BackendException(errorMessage);
            default:
                return new BackendException("Generic error: " + errorMessage + " - " + response.status());
        }
    }

    private String getResponseBody(Response response) throws IOException {
        if (response.body() == null) {
            return null;
        }

        return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}

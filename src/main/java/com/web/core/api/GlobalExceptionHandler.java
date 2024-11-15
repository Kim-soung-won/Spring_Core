package com.web.core.api;

import com.web.core.exception.RecordException;
import lombok.extern.slf4j.Slf4j;
import org.base.base.api.ApiResponseDto;
import org.base.base.exception.BackendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler(BackendException.class)
    public ResponseEntity<ApiResponseDto<Map<String, Object>>> handleBackEndExceptions(BackendException ex, WebRequest request) {
        HttpStatus status = ex.getErrorCode() != null ? HttpStatus.valueOf(ex.getErrorCode()) : HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", ex.getCause());
        body.put("message", ex.getMessage() != null ? ex.getMessage() : ex.getReason());
        body.put("path", request.getDescription(false).substring(4)); // Strip "uri=" prefix

        ApiResponseDto<Map<String, Object>> response = new ApiResponseDto<>(false, body);
        return new ResponseEntity<>(response, status);
    }
}


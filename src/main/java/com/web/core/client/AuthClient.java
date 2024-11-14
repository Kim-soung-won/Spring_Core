package com.web.core.client;

import com.web.core.error.FeignCircuitBreakerErrorDecoder;
import org.base.base.api.ApiResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service", configuration = FeignCircuitBreakerErrorDecoder.class, url = "${auth-service-url}")
@Qualifier("AuthHttpClient")
public interface AuthClient extends HttpClient{
    @PostMapping("/api/no-auth/encode")
    ApiResponseDto<String> encodePassword(@RequestBody String rawPassword);
}

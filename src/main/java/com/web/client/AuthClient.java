package com.web.client;

import com.web.error.FeignCircuitBreakerErrorDecoder;
import org.base.base.api.ApiResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", configuration = FeignCircuitBreakerErrorDecoder.class, url = "${auth-service-url}")
@Qualifier("AuthHttpClient")
public interface AuthClient extends HttpClient{
    @PostMapping("/api/no-auth/encode")
    ApiResponseDto<String> encodePassword(@RequestBody String rawPassword);
}

package com.web.core.api.auth;

import com.web.core.service.noauth.NoAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.base.base.api.ApiResponseDto;
import org.base.base.exception.BackendException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final NoAuthService noAuthService;

    @GetMapping("/test")
    public ApiResponseDto<String> test() throws BackendException, InterruptedException {
        return new ApiResponseDto<String>(true, noAuthService.hana("ccc", "1234"));
    }
}

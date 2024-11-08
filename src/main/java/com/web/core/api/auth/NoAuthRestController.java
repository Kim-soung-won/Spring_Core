package com.web.core.api.auth;

import com.web.core.service.auth.ManagerDto;
import com.web.core.service.noauth.NoAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.base.base.api.ApiResponseDto;
import org.base.base.exception.BackendException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/no-auth")
@Tag(name = "인증 없음", description = "인증 없이 사용 가능한 API")
public class NoAuthRestController {
    private final NoAuthService noAuthService;

    @Operation(summary = "관리자 회원가입", description = "관리자 회원가입")
    @PostMapping(value = "/signup", produces = "application/json")
    public ApiResponseDto<?> signup(
            @RequestBody ManagerDto managerDto) throws BackendException {
        return new ApiResponseDto<>(true, noAuthService.signup(managerDto));
    }
}

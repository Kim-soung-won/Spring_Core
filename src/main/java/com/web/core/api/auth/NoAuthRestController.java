package com.web.core.api.auth;

import com.web.core.client.AuthClient;
import com.web.core.service.auth.ManagerDto;
import com.web.core.service.auth.ManagerService;
import com.web.core.service.noauth.NoAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.base.base.api.ApiResponseDto;
import org.base.base.exception.BackendException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/no-auth")
@Tag(name = "인증 없음", description = "인증 없이 사용 가능한 API")
public class NoAuthRestController {
    private final NoAuthService noAuthService;
    private final ManagerService managerService;
    private final AuthClient authClient;

    @Operation(summary = "관리자 회원가입", description = "관리자 회원가입")
    @PostMapping(value = "/signup", produces = "application/json")
    public ApiResponseDto<?> signup(
            @RequestBody ManagerDto managerDto) throws BackendException {
        return new ApiResponseDto<>(true, noAuthService.signup(managerDto));
    }

    @GetMapping("/manager/{managerId}")
    public ApiResponseDto<String> getManagerById(@PathVariable("managerId") String managerId) {
        return new ApiResponseDto<String>(true, managerService.getManager(managerId).getId());
    }
}

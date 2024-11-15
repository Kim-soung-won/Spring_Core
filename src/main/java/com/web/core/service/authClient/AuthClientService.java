package com.web.core.service.authClient;

import com.web.core.client.AuthClient;
import com.web.core.exception.RecordException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.base.base.exception.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthClientService {

    @Autowired
    @Qualifier("AuthHttpClient")
    public void setClient(AuthClient AuthClient) {
        this.authClient = AuthClient;
    }

    protected AuthClient authClient;

    @CircuitBreaker(name = "basicCircuitBreakerConfig", fallbackMethod = "signupFallback")
    public String encodePassword(String password) {
        try {
            return authClient.encodePassword(password).getData();
        }catch (Exception e){
            throw new RecordException("인증 서버 오류 발생");
        }
    }

    // fallback 메소드는 기존 메서드와 반환 타입이 같아야 한다.
    private String signupFallback(Exception e) throws BackendException {
        log.error("[Auth Service Error] callFallback {}", e.getMessage());
        throw new BackendException(e.getMessage());
    }

    private String signupFallback(CallNotPermittedException e) throws BackendException {
        log.warn("[CircuitBreaker : OPEN] CircuitBreaker is Open .");
        throw new BackendException("인증 서버 오류 발생");
    }
}

package com.web.core.config;

import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
public class CircuitBreaker {

    @EventListener(value = CircuitBreakerOnStateTransitionEvent.class)
    public void onStateTransitionEvent(CircuitBreakerOnStateTransitionEvent event) {
        log.info("Circuit Breaker {} state changed from {} to {}",
                event.getCircuitBreakerName(),
                event.getStateTransition().getFromState(),
                event.getStateTransition().getToState());
    }
}

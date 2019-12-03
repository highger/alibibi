package com.mgy.idempotence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 */
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class IdempotenceAspect {

    @Pointcut("@annotation(com.mgy.idempotence.IdempotentConsume)")
    public void idempotentConsume() {
    }

    @PostConstruct
    public void init() {
        log.info("idempotenceAspect init!");
    }

    @Around("idempotentConsume()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("idempotence control, joinPoint = {}", joinPoint);
        return joinPoint.proceed();
    }


}

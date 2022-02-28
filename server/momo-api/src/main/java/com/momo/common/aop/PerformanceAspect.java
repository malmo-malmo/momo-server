package com.momo.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class PerformanceAspect {

    private final ObjectMapper objectMapper;

    private static final String PERFORMANCE_FORMAT = "Perform : {}";

    private static final double MILLI_TO_SECOND_UNIT = 0.001;
    private static final double MAX_PERFORMANCE_TIME = 3;

    @Around("execution(* com.momo.api..*Controller.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
            RequestContextHolder.getRequestAttributes())).getRequest();

        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime) * MILLI_TO_SECOND_UNIT;

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("method", request.getMethod());
        map.put("uri", request.getRequestURI());
        map.put("time", elapsedTime + "s");

        String resultJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        if (elapsedTime > MAX_PERFORMANCE_TIME) {
            log.warn(PERFORMANCE_FORMAT, resultJson);
            return proceed;
        }

        log.info(PERFORMANCE_FORMAT, resultJson);

        return proceed;
    }
}

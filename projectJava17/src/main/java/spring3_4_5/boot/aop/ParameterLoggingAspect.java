package spring3_4_5.boot.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aspect
@Component
public class ParameterLoggingAspect {
    @Before("execution(* spring3_4_5.boot.service.*.*(..))")
    public void logMethodParameters(JoinPoint joinPoint) throws JsonProcessingException {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        if (args.length == 0) {
            log.debug("{} - {}: 매개변수 없음", className, methodName);
            return;
        }

        // 모든 인자를 한 번에 로깅
        List<String> argDetails = new ArrayList<>();
        for (Object arg : args) {
            if (arg == null) {
                argDetails.add("null");
            } else {
                argDetails.add(objectMapper.writeValueAsString(arg));
            }
        }

        log.info("{} - {} | 파라미터: {}",
                className, methodName, String.join(", ", argDetails));
    }



    private final ObjectMapper objectMapper;

    public ParameterLoggingAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}

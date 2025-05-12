package spring3_4_5.boot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@Aspect
public class TimeLog {

    // 컨트롤러와 서비스 모두를 대상으로 하는 포인트컷
    @Around("execution(* spring3_4_5.boot.controller.*.*(..)) || execution(* spring3_4_5.boot.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String packageName = joinPoint.getTarget().getClass().getPackage().getName();

        String type;
        String indent = "";

        if (packageName.contains("controller")) {
            type = "[컨트롤러]";
        } else if (packageName.contains("service")) {
            type = "   [서비스]";
            indent = "   ";
        } else {
            type = "[알수없음]";
        }

        long start = System.currentTimeMillis();
        log.info("{}", type + " 시작 " + className + "." + methodName);
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("{}-실행시간:{}", type + " 종료 " + className + "." + methodName, indent + executionTime + "ms");

        return proceed;
    }
}

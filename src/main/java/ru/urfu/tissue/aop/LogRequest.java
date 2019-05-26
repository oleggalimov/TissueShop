package ru.urfu.tissue.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;

@Component
@Aspect
public class LogRequest {
    @Around( "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object viewLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        System.out.printf("Запрошено представление: %s%n",proceed);
        return proceed;
    }

    @Around("@annotation(ru.urfu.tissue.aop.customannotations.RequestLogger)")
    public Object requestLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        String signature = joinPoint.getSignature().toString();
        Object [] args = joinPoint.getArgs();
        for (Object arg: args) {
            try {
                SecurityContextHolderAwareRequestWrapper wrapper = (SecurityContextHolderAwareRequestWrapper) arg;
                ServletRequest req = wrapper.getRequest();
                System.out.printf("Входящий запрос с сервера: %s%n", req.getRemoteHost());
            }catch (ClassCastException ex) {
                //не запрос
            }
        }
        Object proceed = joinPoint.proceed();
        System.out.printf("Вызван метод: %s%n",signature);
        return proceed;
    }
}

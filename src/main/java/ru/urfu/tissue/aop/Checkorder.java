package ru.urfu.tissue.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.urfu.tissue.dao.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class Checkorder {
    @Pointcut ("execution(* ru.urfu.tissue.controllers.*.*.*(..))")

    private void checkIfOrderExists() {
    }

    @Around("checkIfOrderExists()")
    public Object Log(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            try {
                HttpSession session = ((HttpServletRequest) arg).getSession(true);
                Order order = (Order) session.getAttribute("Order");
                if (order==null) {
                    System.out.println("Не найден заказ в текущей сессии");
                    order = new ru.urfu.tissue.dao.Order();
                    session.setAttribute("Order", order);
                }
            } catch (ClassCastException | NullPointerException ex) {
                System.out.println("Не удалось конвертировать аргумент в сессию. Это нормально");
            }
        }
        return joinPoint.proceed();
    }
}

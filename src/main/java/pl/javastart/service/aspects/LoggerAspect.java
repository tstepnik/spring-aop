package pl.javastart.service.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import pl.javastart.model.Book;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    @Before("pl.javastart.service.aspects.AspectUtil.allBookRepositoryMethods()")
    public void logInfoBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.printf("Log before %s with args: %s\n",
                joinPoint.getSignature(),
                Arrays.toString(args));
    }

    @After("pl.javastart.service.aspects.AspectUtil.allBookRepositoryMethods()")
    public void logInfoAfter(JoinPoint joinPoint) {
        System.out.printf("Method %s executed \n",joinPoint.getSignature());
    }

    @AfterThrowing(
            pointcut = "pl.javastart.service.aspects.AspectUtil.allBookRepositoryMethods()",
            throwing = "error")
    public void logError(JoinPoint joinPoint, Throwable error) {
        System.out.printf("Method %s finished with error %s", joinPoint.getSignature(), error.getMessage());
    }

    @AfterReturning(
            value = "execution(* pl.javastart.service.BookRepository.*(..)) && args(isbn)",
            returning = "result")
    public void logSuccess(JoinPoint joinPoint, String isbn, Book result) {
        if (result != null) {
            System.out.printf("Method get() successfully returned value %s for isbn %s\n", result, isbn);
        }
    }
}

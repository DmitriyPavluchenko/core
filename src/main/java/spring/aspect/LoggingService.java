package spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingService {

    private final Logger log = Logger.getLogger("LoggingService");

    @Around(value = "@annotation(logged)")
    public Object aroundLogging(ProceedingJoinPoint proceedingJoinPoint, Logged logged) throws Throwable {
        log(logged.level(), String.format("Called %s with args %s", proceedingJoinPoint.getSignature(),
                Arrays.toString(proceedingJoinPoint.getArgs())));

        Object result = proceedingJoinPoint.proceed();
        if (result != null) log(logged.level(), String.format("Method returned %s", result));
        return result;
    }


    private void log(String level, String message) {
        log.log(Level.parse(level), message);
    }
}

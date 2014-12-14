package com.uzelac.monitor;

import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLogging
{
    private final Logger logger = LoggerFactory.getLogger(ServiceLogging.class);

    @Pointcut("execution(* com.uzelac.service.*.*(..))")
    public void businessMethods()
    {
    }

    @Around(value = "businessMethods()")
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable
    {
        final Signature signature = joinPoint.getSignature();

        final Stopwatch sw = Stopwatch.createStarted();

        try
        {
            Object result = joinPoint.proceed();

            sw.stop();
            logger.debug("Service method: {}, arguments: {}, duration: {}", signature.toShortString(), joinPoint.getArgs(), sw);

            return result;
        }
        catch (ValidationException | XMLException ex)
        {
            sw.stop();
            throw ex;
        }
        catch (Exception ex)
        {
            sw.stop();
            logger.error("Exception on request: " + signature + " with arguments " + Arrays.asList(joinPoint.getArgs()) + ", duration=" + sw + ", msg: " + ex.getMessage(), ex);
            throw ex;
        }
    }
}

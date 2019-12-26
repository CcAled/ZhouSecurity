/**
 * 
 */
package com.zhou.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * zhousecurity
 * 2019/11/1 10:30
 *
 * @author ChristChou
 * @since
 **/
@Aspect
@Component
public class TimeAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Around("execution(* com.zhou.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		logger.info("time aspect start");
		
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			logger.info("arg is "+arg);
		}
		
		long start = new Date().getTime();
		
		Object object = pjp.proceed();

		logger.info("time aspect 耗时:"+ (new Date().getTime() - start));
		logger.info("time aspect end");
		
		return object;
	}

}

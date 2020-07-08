package com.riyadbank.course.app.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccountAspect {

	Logger logger = LogManager.getLogger();
	
	@Before("execution(* com.riyadbank.course.app.repository.account.IAccountRepository.find*(..))")
	public void beforeAdvice(JoinPoint jp) {
		logger.info("this message is before the method = "+jp.getSignature().toLongString());
	}
	
	@AfterReturning("execution(* com.riyadbank.course.app.repository.account.IAccountRepository.find*(..))")
	public void afterReturning(JoinPoint jp) {
		logger.info("this message is after the return of the method = "+jp.getSignature().toLongString());
	}
	
	@AfterThrowing(pointcut = "execution(* com.riyadbank.course.app.services.IAccountService.find*(..))", throwing = "e")
	public void afterThrowing(JoinPoint jp, Exception e) {
		logger.info("this message is after throwing an exception of = "+jp.getSignature().toLongString());
		logger.info("Exception = "+e.getMessage());
	}
	
	@After("execution(* com.riyadbank.course.app.services.IAccountService.find*(..))")
	public void after(JoinPoint jp) {
		logger.info("this message is after a result of = "+jp.getSignature().toLongString());
	}
	
	@Around("execution(* com.riyadbank.course.app.services.IAccountService.find*(..))")
	public Object around(ProceedingJoinPoint jp) {
		Object result = null;
		try {
			logger.info("[AROUND] This message is before to proceed with = "+jp.getSignature().toLongString());
			result =jp.proceed();
			logger.info("[AROUND] This message is after to proceed with = "+jp.getSignature().toLongString());
			return result;
		} catch (Throwable e) {
			logger.info("[AROUND] An exception was throwed in the around advice = "+e.getMessage());
			return e;
		}
	}
	
	
	
	
}

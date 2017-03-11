package com.mrppa.widgetmanager.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspects {
	private static final Logger LOG = LoggerFactory.getLogger(LoggingAspects.class);

	@Before("within(@com.mrppa.widgetmanager.logging.EnhanceLogging *) && execution(public * *(..))")
	public void logBefore(JoinPoint joinpoint){
		LOG.debug("BEFORE METHOD\t"+joinpoint.getTarget()+"."+joinpoint.getSignature().getName());
		Object[] signatureArgs = joinpoint.getArgs();
		for (Object signatureArg: signatureArgs) {
			LOG.debug("\t\tArg\t:" + signatureArg);
		}		
	}

	@After("within(@com.mrppa.widgetmanager.logging.EnhanceLogging *) && execution(public * *(..))")
	public void logAfter(JoinPoint joinpoint){
		LOG.debug("AFTER METHOD\t"+joinpoint.getTarget()+"."+joinpoint.getSignature().getName() );
	}
}

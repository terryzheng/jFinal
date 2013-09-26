package com.zhaopin.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.log.Log4jLogger;
import com.jfinal.log.Logger;

public class globalInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		System.out.println("Before global invoking " + ai.getActionKey());
		Logger log = Log4jLogger.getLogger(getClass());
		log.warn("abc");
		ai.invoke();
		System.out.println("After global invoking " + ai.getActionKey());
	}

}
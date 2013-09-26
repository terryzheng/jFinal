package com.zhaopin.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class cacheInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		System.out.println("Before controller invoking " + ai.getActionKey());
		ai.invoke();
		System.out.println("After controller invoking " + ai.getActionKey());
	}

}

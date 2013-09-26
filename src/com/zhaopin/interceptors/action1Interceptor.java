package com.zhaopin.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class action1Interceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		System.out.println("Before action1 invoking " + ai.getActionKey());
		ai.invoke();
		System.out.println("After action1 invoking " + ai.getActionKey());
	}

}

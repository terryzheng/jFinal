package com.zhaopin.interceptors;

import com.jfinal.aop.InterceptorStack;

public class actionsInterceptor extends InterceptorStack {
	public void config() {
		addInterceptors(new action1Interceptor(), new action2Interceptor());
	}
}

package com.zhaopin;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.zhaopin.controllers.CommonController;
import com.zhaopin.interceptors.globalInterceptor;
import com.zhaopin.models.Job;
import com.zhaopin.models.User;

public class IpinConfig extends JFinalConfig {

	public void configConstant(Constants constants) {
		loadPropertyFile("ipin_config.txt");
		constants.setDevMode(Boolean.parseBoolean(getProperty("devMode")));
		// constants.setViewType(ViewType.JSP);
	}

	public void configRoute(Routes routes) {
		routes.add("/", CommonController.class, "static");
	}

	public void configPlugin(Plugins plugins) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password"));
		plugins.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		plugins.add(arp);
		EhCachePlugin ecp = new EhCachePlugin();
		plugins.add(ecp);
		arp.addMapping("user_info", "usr_id", User.class);
		arp.addMapping("job_info", Job.class);
	}

	public void configInterceptor(Interceptors interceptors) {
		interceptors.add(new globalInterceptor());
	}

	public void configHandler(Handlers handlers) {
		// handlers.add(new ResourceHandler());
	}
}

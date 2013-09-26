package com.zhaopin.controllers;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.zhaopin.interceptors.action2Interceptor;
import com.zhaopin.interceptors.action1Interceptor;
import com.zhaopin.interceptors.actionsInterceptor;
import com.zhaopin.interceptors.controllerInterceptor;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.zhaopin.models.Job;
import com.zhaopin.models.User;

@Before(controllerInterceptor.class)
public class CommonController extends Controller {
	@ActionKey("/in")
	@Before({ actionsInterceptor.class, CacheInterceptor.class })
	@CacheName("sampleCache2")
	public void index() {
		Page<User> users = User.dao.paginate(getParaToInt(0, 1), 10,
				"select usr_name,usr_email", "from user_info where usr_id < ?",
				1000);
		List<User> ul = users.getList();
		setAttr("users", users);
		setAttr("ul", ul);

		System.out.println(users.getTotalPage());
		renderJsp("users.jsp");
		// render("users.html");
	}

	@ClearInterceptor
	@Before(Tx.class)
	public void hello() {
		// List<User> users = User.dao
		// .find("select usr_name,usr_email,corp_name from user_info where usr_id < 100");
		// renderJson("users", users);
		// User user = User.dao.findFirst(
		// "select * from user_info where usr_id=?", 105);
		// User user = User.dao.findById(1509);
		User user = User.dao
				.findFirst("select j.job_title,u.usr_id,u.usr_status,u.usr_name from user_info u inner join job_info j on u.usr_id=j.user_id order by j.id desc limit 1");
		Job job = Job.dao.findById(1000059);
		System.out.println(job.getStr("job_title"));
		job.set("job_title", "123").update();
		System.out.println(job.get("user_id"));
		System.out.println(user.get_job_list().size());
		System.out.println(job.get_user_name());
		user.set("usr_status", 3).update();
		renderText(user.getStr("job_title"));
	}

	@Before({ action1Interceptor.class, action2Interceptor.class })
	@ClearInterceptor(ClearLayer.ALL)
	public void add() {
		Enumeration<String> en = getParaNames();
		while (en.hasMoreElements()) {
			System.out.println(en.nextElement());
		}
		Map<String, String[]> map = getParaMap();
		Set<String> key = map.keySet();
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			System.out.println(s + ":" + map.get(s)[0]);
		}
		int ret = getParaToInt(0) + getParaToInt(1)
				+ getParaToInt("one111", 11) + getParaToInt("two");
		setAttr("ret", ret);
		renderJsp("test.jsp");
		// renderFile("/static/test.html");
		// renderJson(getParaToInt(0) + getParaToInt(1)
		// + getParaToInt("one111", 11) + getParaToInt("two"));
	}

	public void page() {
		List users = User.dao.get_users();
		renderJson("user", users);
	}

	@Before(EvictInterceptor.class)
	@CacheName("sampleCache2")
	public void clear() {

	}

	public void set() {
		CacheKit.put("sampleCache2", "value", getPara(0));
	}

	public void get() {
		renderText(CacheKit.get("sampleCache2", "value").toString());
	}
}

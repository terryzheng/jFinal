package com.zhaopin.models;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Job extends Model<Job> {
	public static final Job dao = new Job();

	public String get_user_name() {
		return User.dao.findById(get("user_id")).getStr("usr_name");
	}
}
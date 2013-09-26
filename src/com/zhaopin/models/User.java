package com.zhaopin.models;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class User extends Model<User> {
	public static final User dao = new User();

	public List<Job> get_job_list() {
		return Job.dao.find("select * from job_info where user_id=?",
				get("usr_id"));
	}

	public List<User> get_users() {
		// return User.dao
		// .find("SELECT usr_id,usr_name,usr_email FROM user_info ORDER BY usr_id DESC LIMIT 10");
		return User.dao
				.findByCache("users", "users1",
						"SELECT usr_id,usr_name,usr_email FROM user_info ORDER BY usr_id DESC LIMIT 10");
	}
}
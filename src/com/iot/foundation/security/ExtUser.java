package com.iot.foundation.security;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;

public class ExtUser extends User {
	private Integer id;
	private boolean blacklisted;

	public ExtUser(Integer id, String username, boolean enabled, boolean isBlacklisted) {
		super(username, "password", enabled, true, true, true, new ArrayList());
		this.id = id;
		this.blacklisted = isBlacklisted;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isBlacklisted() {
		return this.blacklisted;
	}

	public void setBlacklisted(boolean blacklisted) {
		this.blacklisted = blacklisted;
	}
}
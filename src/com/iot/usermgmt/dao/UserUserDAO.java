package com.iot.usermgmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.usermgmt.domain.UserUser;

public abstract interface UserUserDAO extends JpaRepository<UserUser, Integer>{

	//验证登录	
	public abstract List<UserUser> findByUsernameAndUserpass(String username, String userpass);
}

package com.iot.raspberry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.raspberry.dao.PiDAO;

//@Service
//@Transactional(rollbackFor = { Exception.class })
//@EnableJpaRepositories(basePackages = {"com.iot.raspberry.dao"})
public class PiserviceImpl implements PiService {

//	@Autowired
	private PiDAO piDAO;
}

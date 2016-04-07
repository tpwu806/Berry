package com.iot.supervise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.supervise.dao.SuperviseDAO;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.supervise.dao"})
public class SuperviseServiceImpl implements SuperviseService {

	@Autowired
	private SuperviseDAO superviseDAO;
}

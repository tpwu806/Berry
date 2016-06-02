package com.iot.threshold.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;
import com.iot.supervise.dao.SuperviseDAO;
import com.iot.supervise.dao.TaskDAO;
import com.iot.supervise.domain.Supervise;
import com.iot.supervise.dto.SuperviseDO;
import com.iot.threshold.dao.ThresholdDAO;
import com.iot.threshold.domain.Threshold;
import com.iot.threshold.dto.ThresholdDO;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.threshold.dao"})
public class ThresholdServiceImpl implements ThresholdService {
	static final Logger log = LoggerFactory.getLogger(ThresholdServiceImpl.class);

	@Autowired
	private ThresholdDAO thresholdDAO;

	@Override
	public ThresholdDO getThresholdDetail() throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Threshold updateDevice(ThresholdDO thresholdForm) throws DaoUpdateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteThreshold(Integer thresholdId) throws DaoDeleteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Threshold createThreshold(ThresholdDO thresholdForm) throws DaoFinderException, DaoCreateException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

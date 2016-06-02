package com.iot.threshold.service;

import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;
import com.iot.threshold.domain.Threshold;
import com.iot.threshold.dto.ThresholdDO;

public interface ThresholdService {
	
	public abstract ThresholdDO getThresholdDetail() throws DaoFinderException;

	public abstract Threshold updateDevice(ThresholdDO thresholdForm) throws DaoUpdateException;

	public abstract void deleteThreshold(Integer thresholdId)throws DaoDeleteException;

	public abstract Threshold createThreshold(ThresholdDO thresholdForm) throws DaoFinderException,DaoCreateException;
	
}

package com.iot.supervise.service;

import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.domain.Supervise;
import com.iot.supervise.dto.SuperviseDO;

public interface SuperviseService {
	
	public abstract SuperviseDO findMostNewSupervise() throws DaoFinderException;
	
	public abstract void creatSupervice(Supervise s) throws DaoFinderException;
	
}

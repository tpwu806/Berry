package com.iot.supervise.service;

import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.dto.SuperviseDO;

public interface SuperviseService {
	
	SuperviseDO findMostNewSupervise() throws DaoFinderException;
	
	void creatSupervice(SuperviseDO s) throws DaoFinderException,DaoCreateException;
	
}

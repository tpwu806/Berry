package com.iot.supervise.service;

import com.iot.exceptions.DaoFinderException;

public interface SensorService {

	public abstract Integer getTem() throws DaoFinderException;
}

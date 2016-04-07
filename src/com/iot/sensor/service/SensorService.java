package com.iot.sensor.service;

import com.iot.exceptions.DaoFinderException;

public interface SensorService {

	public abstract Integer getTem() throws DaoFinderException;
}

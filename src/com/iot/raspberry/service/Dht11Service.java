package com.iot.raspberry.service;

import com.iot.exceptions.DaoFinderException;
import com.iot.raspberry.dto.Dht11DO;

public interface Dht11Service {

	public abstract Integer getTem() throws DaoFinderException;
}

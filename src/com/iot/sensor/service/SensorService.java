package com.iot.sensor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;
import com.iot.sensor.domain.Sensor;
import com.iot.sensor.dto.SensorDO;

public interface SensorService {

	public abstract Integer getTem() throws DaoFinderException;
	
	public abstract Page<SensorDO> retrieveAllSensor(Pageable pgble , Integer deviceid) throws DaoFinderException;

	public abstract Sensor createSensor(SensorDO sensorForm) throws DaoCreateException;

	public abstract Page<SensorDO> searchSensorContent(
			String searchTerm, String receiptState, Pageable pgble) throws DaoFinderException;

	public abstract SensorDO getSensorDetailById(Integer sensorId, String username) throws DaoFinderException;

	public abstract Sensor updateSensor(SensorDO sensorForm) throws DaoUpdateException;

	public abstract void deleteSensor(Integer sensorId)throws DaoDeleteException;
	
	public abstract Integer getNumSearchSensor(String name,String receiptsign)throws DaoFinderException;
}

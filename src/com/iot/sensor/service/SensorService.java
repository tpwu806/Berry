package com.iot.sensor.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;
import com.iot.sensor.domain.Sensor;
import com.iot.sensor.dto.SensorDO;

public interface SensorService {
	
	Page<SensorDO> retrieveAllSensor(Integer deviceid , Pageable pgble) throws DaoFinderException;

	Sensor createSensor(SensorDO sensorForm) throws DaoCreateException;

	Page<SensorDO> searchSensorContent(
			String searchTerm, String receiptState, Pageable pgble) throws DaoFinderException;

	SensorDO getSensorDetailById(Integer sensorId, String username) throws DaoFinderException;

	Sensor updateSensor(SensorDO sensorForm) throws DaoUpdateException;

	void deleteSensor(Integer sensorId)throws DaoDeleteException;
	
	Integer getNumSearchSensor(String name,String receiptsign)throws DaoFinderException;

}

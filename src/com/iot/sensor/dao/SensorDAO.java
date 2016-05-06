package com.iot.sensor.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iot.sensor.domain.Sensor;
import com.iot.sensor.dto.SensorDO;

public interface SensorDAO extends JpaRepository<Sensor, Integer>{
	
	@Query("select s from Sensor s where s.deviceid = :deviceid ") 
	public abstract Page<Sensor> findByDeviceid(@Param("deviceid")Integer deviceid, Pageable pgble);

}

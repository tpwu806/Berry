package com.iot.sensor.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iot.sensor.domain.Sensor;

public interface SensorDAO extends JpaRepository<Sensor, Integer>{
	
	/*@Query("select s from Sensor s where s.deviceid = :deviceid order by postdate desc") 
	public abstract Page<Sensor> findByDeviceidOrderByPostdateDesc(@Param("deviceid")Integer deviceid, Pageable paramPageable);*/
	
	
	public abstract Page<Sensor> findByDeviceidOrderByPostdateDesc(Integer deviceid, Pageable paramPageable);
}

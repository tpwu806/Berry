package com.iot.device.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iot.device.domain.Device;



public interface DeviceDAO extends JpaRepository<Device, Integer>{
	@Query("select d from Device d where d.devicestatus = :devicestatus ") 
	public abstract Device findByDeviceStatus(@Param("devicestatus")Integer devicestatus);
}

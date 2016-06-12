package com.iot.device.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iot.device.domain.Device;

public interface DeviceDAO extends JpaRepository<Device, Integer>{
	@Query("select d from Device d where d.devicestatus = :devicestatus ") 
	public abstract Device findByDeviceStatus(@Param("devicestatus")Integer devicestatus);

	@Query("select d from Device d where d.devicetype = :devicetype ")
	public abstract Page<Device> findByDeviceType(@Param("devicetype")String devicetype, Pageable pgble);

	@Query("select d from Device d where d.devicename like :devicename escape '/' ") 
	public abstract Page<Device> findByDeviceName(@Param("devicename")String devicename, Pageable pgble);
	
	@Query("select d from Device d where d.devicename like :devicename escape '/' and d.devicetype = :devicetype ") 
	public abstract Page<Device> findByDeviceNameAndDeviceType(@Param("devicename")String devicename, @Param("devicetype")String devicetype, Pageable pgble);
}

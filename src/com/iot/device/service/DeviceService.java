package com.iot.device.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iot.device.domain.Device;
import com.iot.device.dto.DeviceDO;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;


public interface DeviceService {

	Page<DeviceDO> retrieveAllDevice(Pageable pgble) throws DaoFinderException;

	Device createDevice(DeviceDO deviceForm) throws DaoCreateException;

	Page<DeviceDO> searchDeviceContent(String searchTerm,String receiptState, 
			Pageable pgble) throws DaoFinderException;

	DeviceDO getDeviceDetailById(Integer deviceId) throws DaoFinderException;

	Device updateDevice(DeviceDO deviceForm) throws DaoUpdateException;

	void deleteDevice(Integer deviceId)throws DaoDeleteException;
	
	Device startDevice(Integer deviceId) throws DaoUpdateException;
	
	Device stopDevice(Integer deviceId) throws DaoUpdateException;
	
	void setAliveDevice() throws DaoUpdateException;
	
	DeviceDO findAliveDevice() throws DaoUpdateException, DaoFinderException;
	
}

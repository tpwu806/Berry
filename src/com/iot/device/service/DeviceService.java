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

	public abstract Page<DeviceDO> retrieveAllDevice(Pageable pgble) throws DaoFinderException;

	public abstract Device createDevice(DeviceDO deviceForm) throws DaoCreateException;

	public abstract Page<DeviceDO> searchNoticeContent(
			String searchTerm, String receiptState, Pageable pgble) throws DaoFinderException;

	public abstract DeviceDO getDeviceDetailById(Integer deviceId, String username) throws DaoFinderException;

	public abstract Device updateDevice(DeviceDO deviceForm) throws DaoUpdateException;

	public abstract void deleteDevice(Integer deviceId)throws DaoDeleteException;
	
	public abstract Integer getNumSearchDevice(String name,String receiptsign)throws DaoFinderException;
	
}

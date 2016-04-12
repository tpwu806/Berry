package com.iot.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.device.dao.DeviceDAO;
import com.iot.device.dao.DeviceTypeDAO;
import com.iot.device.domain.Device;
import com.iot.device.dto.DeviceDO;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.device.dao"})
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceDAO deviceDAO;
	
	@Autowired
	private DeviceTypeDAO deviceTypeDAO;

	@Override
	public Page<DeviceDO> retrieveAllDevice(Pageable pgble) throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Device createDevice(DeviceDO deviceForm) throws DaoCreateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DeviceDO> searchNoticeContent(String searchTerm, String receiptState, Pageable pgble)
			throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceDO getDeviceDetailById(Integer deviceId, String username) throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Device updateDevice(DeviceDO deviceForm) throws DaoUpdateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDevice(Integer deviceId) throws DaoDeleteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getNumSearchDevice(String name, String receiptsign) throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}
}

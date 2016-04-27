package com.iot.device.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

	static final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);
	@Autowired
	private DeviceDAO deviceDAO;
	
	@Autowired
	private DeviceTypeDAO deviceTypeDAO;

	@Override
	public Page<DeviceDO> retrieveAllDevice(Pageable pgble) throws DaoFinderException {
		Page<DeviceDO> results = null;
		try {
			Page<Device> page = null;
			
			page = this.deviceDAO.findAll(pgble);
			
			ArrayList<DeviceDO> list = new ArrayList<DeviceDO>();
			if ((page != null) && (page.hasContent())) {
				for (Device device : page.getContent()) {
					DeviceDO dd = new DeviceDO();
					
					dd.setId(device.getId());
					dd.setDevicename(device.getDevicename());
					dd.setDeviceip(device.getDeviceip());
					dd.setDeviceport(device.getDeviceport());
					
					if(device.getSensornumber()==null){
						dd.setSensornumber(0);
					}else{
						dd.setSensornumber(device.getSensornumber());
					}
					
					dd.setDevicestatus(device.getDevicestatus());
					dd.setDevicetype(device.getDevicetype());

					list.add(dd);
				}
			}
			return new PageImpl(list, pgble, page.getTotalElements());
		} catch (Exception ex) {
			log.debug("Error retrieving notice for user", ex);
			throw new DaoFinderException(ex.getMessage());
		}
	}

	@Override
	public Device createDevice(DeviceDO deviceForm) throws DaoCreateException {
		Device device = null;
		try {
			device = new Device();//deviceForm
			device.setDevicename(deviceForm.getDevicename());
			device.setDeviceip(deviceForm.getDeviceip());
			device.setDeviceport(deviceForm.getDeviceport());
			device.setDevicetype(deviceForm.getDevicetype());
			
			device.setDevicestatus("stop");
			device.setSensornumber(0);

			return (Device) this.deviceDAO.save(device);
		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoCreateException(ex.getMessage());
		}
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

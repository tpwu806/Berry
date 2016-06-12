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
import org.springframework.util.StringUtils;

import com.iot.common.dao.DeviceJdbcDAO;
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
@EnableJpaRepositories(basePackages = {"com.iot.device.dao","com.iot.common.dao"})
public class DeviceServiceImpl implements DeviceService {

	static final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);
	@Autowired
	private DeviceDAO deviceDAO;	
	@Autowired
	private DeviceTypeDAO deviceTypeDAO;	
	@Autowired
	private DeviceJdbcDAO deviceJdbcDAO;

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
					
					Integer[] s=new Integer[1];
					s[0]=device.getDevicestatus();
					dd.setStatus(s);
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
			
			if ((deviceForm.getStatus() != null) && (deviceForm.getStatus().length > 0)) {
				device.setDevicestatus(deviceForm.getStatus()[0]);
			} else {
				device.setDevicestatus(Integer.valueOf(0));
			}
			device.setSensornumber(0);

			return (Device) this.deviceDAO.save(device);
		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoCreateException(ex.getMessage());
		}
	}

	@Override
	public DeviceDO getDeviceDetailById(Integer deviceId) throws DaoFinderException {
		DeviceDO fh = null;
		try {
			Device device = (Device) this.deviceDAO.findOne(deviceId);
			if (device != null) {
				fh = new DeviceDO();
	
				fh.setId(device.getId());
				fh.setDevicename(device.getDevicename());
				Integer[] s=new Integer[1];
				s[0]=device.getDevicestatus();
				fh.setStatus(s);
				fh.setDevicetype(device.getDevicetype());
				fh.setDeviceip(device.getDeviceip());
				fh.setDeviceport(device.getDeviceport());
				fh.setSensornumber(device.getSensornumber());
	
			}
			return fh;
		} catch (Exception ex) {
			log.debug("Error retrieving device article: " , ex);
			throw new DaoFinderException(ex.getMessage());
		}
	}

	@Override
	public Page<DeviceDO> searchDeviceContent(String searchTerm, String type, Pageable pgble)
			throws DaoFinderException {		
		Page<Device> page=null;
		try {
			System.out.println("searchTerm:"+searchTerm+" type:"+type);
			if(StringUtils.isEmpty(searchTerm)){
				if(StringUtils.isEmpty(type)){
					page=this.deviceDAO.findAll(pgble);
				}else{
					page=this.deviceDAO.findByDeviceType(type,pgble);
				}				
			}else if(StringUtils.isEmpty(type)){				
				page=this.deviceDAO.findByDeviceName(searchTerm,pgble);				
			}else{
				page=this.deviceDAO.findByDeviceNameAndDeviceType(searchTerm,type,pgble);
			}
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
					
					Integer[] s=new Integer[1];
					s[0]=device.getDevicestatus();
					dd.setStatus(s);
					dd.setDevicetype(device.getDevicetype());

					list.add(dd);
				}
			}
			return new PageImpl<DeviceDO>(list, pgble, page.getTotalElements());

		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoFinderException(ex.getMessage());
		}
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
	public Device startDevice(Integer deviceId) throws DaoUpdateException {
		Device fh = null;
		try {
			fh = (Device) this.deviceDAO.findOne(deviceId);
			if (fh != null) {				
				fh.setDevicestatus(Integer.valueOf(1));				
				fh = (Device) this.deviceDAO.save(fh);
			} else {
				throw new Exception("Cannot find device to update");
			}
			return fh;
		} catch (Exception ex) {
			log.debug("Cannot start device", ex);
			throw new DaoUpdateException(ex.getMessage());
		}
	}

	@Override
	public Device stopDevice(Integer deviceId) throws DaoUpdateException {
		Device fh = null;
		try {
			fh = (Device) this.deviceDAO.findOne(deviceId);
			if (fh != null) {				
				fh.setDevicestatus(Integer.valueOf(0));				
				fh = (Device) this.deviceDAO.save(fh);
			} else {
				throw new Exception("Cannot find device to update");
			}
			return fh;
		} catch (Exception ex) {
			log.debug("Cannot stop device", ex);
			throw new DaoUpdateException(ex.getMessage());
		}
	}

	@Override
	public void setAliveDevice() throws DaoUpdateException {
		try {
			Integer devicetatus=1;
			Device device = (Device) this.deviceDAO.findOne(devicetatus);
			if (device != null) {
				device.setDevicestatus(0);
			}
			this.deviceDAO.save(device);
		} catch (Exception ex) {
			log.debug("Error retrieving device article: " , ex);
			try {
				throw new DaoFinderException(ex.getMessage());
			} catch (DaoFinderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}

	@Override
	public DeviceDO findAliveDevice() throws DaoUpdateException, DaoFinderException {
		DeviceDO fh = null;
		try {
			Integer devicestatus=1;
			Device device = (Device) this.deviceDAO.findByDeviceStatus(devicestatus);
			if (device != null) {
				fh = new DeviceDO();
	
				fh.setId(device.getId());
				fh.setDevicename(device.getDevicename());
				Integer[] s=new Integer[1];
				s[0]=device.getDevicestatus();
				fh.setStatus(s);
				fh.setDevicetype(device.getDevicetype());
				fh.setDeviceip(device.getDeviceip());
				fh.setDeviceport(device.getDeviceport());
				fh.setSensornumber(device.getSensornumber());
	
			}
			return fh;
		} catch (Exception ex) {
			log.debug("Error retrieving device article: " , ex);
			throw new DaoFinderException(ex.getMessage());
		}
	}
}

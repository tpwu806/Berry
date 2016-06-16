package com.iot.sensor.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.device.domain.Device;
import com.iot.device.dto.DeviceDO;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;
import com.iot.sensor.dao.SensorDAO;
import com.iot.sensor.dao.SensorTypeDAO;
import com.iot.sensor.domain.Sensor;
import com.iot.sensor.dto.SensorDO;
import com.iot.usermgmt.service.UserServiceImpl;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.sensor.dao"})
public class SensorServiceImpl implements SensorService {

static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private SensorDAO sensorDAO;
	
	@Autowired
	private SensorTypeDAO sensorTypeDAO;

	@Override
	public Page<SensorDO> retrieveAllSensor(Integer deviceid, Pageable pgble) throws DaoFinderException {
		try {
			Page<Sensor> page = null;			
			page = this.sensorDAO.findByDeviceid(deviceid, pgble);
			
			ArrayList<SensorDO> list = new ArrayList<SensorDO>();
			if ((page != null) && (page.hasContent())) {
				for (Sensor sensor : page.getContent()) {
					SensorDO dd = new SensorDO();
					
					dd.setId(sensor.getId());
					dd.setSensorname(sensor.getSensorname());
					dd.setSensortype(sensor.getSensortype());					
					dd.setSensorparameter(sensor.getSensorparameter());
					dd.setSensorparameter2(sensor.getSensorparameter2());
					dd.setDeviceid(sensor.getDeviceid());

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
	public Sensor createSensor(SensorDO sensorForm) throws DaoCreateException {
		Sensor s=null;
		try {
			s = new Sensor();//sensorForm
			s.setDeviceid(sensorForm.getDeviceid());
			s.setSensorname(sensorForm.getSensorname());
			s.setSensortype(sensorForm.getSensortype());
			//s.setSensorparameter(sensorForm.getSensorparameter());
			s.setSensorparameter("1");
			s.setSensorparameter("2");
			//s.setSensorparameter2(sensorForm.getSensorparameter2());

			return (Sensor) this.sensorDAO.save(s);
		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoCreateException(ex.getMessage());
		}
	}

	@Override
	public Page<SensorDO> searchSensorContent(String searchTerm, String receiptState, Pageable pgble)
			throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorDO getSensorDetailById(Integer sensorId, String username) throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor updateSensor(SensorDO sensorForm) throws DaoUpdateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSensor(Integer sensorId) throws DaoDeleteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getNumSearchSensor(String name, String receiptsign) throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	
}

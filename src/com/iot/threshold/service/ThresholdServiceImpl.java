package com.iot.threshold.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;
import com.iot.threshold.dao.ThresholdDAO;
import com.iot.threshold.domain.Threshold;
import com.iot.threshold.dto.ThresholdDO;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.threshold.dao"})
public class ThresholdServiceImpl implements ThresholdService {
	static final Logger log = LoggerFactory.getLogger(ThresholdServiceImpl.class);

	@Autowired
	private ThresholdDAO thresholdDAO;

	@Override
	public ThresholdDO getThresholdDetail() throws DaoFinderException {
		ThresholdDO thresholdDO=new ThresholdDO();
		try{
			Threshold threshold=this.thresholdDAO.findOne(Integer.valueOf(1));
			if(threshold!=null){
				thresholdDO.setId(threshold.getId());
				thresholdDO.setMaxhumidity(threshold.getMaxhumidity());
				thresholdDO.setMaxtemperature(threshold.getMaxtemperature());
				thresholdDO.setMinhumidity(threshold.getMinhumidity());
				thresholdDO.setMintemperature(threshold.getMintemperature());
			}	
		} catch (Exception ex) {
			log.debug("Error creating new supervice", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		return thresholdDO;
	}

	@Override
	public Threshold updateDevice(ThresholdDO thresholdForm) throws DaoUpdateException {
		Threshold ts=null;
		try{
			Threshold threshold=this.thresholdDAO.findOne(Integer.valueOf(1));
			if(threshold!=null){				
				//threshold.setId(threshold.getId());
				threshold.setMaxhumidity(thresholdForm.getMaxhumidity());
				threshold.setMaxtemperature(thresholdForm.getMaxtemperature());
				threshold.setMinhumidity(thresholdForm.getMinhumidity());
				threshold.setMintemperature(thresholdForm.getMintemperature());
				ts=this.thresholdDAO.save(threshold);
			}	
		} catch (Exception ex) {
			log.debug("Error creating new supervice", ex);
			throw new DaoUpdateException(ex.getMessage());
		}
		return ts;
	}

	@Override
	public Threshold createThreshold(ThresholdDO thresholdForm) throws DaoFinderException, DaoCreateException {		
		Threshold ts=null;
		try {
			Threshold threshold=new Threshold();
			threshold.setMaxhumidity(thresholdForm.getMaxhumidity());
			threshold.setMaxtemperature(thresholdForm.getMaxtemperature());
			threshold.setMinhumidity(thresholdForm.getMinhumidity());
			threshold.setMintemperature(thresholdForm.getMintemperature());
			ts=this.thresholdDAO.save(threshold);
		} catch (Exception ex) {
			log.debug("Error creating new supervice", ex);
			throw new DaoCreateException(ex.getMessage());
		}
		return ts;
	}

	@Override
	public void deleteThreshold(Integer thresholdId) throws DaoDeleteException {
		// TODO Auto-generated method stub
		
	}
	
	
}

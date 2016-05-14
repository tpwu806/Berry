package com.iot.supervise.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.dao.SuperviseDAO;
import com.iot.supervise.dao.TaskDAO;
import com.iot.supervise.domain.Supervise;
import com.iot.supervise.dto.SuperviseDO;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.supervise.dao"})
public class SuperviseServiceImpl implements SuperviseService {
	static final Logger log = LoggerFactory.getLogger(SuperviseServiceImpl.class);

	@Autowired
	private SuperviseDAO superviseDAO;
	
	@Autowired
	private TaskDAO taskDAO;

	@Override
	public SuperviseDO findMostNewSupervise() throws DaoFinderException {
		SuperviseDO fh=null;
		fh=new SuperviseDO();
		List<Supervise> list=this.superviseDAO.findOrderBySupervisetimeDesc();
		Supervise s=list.get(0);
		if(list!=null&&s!=null){
			fh.setTaskid(s.getTaskid());
			fh.setSensorvalue(s.getSensorvalue());
			fh.setSupervisetime(s.getSupervisetime());
			fh.setSensorvalue2(s.getSensorvalue2());
			fh.setWarningclass(s.getWarningclass());
		}
		
		
		return fh;
	}

	@Override
	public void creatSupervice(SuperviseDO s) throws DaoFinderException, DaoCreateException {
		Supervise ss = null;
		try {
			ss = new Supervise();
			ss.setTaskid(s.getTaskid());
            ss.setSensorvalue(s.getSensorvalue());
            ss.setSensorvalue2(s.getSensorvalue2());
            ss.setSupervisetime(s.getSupervisetime());
            ss.setWarningclass(s.getWarningclass());

			this.superviseDAO.save(ss);
		} catch (Exception ex) {
			log.debug("Error creating new supervice", ex);
			throw new DaoCreateException(ex.getMessage());
		}
		
	}
	
	
}

package com.iot.supervise.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.common.utilities.TimeDateUtility;
import com.iot.device.domain.Device;
import com.iot.device.dto.DeviceDO;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.dao.SuperviseDAO;
import com.iot.supervise.dao.TaskDAO;
import com.iot.supervise.domain.Task;
import com.iot.supervise.dto.TaskDO;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.supervise.dao"})
public class TaskServiceImpl implements TaskService {
	static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Autowired
	private SuperviseDAO superviseDAO;	
	@Autowired
	private TaskDAO taskDAO;
	
	@Override
	public Task startTask(DeviceDO device) throws DaoCreateException {
		Task task = null;
		try {
			task = new Task();
			task.setDeviceid(device.getId());
			task.setStarttime(TimeDateUtility.getCurrentTimestamp());
			task.setTaskstatus(1);					

			return (Task) this.taskDAO.save(task);
		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoCreateException(ex.getMessage());
		}
	}

	@Override
	public Task stopTask(TaskDO t) throws DaoCreateException {
		Task task = null;
		try {
			task=this.taskDAO.findOne(t.getId());
			task.setStoptime(TimeDateUtility.getCurrentTimestamp());
			task.setTaskstatus(0);					

			return (Task) this.taskDAO.save(task);
		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoCreateException(ex.getMessage());
		}
	}

	@Override
	public TaskDO findAliveTask() throws DaoFinderException {
		TaskDO fh=null;
		try {
			Integer taskstatus=1;
			Task task = (Task) this.taskDAO.findByTaskStatus(taskstatus);
			if (task != null) {
				fh = new TaskDO();
	
				fh.setId(task.getId());
				fh.setDeviceid(task.getDeviceid());
				fh.setStarttime(task.getStarttime());
	
			}
			return fh;
		} catch (Exception ex) {
			log.debug("Error retrieving device article: " , ex);
			throw new DaoFinderException(ex.getMessage());
		}
	}

	@Override
	public void setAliveTask() throws DaoFinderException {		
		try {
			Integer taskstatus=1;
			Task task = (Task) this.taskDAO.findByTaskStatus(taskstatus);
			if (task != null) {
				task.setTaskstatus(0);
			}
			this.taskDAO.save(task);
		} catch (Exception ex) {
			log.debug("Error retrieving device article: " , ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}

	@Override
	public boolean findTaskByDeviceId(Integer deviceid) throws DaoFinderException {
		try {
			Task task = (Task) this.taskDAO.findByDeviceId(deviceid);
			if (task != null) {
				if("0".equals(task.getTaskstatus())){
					return true;
				}else{return false;}
			}else{return false;}
			
		} catch (Exception ex) {
			log.debug("Error retrieving device article: " , ex);
			throw new DaoFinderException(ex.getMessage());
		}
	}

	@Override
	public boolean findTask() throws DaoFinderException {
		boolean b=false;
		try {
			List<Task> tasks = (List<Task>) this.taskDAO.findTask();
			if (tasks != null && tasks.size()>0) {
				System.out.println(tasks.get(0).getStarttime());
				if(0 == (tasks.get(0).getTaskstatus())){
					b=true;
				}
			}
			return b;
			
		} catch (Exception ex) {
			log.debug("Error retrieving device article: " , ex);
			throw new DaoFinderException(ex.getMessage());
		}
	}

}

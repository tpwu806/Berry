package com.iot.supervise.service;

import com.iot.device.dto.DeviceDO;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.domain.Task;
import com.iot.supervise.dto.TaskDO;

public interface TaskService {

	public abstract Task startTask(DeviceDO device) throws DaoCreateException;
	
	public abstract Task stopTask(TaskDO task) throws DaoCreateException;
	
	public abstract TaskDO findAliveTask() throws DaoFinderException;
	
	public abstract void setAliveTask() throws DaoFinderException;
	
	public abstract boolean findTaskByDeviceId(Integer id) throws DaoFinderException;
	
	public abstract boolean findTask() throws DaoFinderException;
}

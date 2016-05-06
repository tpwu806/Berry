package com.iot.supervise.service;


import com.iot.device.domain.Device;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.domain.Task;
import com.iot.supervise.dto.TaskDO;

public interface TaskService {

	public abstract Task startTask(Device device) throws DaoCreateException;
	
	public abstract Task stopTask(Device device) throws DaoCreateException;
	
	public abstract TaskDO findAliveTask() throws DaoFinderException;
}

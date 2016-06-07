package com.iot.supervise.service;

import com.iot.device.dto.DeviceDO;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.domain.Task;
import com.iot.supervise.dto.TaskDO;

public interface TaskService {

	Task startTask(DeviceDO device) throws DaoCreateException;
	
	Task stopTask(TaskDO task) throws DaoCreateException;
	
	TaskDO findAliveTask() throws DaoFinderException;
	
	void setAliveTask() throws DaoFinderException;
	
	boolean findTaskByDeviceId(Integer id) throws DaoFinderException;
	
	boolean findTask() throws DaoFinderException;
}

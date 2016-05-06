package com.iot.supervise.service;


import com.iot.device.domain.Device;
import com.iot.exceptions.DaoCreateException;
import com.iot.supervise.domain.Task;

public interface TaskService {

	public abstract Task startTask(Device device) throws DaoCreateException;
	
	public abstract Task stopTask(Device device) throws DaoCreateException;
}

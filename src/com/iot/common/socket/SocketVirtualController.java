package com.iot.common.socket;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.iot.device.service.DeviceService;
import com.iot.sensor.service.SensorService;

/**
 * 为task提供从数据库中获得数据
 * */
public class SocketVirtualController{
	private static Logger LOG = LoggerFactory.getLogger(SocketVirtualController.class);
	@Resource
	private Environment env;	
	@Autowired
	private SensorService sensorService;	
	@Autowired
	private DeviceService deviceService;
	
	private boolean cmdstatus=true;//指令状态,ture开启 false关闭
	
	public boolean isCmdstatus() {
		return cmdstatus;
	}
	public void setCmdstatus(boolean cmdstatus) {
		this.cmdstatus = cmdstatus;
	}
}

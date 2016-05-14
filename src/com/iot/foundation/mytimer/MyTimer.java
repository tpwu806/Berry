package com.iot.foundation.mytimer;

import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.iot.common.socket.task.MyTask;
import com.iot.device.service.DeviceService;
import com.iot.sensor.service.SensorService;
import com.iot.supervise.dao.SuperviseDAO;
import com.iot.supervise.service.SuperviseService;
import com.iot.supervise.service.TaskService;

/**
 * 开启一个任务
 * */
@Component
public class MyTimer {
	private static Logger LOG = LoggerFactory.getLogger(MyTimer.class);
	@Autowired
	private SuperviseService superviseService;
	@Autowired
	private SensorService sensorService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private SuperviseDAO superviseDAO;
	public MyTimer(){
		System.out.println("MyTimer");
		//Timer timer = new Timer();		
		//timer.schedule(new MyTask(superviseDAO), 1000 ,5000);
	}
	
	/*public static void main(String[] args) {
	Timer timer = new Timer();		
	timer.schedule(new MyTask(), 1000 ,5000);
    }*/
}

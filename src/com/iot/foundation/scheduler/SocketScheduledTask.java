package com.iot.foundation.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iot.common.socket.task.SocketUtilities;
import com.iot.device.dto.DeviceDO;
import com.iot.device.service.DeviceService;
import com.iot.supervise.dao.SuperviseDAO;
import com.iot.supervise.dto.TaskDO;
import com.iot.supervise.service.TaskService;
import com.iot.threshold.dto.ThresholdDO;
import com.iot.threshold.service.ThresholdService;

/**
 * 任务调度器
 * by wutongpeng 20160514
 * */
@Component
public class SocketScheduledTask {
	static final Logger log = LoggerFactory.getLogger(SocketScheduledTask.class);
	
	@Autowired
	private SuperviseDAO superviseDAO;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ThresholdService thresholdService;
	private boolean fiststart=true;
	@Scheduled(cron="0/10 * *  * * ? ")//每5秒执行一次
	private void ScheduledTask () throws Exception {
		/**
		 * 初始化数据库设备状态和任务状态
		 * */
		System.out.println("执行ScheduledTask");
		if(fiststart){
			System.out.println("初始化数据库");
			fiststart=false;
			this.deviceService.setAliveDevice();
			this.taskService.setAliveTask();						
		}else{	
			System.out.println("判断设备状态");
			DeviceDO dd=this.deviceService.findAliveDevice();
			ThresholdDO thresholdDO=this.thresholdService.getThresholdDetail();
            if(dd!=null){
            	boolean b=this.taskService.findTask();
            	if(b){
            		System.out.println("连接树莓派");
            		taskService.startTask(dd);
            		SocketUtilities.startThread(dd,superviseDAO,thresholdDO);
            		
            	}
            }else{
            	TaskDO task=this.taskService.findAliveTask();
            	if(task!=null){
            		SocketUtilities.stopThread();
            		taskService.stopTask(task);
            	}
            }
		}
		
		
	}
}

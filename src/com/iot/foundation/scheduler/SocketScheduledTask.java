package com.iot.foundation.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iot.supervise.dao.SuperviseDAO;

/**
 * 任务调度器
 * by wutongpeng 20160514
 * */
@Component
public class SocketScheduledTask {
	static final Logger log = LoggerFactory.getLogger(SocketScheduledTask.class);
	
	@Autowired
	private SuperviseDAO superviseDAO;
	
	@Scheduled(cron="0/5 * *  * * ? ")//每5秒执行一次
	private void ScheduledTask () throws Exception {	
		System.out.println("Hello");
	}
}

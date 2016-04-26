package com.iot.foundation.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SocketScheduledTask {
	static final Logger log = LoggerFactory.getLogger(SocketScheduledTask.class);
	
	//@Autowired

	
	
	/*@Scheduled(cron = "1 0 0 * * ? ")
	private void unBlacklistUser () throws Exception {
		System.out.println("Hello");
	}*/
}

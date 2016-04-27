package com.iot.common.socket;

import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.iot.common.socket.task.MyTask;

/**
 * 开启一个任务
 * */
//@Component
public class MyTimer {
	private static Logger LOG = LoggerFactory.getLogger(MyTimer.class);
	public MyTimer(){
		System.out.println("MyTimer");
		Timer timer = new Timer();		
		timer.schedule(new MyTask(), 1000 ,5000);
	}
	
	/*public static void main(String[] args) {
	Timer timer = new Timer();		
	timer.schedule(new MyTask(), 1000 ,5000);
    }*/
}

package com.iot.common.socket;

import java.util.Timer;

import org.springframework.stereotype.Component;

import com.iot.common.socket.task.MyTask;

/**
 * 开启一个任务
 * */
@Component
public class M {
//	public static void main(String[] args) {
	public M(){
		System.out.println("M");
		Timer timer = new Timer();		
		timer.schedule(new MyTask(), 1000 ,5000);
	}
}

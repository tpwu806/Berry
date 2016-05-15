package com.iot.common.socket.task;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iot.common.socket.thread.MyThread;
import com.iot.device.dto.DeviceDO;
import com.iot.supervise.dao.SuperviseDAO;

public class SocketUtilities {

	private static Logger LOG = LoggerFactory.getLogger(SocketUtilities.class);
	
	static MyThread mt;
	static Socket socket;
	static SuperviseDAO superviseDAO;
	
	/**
	 * 开启线程
	 * */
	public static void startThread(DeviceDO dd,SuperviseDAO superviseDAO){
		String IP=dd.getDeviceip(); 
		int PORT = Integer.valueOf(dd.getDeviceport());
		SocketUtilities.superviseDAO=superviseDAO;
		try {			
			socket = new Socket(IP, PORT);
			mt=new MyThread(socket,superviseDAO);
			mt.start();
		}catch(Exception e){
            e.printStackTrace();
		}
		
	}
	
	/**
	 * 关闭线程
	 * */
	public static void stopThread(){
		mt.setStatus(false);
	}
}

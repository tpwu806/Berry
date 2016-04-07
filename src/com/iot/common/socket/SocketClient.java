package com.iot.common.socket;

import java.io.IOException;
import java.net.Socket;

import org.springframework.stereotype.Component;

//@Component
public class SocketClient {
	static Socket socket;
	
	public static void SocketClient() throws IOException {
	   
		String IP="172.16.8.109";
		//String IP="localhost"; 
		int PORT = 6789;
		try {
			
			socket = new Socket(IP, PORT);
			Thread workThread=new Thread(new SocketThread(socket));    //创建线程
            workThread.start();
		}catch(Exception e){
            e.printStackTrace();
		}
	}
	
	
}

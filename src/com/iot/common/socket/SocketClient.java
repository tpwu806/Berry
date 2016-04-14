package com.iot.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.springframework.stereotype.Component;

import com.iot.common.SocketThread;

@Component
public class SocketClient {
	static Socket socket;
	
	public SocketClient() throws IOException {
		socket = new Socket(InetAddress.getLocalHost(), 6789);
		   BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		   PrintWriter send = new PrintWriter(socket.getOutputStream());
		   BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

		   while (true) {
		    String str = wt.readLine();
		    send.println(str);
		    send.flush();
		     
		    System.out.println("send ok");
		    if (str.equals("end")){
		      break;
		    }
		    String tem=receive.readLine();
		    System.out.println(tem);

		   }
		   socket.close();
		   
		   
		  /* String IP="172.16.8.109";
			//String IP="localhost"; 
			int PORT = 6789;
			try {
				
				socket = new Socket(IP, PORT);
				Thread workThread=new Thread(new SocketThread(socket));    //创建线程
	            workThread.start();
			}catch(Exception e){
	            e.printStackTrace();
			}*/
	}
	
	
}

package com.iot.common.socket.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * socket线程
 * */
public class MyThread extends Thread{
	private volatile boolean status;//设备状态,ture;正在运行  false：已经关闭	
	private Socket socket;
	
    public MyThread(Socket socket){
        this.socket=socket;
    }
	public  boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void run() {
		BufferedReader receive = null;
		PrintWriter send = null;
		String tem = null;
		String cmd="GET";
		
		try{
			receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    send = new PrintWriter(socket.getOutputStream());
		    
		    while(status){  
		        send.println(cmd);  
                send.flush();
		    	tem = receive.readLine();  
                if(tem.equals("END")){  
                    break;  
                }                 
                System.out.println("Client Socket Message:"+tem);  
                Thread.sleep(50);  
                  
            }
            
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
                System.out.println("关闭连接:"+socket.getInetAddress()+":"+socket.getPort());
                if(socket!=null){
                	receive.close();
                	send.close();
                	socket.close();
                }               	
            }catch(IOException e){
                e.printStackTrace();
                System.out.println(e);
            }
		}
					
	}
	
}

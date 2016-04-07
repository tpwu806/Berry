package com.iot.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketThread implements Runnable{
	
	private Socket socket;
    public SocketThread(Socket socket){
        this.socket=socket;
    }
	@Override
	public void run() {
		BufferedReader receive = null;
		PrintWriter send = null;
		String tem = null;
		String cmd="GET";
		try{
			receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    send = new PrintWriter(socket.getOutputStream());
		    
		    while(true){  
		        send.println(cmd);  
                send.flush();
		    	tem = receive.readLine();  
                if(tem.equals("END")){  
                    break;  
                }                 
                System.out.println("Client Socket Message:"+tem);  
                Thread.sleep(2000);  
                  
            }
		    
//		    for (int i = 0; i < 10; i++) {  
//		    	send.println(cmd + i);  
//		    	send.flush();  
//		    	tem = receive.readLine();  
//                System.out.println(tem);  
//            }  
//		    send.println("END");  
//		    send.flush();  
            
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

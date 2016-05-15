package com.iot.common.socket.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iot.common.utilities.TimeDateUtility;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.dao.SuperviseDAO;
import com.iot.supervise.domain.Supervise;
import com.iot.supervise.dto.SuperviseDO;
import com.iot.supervise.service.SuperviseService;


/**
 * socket线程
 * */
public class MyThread extends Thread{
	private static Logger LOG = LoggerFactory.getLogger(MyThread.class);
	
	private volatile boolean status;//设备状态,ture;正在运行  false：已经关闭	
	private Socket socket;
	private SuperviseService superviseService;
	private SuperviseDAO superviseDAO;
	
	public  boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public MyThread(Socket socket,SuperviseDAO superviseDAO){
        this.socket=socket;
        this.superviseDAO=superviseDAO;
        status=true;
    }
	
	public void run() {
		BufferedReader receive = null;
		PrintWriter send = null;
		String tem = null;
		String cmd="GET";
		Supervise s=null;
		
		try{
			receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    send = new PrintWriter(socket.getOutputStream());
		    s=new Supervise();
		    while(status){  
		        send.println(cmd);  
                send.flush();
		    	tem = receive.readLine(); 
		    	System.out.println(tem);
		    	/**
		    	 * 保存到数据库
		    	 * */
		    	s=new Supervise();
		    	s.setTaskid(1);
                s.setSensorvalue(tem);
                s.setSensorvalue2("666");
                s.setSupervisetime(TimeDateUtility.getCurrentTimestamp());
                s.setWarningclass(0);
                this.superviseDAO.save(s);
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
                
                if(tem.equals("END")){  
                    break;  
                }
               
                System.out.println("Client Socket Message:"+tem);  
                Thread.sleep(1000);  
                  
            }
            
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (InterruptedException e) {
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

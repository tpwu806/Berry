package com.iot.common.socket.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iot.common.utilities.Compare;
import com.iot.common.utilities.TimeDateUtility;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.dao.SuperviseDAO;
import com.iot.supervise.domain.Supervise;
import com.iot.supervise.dto.SuperviseDO;
import com.iot.supervise.service.SuperviseService;
import com.iot.threshold.dto.ThresholdDO;


/**
 * socket线程
 * */
public class MyThread extends Thread{
	private static Logger LOG = LoggerFactory.getLogger(MyThread.class);
	
	private volatile boolean status;//设备状态,ture;正在运行  false：已经关闭	
	private Socket socket;
	private SuperviseService superviseService;
	private SuperviseDAO superviseDAO;
	private ThresholdDO thresholdDO;
	
	public  boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public MyThread(Socket socket,SuperviseDAO superviseDAO,ThresholdDO thresholdDO){
        this.socket=socket;
        this.superviseDAO=superviseDAO;
        status=true;
        this.thresholdDO=thresholdDO;
    }
	
	public void run() {
		BufferedReader receive = null;
		PrintWriter send = null;;
		String cmd="GET";
		
		try{
			receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			send = new PrintWriter(socket.getOutputStream());
			
		    while(status){
		    	//String temperature=null;
		        send.println(cmd);  
                send.flush();
                String tem = receive.readLine();
		    	//System.out.println("tem:"+tem);
		    	if(tem.length()==30){
		    		String fail = tem.substring(15, 16);
		    		if(!fail.equals("0")){
		    			fail=tem.substring(15, 17);
		    		}
			    	System.out.println("fail:"+fail);
			    	
			    	/**
			    	 * 保存到数据库
			    	 * */
			    	Supervise s=new Supervise();
			    	s.setTaskid(1);
	                s.setSensorvalue(fail);
	                s.setSensorvalue2("0");
	                s.setSupervisetime(TimeDateUtility.getCurrentTimestamp());
	                /*if(String.valueOf(fail) != null){
	                	s.setWarningclass(1);
	                }*/
	                s.setWarningclass(0);
	                this.superviseDAO.save(s);
	                //System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
		    	}
                if(tem.equals("END")){  
                    break;  
                }               
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

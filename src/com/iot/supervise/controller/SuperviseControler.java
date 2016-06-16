package com.iot.supervise.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iot.device.domain.Device;
import com.iot.device.dto.DeviceDO;
import com.iot.device.service.DeviceService;
import com.iot.exceptions.DaoFinderException;
import com.iot.sensor.service.SensorService;
import com.iot.supervise.domain.Task;
import com.iot.supervise.dto.SuperviseDO;
import com.iot.supervise.dto.TaskDO;
import com.iot.supervise.service.SuperviseService;
import com.iot.supervise.service.TaskService;

/**
 * @author wutongpeng 201605062115
 * 控制
 */

@Controller
public class SuperviseControler {
	static final Logger log = LoggerFactory.getLogger(SuperviseControler.class);
	
	@Resource
	private Environment env;	
	@Autowired
	private SuperviseService superviseService;
	@Autowired
	private SensorService sensorService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private TaskService taskService;
	
	/**
	 * 开启设备
	 * */
	@RequestMapping(value = { "/divice/startdevice/{deviceId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView startDevice(@PathVariable Integer deviceId) {
		ModelAndView modelAndView = null;
		try {
			Device d=this.deviceService.startDevice(deviceId);
			//Task t=this.taskService.startTask(d);
			modelAndView = new ModelAndView("redirect:/device/viewdevice");
		} catch (Exception ex) {
			log.debug("Error in finding device  to use in start", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = { "/divice/stopdevice/{deviceId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView stopDevice(@PathVariable Integer deviceId) {
		ModelAndView modelAndView = null;
		try {
			Device device=this.deviceService.stopDevice(deviceId);
			//Task t=this.taskService.stopTask(device);
			modelAndView = new ModelAndView("redirect:/device/viewdevice");
		} catch (Exception ex) {
			log.debug("Error in finding device  to use in stop", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = { "/supervise/viewsupervise" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView viewSupervise(){
		ModelAndView modelAndView = null;
		try{
			TaskDO taskObject = this.taskService.findAliveTask();
			if(taskObject==null){				
				//modelAndView = new ModelAndView("redirect:/device/viewdevice?page=0&size=" + this.env.getRequiredProperty("paging.numitems"));
				modelAndView = new ModelAndView("/supervise/real_time_protection");
				modelAndView.addObject("MESSAGE_KEY", "没有任务被激活，请在设备列表中开启设备！");
			}else{
				modelAndView = new ModelAndView("/supervise/real_time_protection");
				modelAndView.addObject("task", taskObject);
			}
			
			
		} catch (Exception ex) {
			log.debug("Error retrieving list device search results or retrieving all device", ex);
			//modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}				
		return modelAndView;
	}
	
	/**
	 * 功能    获取即时信息
	 * @return
	 * @throws DaoFinderException
	 */
	@ResponseBody
	@RequestMapping(value = { "/supervise/getdht11" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST})
	public String getdht11() throws DaoFinderException {
		String tem = null;
		try{
			SuperviseDO s=this.superviseService.findMostNewSupervise();
			tem=s.getSensorvalue();
			System.out.println("tem"+tem);
			//tem=s.getSupervisetime().toString();
			/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tem=df.format(new Date());*/
		} catch (Exception ex) {
			log.debug("Error retrieving list device search results or retrieving all device", ex);
		}				
		return tem;
	}
}

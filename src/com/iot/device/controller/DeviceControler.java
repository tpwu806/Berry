package com.iot.device.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iot.exceptions.DaoFinderException;
import com.iot.device.service.DeviceService;
import com.iot.sensor.service.SensorService;

@Controller
public class DeviceControler {
	static final Logger log = LoggerFactory.getLogger(DeviceControler.class);
	
	@Resource
	private Environment env;
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private DeviceService deviceService;
	
	
//	@RequestMapping(value = { "/device/getsensor" }, method = {
//			org.springframework.web.bind.annotation.RequestMethod.GET})
//	public ModelAndView getTem() throws DaoFinderException {
//		ModelAndView modelAndView = new ModelAndView("/supervise/supervise");			
//		Integer tem=this.sensorService.getTem();
//		modelAndView.addObject("tem", tem);
//		return modelAndView;
//	}
}

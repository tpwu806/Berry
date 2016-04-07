package com.iot.raspberry.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iot.exceptions.DaoFinderException;
import com.iot.raspberry.service.Dht11Service;
import com.iot.usermgmt.controller.UserMgmtController;

//@Controller
public class PiControler {
	static final Logger log = LoggerFactory.getLogger(PiControler.class);
	
//	@Resource
//	private Environment env;
//	
//	@Autowired
//	private Dht11Service dht11Service;
//	@RequestMapping(value = { "/pi/getTem" }, method = {
//			org.springframework.web.bind.annotation.RequestMethod.GET})
//	public ModelAndView getTem() throws DaoFinderException {
//		ModelAndView modelAndView = new ModelAndView("/pi/getTem");			
//		Integer tem=this.dht11Service.getTem();
//		modelAndView.addObject("tem", tem);
//		return modelAndView;
//	}
}

package com.iot.supervise.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iot.supervise.service.SuperviseService;

@Controller
public class SuperviseControler {
	static final Logger log = LoggerFactory.getLogger(SuperviseControler.class);
	
	@Resource
	private Environment env;
	
	@Autowired
	private SuperviseService superviseService;
	
	
	
	
	@RequestMapping(value = { "/supervise/viewsupervise" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET})
	public String viewSupervise(){			
		return "/supervise/list-of-supervise";
	}
	
	@ResponseBody
	@RequestMapping(value = { "/supervise/getdht11" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST})
	public String getdht11() /*throws DaoFinderException*/ {
		String tem;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tem=df.format(new Date());
					
		return tem;
	}
}

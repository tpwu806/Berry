package com.iot.usermgmt.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iot.usermgmt.service.UserService;

@Controller
public class UserMgmtController {
	static final Logger log = LoggerFactory.getLogger(UserMgmtController.class);

	@Resource
	private Environment env;
	
	@RequestMapping(value = { "/usermgmt/welcome" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView mainPage() {
		ModelAndView mv = null;			
			mv = new ModelAndView("/usermgmt/welcome");
		return mv;
	}
}

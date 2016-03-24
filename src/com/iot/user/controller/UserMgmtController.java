package com.iot.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class UserMgmtController {

	@RequestMapping({"/welcome" })
	public ModelAndView mainPage() {
		ModelAndView mv = null;			
			mv = new ModelAndView("welcome");
		return mv;
	}
}

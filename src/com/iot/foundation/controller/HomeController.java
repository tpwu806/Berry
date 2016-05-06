package com.iot.foundation.controller;


//import com.ght.common.utilities.UserUtility;
//import com.ght.forums.service.ForumPostService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iot.exceptions.DaoFinderException;
import com.iot.usermgmt.dto.CreateEditUserDO;
import com.iot.usermgmt.service.UserService;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@Resource
	private Environment env;
	
	@RequestMapping({"/login" })
	public ModelAndView login(CreateEditUserDO user,HttpSession httpSession) {
		ModelAndView mv = null;	
		//System.out.println(user.getUsername());
		try {
			if(this.userService.LoginCheck(user)){
				mv = new ModelAndView("redirect:/device/viewdevice");
				httpSession.setAttribute("loginname", user.getUsername());
			}else{
				mv = new ModelAndView("loginform");
			}
		} catch (DaoFinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping({ "/", "/index" })
	public ModelAndView mainPage(CreateEditUserDO User) {
		ModelAndView mv = null;	
				mv = new ModelAndView("redirect:/loginform");		
		return mv;
	}

	@RequestMapping(value = { "/loginform" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView loginPage() {
		ModelAndView modelAndView = new ModelAndView("login_form");
		
		CreateEditUserDO userForm = new CreateEditUserDO();
		modelAndView.addObject("userForm", userForm);
		
		return modelAndView;
	}

	@RequestMapping({ "/unauthorized" })
	public ModelAndView unauthorizedPage() {
		return new ModelAndView("error/unauthorized");
	}

	@RequestMapping({ "/notfound" })
	public ModelAndView pageNotFound() {
		return new ModelAndView("error/notfound");
	}
}

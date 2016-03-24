package com.iot.foundation.controller;


//import com.ght.common.utilities.UserUtility;
//import com.ght.forums.service.ForumPostService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午11:37:00
 * @author zhanggp (mailto:*****@sdas.org)
 * 功能说明：主页Controller
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午11:37:00 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//@Autowired
	//private UserUtility userUtil;

	//@Autowired
	//private ForumPostService postService;

	@Resource
	private Environment env;

	@RequestMapping({ "/", "/index" })
	public ModelAndView mainPage() {
		ModelAndView mv = null;
		try {
			//if (this.userUtil.isCurrentUserInRole("ROLE_USRMGMT"))
			//	mv = new ModelAndView("redirect:/org/editorgform");
			//else {
				mv = new ModelAndView("redirect:/welcome");
			//}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return mv;
	}
	@RequestMapping({ "/welcome" })
	public ModelAndView welcome() {
		ModelAndView mv = null;
		try {
			//if (this.userUtil.isCurrentUserInRole("ROLE_USRMGMT"))
			//	mv = new ModelAndView("redirect:/org/editorgform");
			//else {
				mv = new ModelAndView("welcome");
			//}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return mv;
	}

	@RequestMapping({ "/loginform" })
	public ModelAndView loginPage() {
		return new ModelAndView("login_form");
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

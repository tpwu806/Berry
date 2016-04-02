package com.iot.foundation.security;

//import com.ght.common.utilities.UserUtility;
//import com.ght.usermgmt.dao.SecUsersDAO;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class ExtAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	static final Logger log = LoggerFactory.getLogger(ExtAuthenticationSuccessHandler.class);

	//@Autowired
	//private SecUsersDAO secUsersDAO;

	//@Autowired
	//private UserUtility userUtil;

	@Resource
	private Environment env;

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();

		if (role.contains("ROLE_USRMGMT")) {
			return "/org/vieworg";
		}

		return "/qilu/notice/viewnotice?page=0&size=" + this.env.getRequiredProperty("paging.numitems");
	}

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		try {
			//String givenName = this.userUtil.getGNameofUserUser(this.userUtil.getUserUserForCurrentUser());

			//request.getSession().setAttribute("givenName", givenName);

			super.onAuthenticationSuccess(request, response, authentication);
		} catch (Exception ex) {
			log.debug("Error getting the given name of the logged in user after auth success", ex);
			throw new ServletException(ex.getMessage());
		}
	}
}
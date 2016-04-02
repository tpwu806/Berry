package com.iot.foundation.security;

//import com.iot.usermgmt.dao.SecUsersDAO;
//import com.iot.usermgmt.model.SecUsers;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ForcePasswordChangeFilter implements Filter {
	static final Logger log = LoggerFactory.getLogger(ForcePasswordChangeFilter.class);

	private RequestCache requestCache = new HttpSessionRequestCache();

	//@Autowired
	//private SecUsersDAO secUsersDAO;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null) && (auth.isAuthenticated())) {
			String username = auth.getName();

//			List users = this.secUsersDAO.findDistinctByUsername(username);
//			if ((users != null) && (!users.isEmpty())) {
//				SecUsers user = (SecUsers) users.get(0);
//				if (user.isReqpwdchange()) {
//					String servletpath = req.getServletPath();
//					log.debug("ForcePasswordChangeFilter servlet path: " + servletpath);
//
//					if ((!StringUtils.isEmpty(servletpath))
//							&& (!servletpath.matches(".*/user/updatepasswordform/updatepassword"))) {
//						SavedRequest sr = this.requestCache.getRequest(req, res);
//						if (sr != null) {
//							if (!servletpath.contains("/user/updatepasswordform")) {
//								this.requestCache.saveRequest(req, res);
//							}
//
//						} else {
//							this.requestCache.saveRequest(req, res);
//						}
//
//						RequestDispatcher dispatcher = request.getRequestDispatcher("/user/updatepasswordform/");
//						if (dispatcher != null) {
//							dispatcher.forward(request, response);
//							return;
//						}
//
//					}
//
//				}
//
//			}

		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
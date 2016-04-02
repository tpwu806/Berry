package com.iot.foundation.config;

import com.iot.foundation.security.ExtAuthenticationSuccessHandler;
import com.iot.foundation.security.ExtJdbcUserDetailsManager;
import com.iot.foundation.security.ForcePasswordChangeFilter;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
/**
 * *****************************************************************
 * Created on 2016年1月18日 下午1:47:45
 * @author 
 * 功能说明： Spring Security 配置
 *
 * 修改历史
 * Revision 1.0.1   
 * Update: ------ empty log ------
 ******************************************************************
 */
@Configuration
@EnableWebSecurity
public class MultiHttpSecurityConfig extends WebSecurityConfigurerAdapter {
	static final Logger log = LoggerFactory.getLogger(MultiHttpSecurityConfig.class);

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ExtJdbcUserDetailsManager extJdbcUserDetailsManager;

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.extJdbcUserDetailsManager).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Configuration
	@Order(1)
	public static class FormSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		ExtAuthenticationSuccessHandler authSuccessHandler;

		@Autowired
		ForcePasswordChangeFilter forcePwdChangeFilter;

		protected void configure(HttpSecurity http) throws Exception {
			String USRREG_OR_HIGHER = "hasRole('ROLE_USRREG') or hasRole('ROLE_USRMGMT') or hasRole('ROLE_ORGREG') or hasRole('ROLE_ORGMGMT') or hasRole('ROLE_ORGMOD') or hasRole('ROLE_ORGADMIN')";

			String USRMGMT_OR_HIGHER = "hasRole('ROLE_USRMGMT') or hasRole('ROLE_ORGREG') or hasRole('ROLE_ORGMGMT') or hasRole('ROLE_ORGMOD') or hasRole('ROLE_ORGADMIN')";

			String ORGREG_OR_HIGHER = "hasRole('ROLE_ORGREG') or hasRole('ROLE_ORGMGMT') or hasRole('ROLE_ORGMOD') or hasRole('ROLE_ORGADMIN')";

			String ORGMGMT_OR_HIGHER = "hasRole('ROLE_ORGMGMT') or hasRole('ROLE_ORGMOD') or hasRole('ROLE_ORGADMIN')";

			String ORGMOD_OR_HIGHER = "hasRole('ROLE_ORGMOD') or hasRole('ROLE_ORGADMIN')";

			HttpSecurity  security3=http.csrf().disable();
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl11=security3.authorizeRequests().antMatchers(new String[] { "/admin/**" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl10=(AuthorizedUrl) authorurl11.hasRole("ORGADMIN").antMatchers(new String[] { "/mod/**" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl9=(AuthorizedUrl) authorurl10.access(ORGMOD_OR_HIGHER).antMatchers(new String[] { "/qilu/**" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl8=(AuthorizedUrl) authorurl9.access(ORGREG_OR_HIGHER).antMatchers(new String[] { "/user/edituserform", "/user/edituserform/edituser" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl7=(AuthorizedUrl) authorurl8.access(USRMGMT_OR_HIGHER).antMatchers(new String[] { "/user/updatepasswordform","/user/updatepasswordform/updatepassword" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl6=(AuthorizedUrl) authorurl7.access(USRMGMT_OR_HIGHER).antMatchers(new String[] { "/user/resetpassword" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl5=(AuthorizedUrl) authorurl6.access(USRMGMT_OR_HIGHER).antMatchers(new String[] {"/user/viewlistoforgusers/*" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl4=(AuthorizedUrl) authorurl5.access(USRMGMT_OR_HIGHER).antMatchers(new String[] {"/org/editorgform" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl3=(AuthorizedUrl) authorurl4.hasRole("USRMGMT").antMatchers(new String[] {"/org/vieworg" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl2=(AuthorizedUrl) authorurl3.hasRole("USRMGMT").antMatchers(new String[] {"/org/editorgform/editorg" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl1=(AuthorizedUrl) authorurl2.access(USRMGMT_OR_HIGHER).antMatchers(new String[] {"/notfound" });
			
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl  authorurl=(AuthorizedUrl) authorurl1.access(ORGREG_OR_HIGHER).antMatchers(new String[] {"/**" });
			
			HttpSecurity security2=(HttpSecurity) authorurl.access(ORGREG_OR_HIGHER).and();
			
			HttpSecurity security1=security2.exceptionHandling().accessDeniedPage("/unauthorized").and();
			
			FormLoginConfigurer configuer1=security1.formLogin().permitAll();
			 
		    FormLoginConfigurer configuer=(FormLoginConfigurer) configuer1.loginPage("/loginform").successHandler(this.authSuccessHandler);

			HttpSecurity security=(HttpSecurity) configuer.and();
				
			security.addFilterAfter(this.forcePwdChangeFilter,FilterSecurityInterceptor.class).logout().logoutUrl("/logout").logoutSuccessUrl("/loginform");
																																																															
		}

		public void configure(WebSecurity web) throws Exception {
			((WebSecurity.IgnoredRequestConfigurer) web.ignoring()
					.antMatchers(new String[] { "/css/**", "/fonts/**", "/images/**", "/js/**" }))
							.antMatchers(new String[] { "/unauthorized" });
		}
	}

}
package com.iot.usermgmt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.usermgmt.service.UserServiceImpl;
import com.iot.exceptions.DaoFinderException;
import com.iot.usermgmt.dao.UserUserDAO;
import com.iot.usermgmt.domain.UserUser;
import com.iot.usermgmt.dto.CreateEditUserDO;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.usermgmt.dao"})
public class UserServiceImpl implements UserService {

	static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserUserDAO userUserDAO;	

	@Override
	public boolean LoginCheck(CreateEditUserDO user) throws DaoFinderException {
		
		try{		   
			List<UserUser> result=this.userUserDAO.findByUsernameAndUserpass(user.getUsername(),user.getUserpass());
			if(result.size()>0){
				return true;
			}
			return false;
		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}

}

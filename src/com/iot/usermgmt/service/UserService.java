package com.iot.usermgmt.service;

import com.iot.exceptions.DaoFinderException;
import com.iot.usermgmt.domain.UserUser;
import com.iot.usermgmt.dto.CreateEditUserDO;

public interface UserService {

	boolean LoginCheck(CreateEditUserDO user) throws DaoFinderException;
}

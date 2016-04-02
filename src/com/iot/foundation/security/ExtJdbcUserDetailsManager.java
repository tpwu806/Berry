package com.iot.foundation.security;

import com.iot.exceptions.DaoAlreadyExistsException;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoMethodException;
import com.iot.exceptions.DaoUpdateException;
//import com.iot.usermgmt.dataobjects.CreateEditUserDO;
import com.iot.usermgmt.dto.CreateEditUserDO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class ExtJdbcUserDetailsManager extends JdbcUserDetailsManager {
	static final Logger log = LoggerFactory.getLogger(ExtJdbcUserDetailsManager.class);

	@Autowired
	protected PasswordEncoder passwordEncoder;
	String userByUsernameQ = "select USERNAME, PASSWORD, ENABLED from SEC_USERS where USERNAME = ?";

	String authByUsernameQ = "select USERNAME, ROLE from SEC_USER_ROLES where USERNAME = ?";

	String grpAuthByUsernameQ = "select G.ID, G.GROUP_NAME, GA.AUTHORITY from SEC_GROUPS G, SEC_GROUP_MEMBERS GM, SEC_GROUP_AUTHORITIES GA where GM.USERNAME = ? and G.ID = GA.GROUP_ID and G.ID = GM.GROUP_ID";

	public static String CREATE_USER_SQL = "insert into SEC_USERS (USERNAME, PASSWORD, ENABLED) values (?,?,?)";
	public static final String UPDATE_USER_SQL = "update SEC_USERS set PASSWORD = ?, ENABLED = ? where USERNAME = ?";
	public static final String CHANGE_ENABLED_USER_SQL = "update SEC_USERS set ENABLED = ? where USERNAME = ?";
	public static String USER_EXISTS_SQL = "select USERNAME from SEC_USERS where USERNAME = ?";

	public static String CHANGE_PASSWORD_SQL = "update SEC_USERS set PASSWORD = ? where USERNAME = ?";

	public static String INSERT_GROUP_MEMBER_SQL = "insert into SEC_GROUP_MEMBERS (GROUP_ID, USERNAME) values (?,?)";
	public static final String DELETE_GROUP_MEMBER_SQL = "delete from SEC_GROUP_MEMBERS where GROUP_ID = ? and USERNAME = ?";
	public static final String FIND_GROUPS_SQL = "select GROUP_NAME from SEC_GROUPS";
	public static final String FIND_GROUP_ID_SQL = "select ID from SEC_GROUPS where GROUP_NAME = ?";
	public static String FIND_GROUPS_USER_IS_IN_SQL = "select g.GROUP_NAME from SEC_GROUPS g, SEC_GROUP_MEMBERS gm, SEC_USERS u where u.USERNAME = ? and u.USERNAME = gm.USERNAME and g.ID = gm.GROUP_ID";

	public static String FIND_ALL_USERNAMES_SQL = "select USERNAME from SEC_USERS S, USER_PROFILE U where U.SEC_USERS_ID = S.ID and U.ISDELETED <> 1 and S.ENABLED = 1 union select USERNAME from SEC_USERS S2, USER_PROFILE U2 where U2.SEC_USERS_ID = S2.ID and U2.ISDELETED <> 1 and U2.ISBLACKLISTED = 1 order by USERNAME";

	public static String FIND_ALL_USERS_SQL = "select S.ID, S.USERNAME, S.ENABLED, U.IMAGEURL, U.ISBLACKLISTED, U.G_NAME, U.PHONENUM, C.JOB_PHONENUM, U.GENDER, C.JOB_CLASS, G.GROUP_NAME, O.ORGNAME from SEC_USERS S, USER_PROFILE U, USER_CONTACT C, ORG_ORG O, SEC_GROUPS G, SEC_GROUP_MEMBERS M where U.SEC_USERS_ID = S.ID and U.ISDELETED <> 1 and ((S.ENABLED = 1 AND U.ISBLACKLISTED <> 1) OR (S.ENABLED = 0 AND U.ISBLACKLISTED = 1)) and S.USERNAME = M.USERNAME and M.GROUP_ID = G.ID and C.USER_ID = U.ID and C.ORG_ID = O.ID ";

	public static String COUNT_ALL_USERS_SQL = "select count(*) from (" + FIND_ALL_USERS_SQL + ") as temp";

	public static String FIND_USER_BY_USERNAME_SQL = "select S.ID, S.USERNAME, S.ENABLED, U.ISBLACKLISTED from SEC_USERS S, USER_PROFILE U where S.USERNAME = ? and S.ID = U.SEC_USERS_ID order by USERNAME";

	public static String REMOVE_USER_FROM_ALL_GROUPS_SQL = "delete from SEC_GROUP_MEMBERS where USERNAME = ?";

	public static String USERS_BY_USERNAME_SQL = "select USERNAME, PASSWORD, ENABLED from SEC_USERS where USERNAME = ?";

	public static String AUTH_BY_GROUP_SQL = "select g.id, g.group_name, ga.authority from SEC_GROUPS g, SEC_GROUP_MEMBERS gm, SEC_GROUP_AUTHORITIES ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id";

	@Autowired
	public ExtJdbcUserDetailsManager(DataSource dataSource, AuthenticationManager authenticationManagerBean) {
		super.setDataSource(dataSource);

		super.setAuthenticationManager(authenticationManagerBean);

		super.setUsersByUsernameQuery(this.userByUsernameQ);
		super.setAuthoritiesByUsernameQuery(this.authByUsernameQ);
		super.setGroupAuthoritiesByUsernameQuery(this.grpAuthByUsernameQ);

		super.setRolePrefix("ROLE_");
		super.setEnableAuthorities(false);
		super.setEnableGroups(true);
	}

	public void createUser(String username, String password, String[] groups)
			throws DaoAlreadyExistsException, DaoCreateException {
		try {
			super.setUserExistsSql(USER_EXISTS_SQL);
			if (super.userExists(username)) {
				throw new DaoAlreadyExistsException("User exists - cannot create using username: " + username);
			}

			String encodedPassword = this.passwordEncoder.encode(password);

			UserDetails ud = new User(username, encodedPassword, true, true, true, true, new ArrayList());

			super.setCreateUserSql(CREATE_USER_SQL);
			super.createUser(ud);

			if ((groups != null) && (groups.length > 0)) {
				for (String group : groups)
					extAddUserToGroup(username, group);
			}
		} catch (DaoAlreadyExistsException aeEx) {
			log.debug("Username already exists", aeEx);
			throw aeEx;
		} catch (Exception ex) {
			log.debug("User create failed", ex);
			throw new DaoCreateException(ex.getMessage());
		}
	}

	public void updateUser(String username, boolean enabled, String[] groups) throws DaoUpdateException {
		try {
			if (enabled)
				enableUser(username);
			else {
				disableUser(username);
			}

			if ((groups != null) && (groups.length > 0)) {
				removeUserFromGroups(username);

				for (String group : groups)
					extAddUserToGroup(username, group);
			}
		} catch (Exception ex) {
			log.debug("User update failed", ex);
			throw new DaoUpdateException(ex.getMessage());
		}
	}

	public void updateUserFull(String username, String password, boolean enabled, String[] groups)
			throws DaoUpdateException {
		try {
			String encodedPassword = this.passwordEncoder.encode(password);

			UserDetails ud = new User(username, encodedPassword, enabled, true, true, true, new ArrayList());

			super.setUpdateUserSql("update SEC_USERS set PASSWORD = ?, ENABLED = ? where USERNAME = ?");
			super.updateUser(ud);

			if ((groups != null) && (groups.length > 0)) {
				removeUserFromGroups(username);

				for (String group : groups)
					extAddUserToGroup(username, group);
			}
		} catch (Exception ex) {
			log.debug("User update failed", ex);
			throw new DaoUpdateException(ex.getMessage());
		}
	}

	public void softDeleteUser(String username) throws DaoDeleteException {
		try {
			JdbcTemplate jdbcT = super.getJdbcTemplate();
			jdbcT.update("update SEC_USERS set ENABLED = ? where USERNAME = ?",
					new Object[] { new Boolean(false), username });
		} catch (Exception ex) {
			log.debug("User delete failed", ex);
			throw new DaoDeleteException(ex.getMessage());
		}
	}

	public void disableUser(String username) throws DaoMethodException {
		try {
			JdbcTemplate jdbcT = super.getJdbcTemplate();
			jdbcT.update("update SEC_USERS set ENABLED = ? where USERNAME = ?",
					new Object[] { new Boolean(false), username });
		} catch (Exception ex) {
			log.debug("Disable user failed", ex);
			throw new DaoMethodException(ex.getMessage());
		}
	}

	public void enableUser(String username) throws DaoMethodException {
		try {
			JdbcTemplate jdbcT = super.getJdbcTemplate();
			jdbcT.update("update SEC_USERS set ENABLED = ? where USERNAME = ?",
					new Object[] { new Boolean(true), username });
		} catch (Exception ex) {
			log.debug("Enable user failed", ex);
			throw new DaoMethodException(ex.getMessage());
		}
	}

	public List<String> getListOfUsernames() throws DaoFinderException {
		List list = null;
		try {
			JdbcTemplate jdbcT = super.getJdbcTemplate();
			list = jdbcT.queryForList(FIND_ALL_USERNAMES_SQL, String.class);

			return list;
		} catch (Exception ex) {
			log.debug("getListOfUsernames failed", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}

	public List<ExtUser> getListOfUsers() throws DaoFinderException {
		List extUsers = null;
		try {
			JdbcTemplate jdbcT = super.getJdbcTemplate();

			String query = FIND_ALL_USERS_SQL + " ORDER BY CONVERT(U.G_NAME USING GBK)";

			List<UserDetails> userList = jdbcT.query(query, new Integer[] { Integer.valueOf(0), Integer.valueOf(2147483647) },
					new RowMapper() {
						public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
							Integer id = Integer.valueOf(rs.getInt(1));
							String username = rs.getString(2);
							boolean enabled = rs.getBoolean(3);
							boolean blacklisted = rs.getBoolean(4);

							return new ExtUser(id, username, enabled, blacklisted);
						}
					});
			if ((userList != null) && (!userList.isEmpty())) {
				extUsers = new ArrayList();
				for (UserDetails user : userList) {
					extUsers.add((ExtUser) user);
				}

			}

			return extUsers;
		} catch (Exception ex) {
			log.debug("findUserByUsername function failed", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}

	public Page<CreateEditUserDO> getPagedListOfUsers(Pageable pgble) throws DaoFinderException {
		Page pageOfUsers = null;
		try {
			JdbcTemplate jdbcT = super.getJdbcTemplate();

			int pgnum = pgble.getPageNumber();
			int pgsize = pgble.getPageSize();
			int beginIndex = pgnum * pgsize;

			String query = FIND_ALL_USERS_SQL + " ORDER BY CONVERT(U.G_NAME USING GBK)";

			String psSQL = query + " limit " + beginIndex + "," + pgsize;

			List userList = jdbcT.query(psSQL, new RowMapper() {
				public CreateEditUserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					Integer id = Integer.valueOf(rs.getInt(1));
					String username = rs.getString(2);
					boolean enabled = rs.getBoolean(3);
					String imageurl = rs.getString(4);
					boolean blacklisted = rs.getBoolean(5);
					String gName = rs.getString(6);
					String phonenum = rs.getString(7);
					String workphonenum = rs.getString(8);
					String gender = rs.getString(9);
					String jobclass = rs.getString(10);
					String grpname = rs.getString(11);
					String orgname = rs.getString(12);

					CreateEditUserDO fh = new CreateEditUserDO();
					fh.setUserId(id);
					fh.setUsername(username);
//					fh.setEnabled(enabled);
//					fh.setImageurl(imageurl);
//					fh.setBlacklisted(blacklisted);
//					fh.setgName(gName);
//					fh.setPhonenum(phonenum);
//					fh.setJobPhonenum(workphonenum);
//					fh.setGender(gender);
//					fh.setJobClass(jobclass);
//					fh.setGroup(grpname);
//					fh.setOrgname(orgname);

					return fh;
				}
			});
			if ((userList != null) && (!userList.isEmpty())) {
				Integer totalCount = (Integer) jdbcT.queryForObject(COUNT_ALL_USERS_SQL, Integer.class);

				pageOfUsers = new PageImpl(userList, pgble, totalCount.intValue());
			}

			return pageOfUsers;
		} catch (Exception ex) {
			log.debug("getPagedListOfUsers function failed", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}

	public ExtUser findSecUserByUsername(String username) throws DaoFinderException {
		ExtUser extUser = null;
		try {
			JdbcTemplate jdbcT = super.getJdbcTemplate();

			String[] args = { username };

			List userList = jdbcT.query(FIND_USER_BY_USERNAME_SQL, args, new RowMapper() {
				public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
					Integer id = Integer.valueOf(rs.getInt(1));
					String username = rs.getString(2);
					boolean enabled = rs.getBoolean(3);
					boolean blacklisted = rs.getBoolean(4);

					return new ExtUser(id, username, enabled, blacklisted);
				}
			});
			if ((userList != null) && (userList.size() > 0)) {
				if ((userList.get(0) instanceof ExtUser)) {
					extUser = (ExtUser) userList.get(0);
				}
			}

			return extUser;
		} catch (Exception ex) {
			log.debug("findSecUserByUsername function failed", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}

	public void changePasswordForCurrentUser(String oldPassword, String newPassword) throws DaoMethodException {
		try {
			String encNewPassword = this.passwordEncoder.encode(newPassword);

			super.setChangePasswordSql(CHANGE_PASSWORD_SQL);
			super.changePassword(oldPassword, encNewPassword);
		} catch (Exception ex) {
			log.debug("Change password for current user failed", ex);
			throw new DaoMethodException(ex.getMessage());
		}
	}

	public Map resetPasswordForUser(String username) throws DaoMethodException {
		String randomPwd = null;
		try {
			//randomPwd = RandomStringUtils.randomAlphanumeric(8);
			randomPwd = RandomStringUtils.randomNumeric(6);
			String encPwd = this.passwordEncoder.encode(randomPwd);

			JdbcTemplate jdbcT = super.getJdbcTemplate();
			jdbcT.update(CHANGE_PASSWORD_SQL, new Object[] { encPwd, username });
           
			Map<String, String>  result=new HashMap<String,String>();
			result.put("randomPwd", randomPwd);
			result.put("encPwd", encPwd);
			return result;
		} catch (Exception ex) {
			log.debug("Reset password for user failed", ex);
			throw new DaoMethodException(ex.getMessage());
		}
		
	}

	public void updateUserGroups(String username, String[] groups) throws DaoUpdateException {
		try {
			if ((groups != null) && (groups.length > 0)) {
				removeUserFromGroups(username);

				for (String group : groups)
					extAddUserToGroup(username, group);
			}
		} catch (Exception ex) {
			log.debug("Updating user groups failed", ex);
			throw new DaoUpdateException(ex.getMessage());
		}
	}

	private void extAddUserToGroup(String username, String groupname) throws DaoMethodException {
		try {
			super.setInsertGroupMemberSql(INSERT_GROUP_MEMBER_SQL);
			super.setFindGroupIdSql("select ID from SEC_GROUPS where GROUP_NAME = ?");
			super.addUserToGroup(username, groupname);
		} catch (Exception ex) {
			log.debug("Adding user to group failed", ex);
			throw new DaoMethodException(ex.getMessage());
		}
	}

	private void removeUserFromGroups(String username) throws DaoMethodException {
		try {
			JdbcTemplate jdbcT = super.getJdbcTemplate();
			jdbcT.update(REMOVE_USER_FROM_ALL_GROUPS_SQL, new Object[] { username });
		} catch (Exception ex) {
			log.debug("Removing user from groups failed", ex);
			throw new DaoMethodException(ex.getMessage());
		}
	}

	public List<String> getGroupsforUser(String username) throws DaoFinderException {
		try {
			setUserExistsSql(USER_EXISTS_SQL);
			if (userExists(username)) {
				JdbcTemplate jdbcT = super.getJdbcTemplate();

				String[] args = { username };
				List groups = jdbcT.queryForList(FIND_GROUPS_USER_IS_IN_SQL, args, String.class);

				return groups;
			}

			throw new Exception("Cannot find user: " + username);
		} catch (Exception ex) {
			log.debug("getGroupsUserBelongsTo failed", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}
}
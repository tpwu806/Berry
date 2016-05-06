package com.iot.common.dao;

import com.iot.common.utilities.JdbcUtil;
import com.iot.exceptions.DaoFinderException;
import com.iot.usermgmt.dto.CreateEditUserDO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午10:31:45
 * @author wutongpeng(mailto:)
 * 功能说明：device jdbcDAO
 *
 * 修改历史
 * Revision 1.0.1   2016年5月6日 上午10:31:45 by wutongpeng
 * Update: ------ empty log ------
 ******************************************************************
 */
@Component
public class JdbcDAO {
	static final Logger log = LoggerFactory.getLogger(JdbcDAO.class);

	@Autowired
	private JdbcUtil jdbcUtil;

	public Page<CreateEditUserDO> findUserByGNameOrOrgOrg(String searchString, String userRole, String jobClass,
			Pageable pgble) throws DaoFinderException {
		Page pageOfUsers = null;
		try {
			String FIND_ID_USERNAME_ORGNAME_QUERY = "select distinct S.ID, S.USERNAME, S.ENABLED, U.ISBLACKLISTED, U.G_NAME, U.PHONENUM, C.JOB_PHONENUM, U.GENDER, C.JOB_CLASS, G.GROUP_NAME, O.ORGNAME, U.IMAGEURL from SEC_USERS S, USER_PROFILE U, USER_CONTACT C, ORG_ORG O, SEC_GROUPS G, SEC_GROUP_MEMBERS M where U.SEC_USERS_ID = S.ID and U.ISDELETED <> 1 and ((S.ENABLED = 1 AND U.ISBLACKLISTED <> 1) OR (S.ENABLED = 0 AND U.ISBLACKLISTED = 1)) and S.USERNAME = M.USERNAME and M.GROUP_ID = G.ID and C.USER_ID = U.ID and C.ORG_ID = O.ID and (U.G_NAME LIKE ? escape '/' OR O.ORGNAME LIKE ? escape '/')";

			boolean hasUserRole = false;
			boolean hasJobClass = false;

			if (!StringUtils.isEmpty(userRole)) {
				hasUserRole = true;

				FIND_ID_USERNAME_ORGNAME_QUERY = FIND_ID_USERNAME_ORGNAME_QUERY.concat(" and G.GROUP_NAME = ?");
			}

			if (!StringUtils.isEmpty(jobClass)) {
				hasJobClass = true;

				FIND_ID_USERNAME_ORGNAME_QUERY = FIND_ID_USERNAME_ORGNAME_QUERY.concat(" and C.JOB_CLASS = ?");
			}

			String COUNT_ALL_USERS_SQL = "select count(*) from (" + FIND_ID_USERNAME_ORGNAME_QUERY + ") as temp";

			int pgnum = pgble.getPageNumber();
			int pgsize = pgble.getPageSize();
			int beginIndex = pgnum * pgsize;

			FIND_ID_USERNAME_ORGNAME_QUERY = FIND_ID_USERNAME_ORGNAME_QUERY
					+ " ORDER BY CONVERT(U.G_NAME USING GBK) limit " + beginIndex + "," + pgsize;

			JdbcTemplate jt = this.jdbcUtil.getJdbcTemplate();

			ArrayList listOfArgs = new ArrayList();
			if (!StringUtils.isEmpty(searchString)) {
				searchString=searchString.replaceAll("/","//");
				searchString=searchString.replaceAll("%","/%");
				searchString=searchString.replaceAll("_","/_");
				listOfArgs.add("%" + searchString + "%");
				listOfArgs.add("%" + searchString + "%");
			} else {
				listOfArgs.add("%");
				listOfArgs.add("%");
			}

			if (hasUserRole) {
				listOfArgs.add(userRole);
			}

			if (hasJobClass) {
				listOfArgs.add(jobClass);
			}

			String[] args = (String[]) listOfArgs.toArray(new String[listOfArgs.size()]);

			List userList = jt.query(FIND_ID_USERNAME_ORGNAME_QUERY, args, new RowMapper() {
				public CreateEditUserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					Integer id = Integer.valueOf(rs.getInt(1));
					String username = rs.getString(2);
					boolean enabled = rs.getBoolean(3);
					boolean blacklisted = rs.getBoolean(4);
					String gName = rs.getString(5);
					String phonenum = rs.getString(6);
					String workphonenum = rs.getString(7);
					String gender = rs.getString(8);
					String jobclass = rs.getString(9);
					String grpname = rs.getString(10);
					String orgname = rs.getString(11);
					String imageurl = rs.getString(12);

					CreateEditUserDO fh = new CreateEditUserDO();
					fh.setUserId(id);
					fh.setUsername(username);
/*					fh.setEnabled(enabled);
					fh.setBlacklisted(blacklisted);
					fh.setgName(gName);
					fh.setPhonenum(phonenum);
					fh.setJobPhonenum(workphonenum);
					fh.setGender(gender);
					fh.setJobClass(jobclass);
					fh.setGroup(grpname);
					fh.setOrgname(orgname);
					fh.setImageurl(imageurl);
*/
					return fh;
				}
			});
			if ((userList != null) && (!userList.isEmpty())) {
				Integer totalCount = (Integer) jt.queryForObject(COUNT_ALL_USERS_SQL, args, Integer.class);

				pageOfUsers = new PageImpl(userList, pgble, totalCount.intValue());
			}

			return pageOfUsers;
		} catch (Exception ex) {
			log.debug("Error in finding user by gname or orgname", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}
}
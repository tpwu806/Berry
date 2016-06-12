package com.iot.common.dao;

import com.iot.common.utilities.JdbcUtil;
import com.iot.device.dto.DeviceDO;
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
 * 功能说明：devicejdbcDAO
 *
 * 修改历史
 * Revision 1.0.1   2016年5月6日 上午10:31:45 by wutongpeng
 * Update: ------ empty log ------
 ******************************************************************
 */
@Component
public class DeviceJdbcDAO {
	static final Logger log = LoggerFactory.getLogger(DeviceJdbcDAO.class);

	@Autowired
	private JdbcUtil jdbcUtil;

	public Page<DeviceDO> findDeviceByGNameOrType(String searchString, String type,Pageable pgble) throws DaoFinderException {
		Page<DeviceDO> pageOfDevice = null;
		try {
			String FIND_DEVICE_QUERY = "select D.ID, D.DEVICENAME,D.DEVICEIP,D.DEVICEPORT,D.DEVICESTATUS,D.DEVICETYPE,D.SENSORNUMBER from DEVICE_DEVICE D  ";

			ArrayList<String> listOfArgs = new ArrayList<String>();
			
			if (!StringUtils.isEmpty(type)) {
				FIND_DEVICE_QUERY = FIND_DEVICE_QUERY.concat(" where D.DEVICETYPE = ?");
				listOfArgs.add(type);
			}
			if (!StringUtils.isEmpty(searchString)) {
				searchString=searchString.replaceAll("/","//");
				searchString=searchString.replaceAll("%","/%");
				searchString=searchString.replaceAll("_","/_");
				listOfArgs.add("%" + searchString + "%");
				listOfArgs.add("%" + searchString + "%");
				FIND_DEVICE_QUERY=FIND_DEVICE_QUERY.concat(" and D.DEVICENAME LIKE ? escape '/'");
			} else {
				listOfArgs.add("%");
				listOfArgs.add("%");
			}
			
			
			String COUNT_ALL_DEVICE_SQL = "select count(*) from (" + FIND_DEVICE_QUERY + ") as temp";

			int pgnum = pgble.getPageNumber();
			int pgsize = pgble.getPageSize();
			int beginIndex = pgnum * pgsize;

			FIND_DEVICE_QUERY = FIND_DEVICE_QUERY
					+ " limit " + beginIndex + "," + pgsize;

			String[] args = listOfArgs.toArray(new String[listOfArgs.size()]);
			JdbcTemplate jt = this.jdbcUtil.getJdbcTemplate();
			List<DeviceDO> deviceList = jt.query(FIND_DEVICE_QUERY, args, new RowMapper() {
				public DeviceDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					Integer id = Integer.valueOf(rs.getInt(1));
					String devicename = rs.getString(2);
					String deviceip = rs.getString(3);
					String deviceport = rs.getString(4);
					Integer devicestatus = rs.getInt(5);
					String devicetype = rs.getString(6);
					Integer sensornumber = rs.getInt(7);

					DeviceDO fh = new DeviceDO();
					fh.setId(id);
					fh.setDevicename(devicename);
				    fh.setDevicetype(devicetype);
					fh.setDeviceip(deviceip);
					fh.setDeviceport(deviceport);
					fh.setSensornumber(sensornumber);
					Integer[] ints = new Integer[1];
					ints[0] = devicestatus;					
					fh.setStatus(ints);
				
					return fh;
				}
			});
			if ((deviceList != null) && (!deviceList.isEmpty())) {
				Integer totalCount = jt.queryForObject(COUNT_ALL_DEVICE_SQL, args, Integer.class);
				pageOfDevice = new PageImpl<DeviceDO>(deviceList, pgble, totalCount.intValue());
			}

			return pageOfDevice;
		} catch (Exception ex) {
			log.debug("Error in finding device by dname or dtype", ex);
			throw new DaoFinderException(ex.getMessage());
		}
		
	}
}
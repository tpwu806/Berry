package com.iot.common.utilities;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午10:34:25
 * @author  wutongpeng
 * 功能说明：jdbc连接工具类
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午10:34:25
 * Update: ------ empty log ------
 ******************************************************************
 */
@Component
public class JdbcUtil extends JdbcDaoSupport {
	@Autowired
	public JdbcUtil(DataSource dataSource) {
		setDataSource(dataSource);

		initTemplateConfig();

		checkDaoConfig();
	}

	protected void initTemplateConfig() {
	}
}
package com.iot.supervise.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iot.device.domain.Device;
import com.iot.sensor.domain.Sensor;
/**
 * *****************************************************************
 * Created on 2016年4月7日  16:40:10
 * @author wutongpeng (mailto:wutongpeng803@163.com)
 * 功能说明：监控器daomain
 *
 * 修改历史
 * Revision 1.0.1   2016年 月 日  10:06:10 by 
 * Update: ------  ------
 * 
 * 
 ******************************************************************
 */
@Entity
@Table(name = "SUPERVISE_SUPERVISE")//采集信息表
public class Supervise implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//采集编号

	@Column(name = "TASKID")
	private Integer taskid;//设备id
	
	@Column(name = "SUPERVISETIME")
	private Timestamp supervisetime;//采集时间
	
	@Column(name = "SUPERVISEVALUE")
	private String sensorvalue;//传感器参数值
	
	@Column(name = "SUPERVISEVALUE2")
	private String sensorvalue2;//传感器参数2值

	@Column(name = "WARNINGCLASS")
	private Integer warningclass;//报警状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskid() {
		return taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public Timestamp getSupervisetime() {
		return supervisetime;
	}

	public void setSupervisetime(Timestamp supervisetime) {
		this.supervisetime = supervisetime;
	}

	public String getSensorvalue() {
		return sensorvalue;
	}

	public void setSensorvalue(String sensorvalue) {
		this.sensorvalue = sensorvalue;
	}

	public String getSensorvalue2() {
		return sensorvalue2;
	}

	public void setSensorvalue2(String sensorvalue2) {
		this.sensorvalue2 = sensorvalue2;
	}

	public Integer getWarningclass() {
		return warningclass;
	}

	public void setWarningclass(Integer warningclass) {
		this.warningclass = warningclass;
	}
	
	
	
}

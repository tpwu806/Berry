package com.iot.supervise.dto;

import java.sql.Timestamp;

import com.iot.device.domain.Device;
import com.iot.sensor.domain.Sensor;
import com.iot.supervise.domain.Task;

public class SuperviseDO {

	private Integer id;//采集编号
	
	private Integer taskid;//设备id
	
	private Timestamp supervisetime;//采集时间
	
	private String sensorvalue;//传感器参数值
	
	private String sensorvalue2;//传感器参数2值
	
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

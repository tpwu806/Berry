package com.iot.sensor.dto;

import com.iot.device.domain.Device;

public class SensorDO {

	private Integer id;//传感器编号
	
	private String sensorname;//传感器名称
	
	private String sensortype;//传感器类型
	
	private String sensorparameter;//传感器参数名
	
	private String sensorparameter2;//传感器参数2名
	
	private Integer deviceid;//设备id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSensorname() {
		return sensorname;
	}

	public void setSensorname(String sensorname) {
		this.sensorname = sensorname;
	}

	public String getSensortype() {
		return sensortype;
	}

	public void setSensortype(String sensortype) {
		this.sensortype = sensortype;
	}

	public String getSensorparameter() {
		return sensorparameter;
	}

	public void setSensorparameter(String sensorparameter) {
		this.sensorparameter = sensorparameter;
	}

	public String getSensorparameter2() {
		return sensorparameter2;
	}

	public void setSensorparameter2(String sensorparameter2) {
		this.sensorparameter2 = sensorparameter2;
	}

	public Integer getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}
	
}

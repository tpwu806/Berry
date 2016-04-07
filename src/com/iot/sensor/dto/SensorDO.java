package com.iot.sensor.dto;

public class SensorDO {

	private Integer id;//传感器编号
	
	private String sensorname;//传感器名称
	
	private Integer sensorparamiter;//传感器参数

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

	public Integer getSensorparamiter() {
		return sensorparamiter;
	}

	public void setSensorparamiter(Integer sensorparamiter) {
		this.sensorparamiter = sensorparamiter;
	}
	
	
}

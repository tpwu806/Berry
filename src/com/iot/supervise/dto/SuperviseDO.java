package com.iot.supervise.dto;

import java.sql.Timestamp;

import com.iot.device.domain.Device;
import com.iot.sensor.domain.Sensor;

public class SuperviseDO {

	private Integer id;//设备编号
	
	private String supervisename;//任务名称
	
	private String devicename;//多对一设备名称
	
	private Device device;//设备集合
	
	private String sensorname;//多对一传感器名称
	
	private Sensor sensor;//传感器集合
	
	private Timestamp supervisestarttime;//开启时间
	
	private Timestamp supervisestoptime;//关闭时间
	
	private String supervisetype;//开关状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupervisename() {
		return supervisename;
	}

	public void setSupervisename(String supervisename) {
		this.supervisename = supervisename;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getSensorname() {
		return sensorname;
	}

	public void setSensorname(String sensorname) {
		this.sensorname = sensorname;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Timestamp getSupervisestarttime() {
		return supervisestarttime;
	}

	public void setSupervisestarttime(Timestamp supervisestarttime) {
		this.supervisestarttime = supervisestarttime;
	}

	public Timestamp getSupervisestoptime() {
		return supervisestoptime;
	}

	public void setSupervisestoptime(Timestamp supervisestoptime) {
		this.supervisestoptime = supervisestoptime;
	}

	public String getSupervisetype() {
		return supervisetype;
	}

	public void setSupervisetype(String supervisetype) {
		this.supervisetype = supervisetype;
	}

	
	
	
}

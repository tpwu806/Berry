package com.iot.device.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Device_Device")//设备表
public class Device implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEVICEID")
	private Integer deviceid;//设备编号
	
	@Column(name = "DEVICE_NAME")
	private String devicename;//设备名称
	
	@Column(name = "DEVICE_IP")
	private String deviceip;//连接IP地址
	
	@Column(name = "DEVICE_PORT")
	private String deviceport;//监听端口号
	
	@Column(name = "DEVICE_STATUS")
	private String devicestatus;//设备状态
	
	@Column(name = "DEVICE_TYPE")
	private String devicetype;//设备类型
	
	@Column(name = "DEVICE_SENSOR_NUMBER")
	private Integer devicesensornumber;//传感器个数

	

	public Integer getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getDeviceip() {
		return deviceip;
	}

	public void setDeviceip(String deviceip) {
		this.deviceip = deviceip;
	}

	public String getDeviceport() {
		return deviceport;
	}

	public void setDeviceport(String deviceport) {
		this.deviceport = deviceport;
	}

	public String getDevicestatus() {
		return devicestatus;
	}

	public void setDevicestatus(String devicestatus) {
		this.devicestatus = devicestatus;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public Integer getDevicesensornumber() {
		return devicesensornumber;
	}

	public void setDevicesensornumber(Integer devicesensornumber) {
		this.devicesensornumber = devicesensornumber;
	}

	
	
}

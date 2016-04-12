package com.iot.device.dto;

public class DeviceDO {

	private Integer id;//设备编号
	
	private String devicename;//设备名称
	
	private String deviceip;//连接IP地址
	
	private String deviceport;//监听端口号
	
	private String devicestatus;//设备状态
	
	private String devicetype;//设备型号
	
	private Integer sensornumber;//传感器个数

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getSensornumber() {
		return sensornumber;
	}

	public void setSensornumber(Integer sensornumber) {
		this.sensornumber = sensornumber;
	}
	
	
	
}

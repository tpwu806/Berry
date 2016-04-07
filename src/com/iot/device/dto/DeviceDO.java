package com.iot.device.dto;

public class DeviceDO {

	private Integer id;//设备编号
	
	private String devicename;//设备名称
	
	private String deviceip;//连接IP地址
	
	private String deviceport;//监听端口号

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
	
	
}

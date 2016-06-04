package com.iot.device.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE_DEVICE")//设备表
public class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3574550010937711395L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//设备编号
	
	@Column(name = "DEVICENAME")
	private String devicename;//设备名称
	
	@Column(name = "DEVICEIP")
	private String deviceip;//连接IP地址
	
	@Column(name = "DEVICEPORT")
	private String deviceport;//监听端口号
	
	@Column(name = "DEVICESTATUS")
	private Integer devicestatus;//设备状态
	
	@Column(name = "DEVICETYPE")
	private String devicetype;//设备型号
	
	@Column(name = "SENSORNUMBER")
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

	public Integer getDevicestatus() {
		return devicestatus;
	}

	public void setDevicestatus(Integer devicestatus) {
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

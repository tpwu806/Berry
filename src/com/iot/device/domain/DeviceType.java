package com.iot.device.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE_TYPE")//设备型号表
public class DeviceType implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEVICETYPEID")
	private Integer devicetypeid;//编号
	
	@Column(name = "DEVICETYPE")
	private String deviceTYPE;//设备型号

	public Integer getDevicetypeid() {
		return devicetypeid;
	}

	public void setDevicetypeid(Integer devicetypeid) {
		this.devicetypeid = devicetypeid;
	}

	public String getDeviceTYPE() {
		return deviceTYPE;
	}

	public void setDeviceTYPE(String deviceTYPE) {
		this.deviceTYPE = deviceTYPE;
	}
	
	
}

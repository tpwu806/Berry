package com.iot.sensor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iot.device.domain.Device;

@Entity
@Table(name = "SENSOR_SENSOR")//传感器表
public class Sensor implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//传感器编号
	
	@Column(name = "SENSORNAME")
	private String sensorname;//传感器名称
	
	@Column(name = "SENSORTYPE")
	private String sensortype;//传感器类型
	
	@Column(name = "SENSORPARAMETER")
	private String sensorparameter;//传感器参数名
	
	@Column(name = "SENSORPARAMETER2")
	private String sensorparameter2;//传感器参数2名
	
	@Column(name = "DEVICEID")
	private Integer deviceid;//设备编号
	
	@ManyToOne(targetEntity = Device.class)
	@JoinColumn(name = "DEVICEID", updatable = false, insertable = false)
	private Device device;//多对一设备集合

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

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	
	
}

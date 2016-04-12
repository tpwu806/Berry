package com.iot.sensor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SENSOR_SENSOR")//传感器表
public class Sensor implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SENSORID")
	private Integer sensorid;//传感器编号
	
	@Column(name = "SENSORNAME")
	private String sensorname;//传感器名称
	
	@Column(name = "SENSORTYPE")
	private String sensortype;//传感器类型
	
	@Column(name = "SENSORPARAMETER")
	private String sensorparameter;//传感器参数名
	
	@Column(name = "SENSORPARAMETER2")
	private String sensorparameter2;//传感器参数2名

	public Integer getSensorid() {
		return sensorid;
	}

	public void setSensorid(Integer sensorid) {
		this.sensorid = sensorid;
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

	
}

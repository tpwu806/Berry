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
	@Column(name = "ID")
	private Integer id;//传感器编号
	
	@Column(name = "SENSORNAME")
	private String sensorname;//传感器名称
	
	@Column(name = "SENSORTYPE")
	private String sensortype;//传感器类型
	
	@Column(name = "SENSORPARAMETER")
	private Integer sensorparameter;//传感器参数名
	
	@Column(name = "SENSORPARAMETER2")
	private Integer sensorparameter2;//传感器参数2名

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

	public Integer getSensorparameter() {
		return sensorparameter;
	}

	public void setSensorparameter(Integer sensorparameter) {
		this.sensorparameter = sensorparameter;
	}

	public Integer getSensorparameter2() {
		return sensorparameter2;
	}

	public void setSensorparameter2(Integer sensorparameter2) {
		this.sensorparameter2 = sensorparameter2;
	}

	
}

package com.iot.supervise.domain;

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
	
	@Column(name = "SENSOR_NAME")
	private String sensorname;//传感器名称
	
	@Column(name = "SENSOR_PARAMITER")
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

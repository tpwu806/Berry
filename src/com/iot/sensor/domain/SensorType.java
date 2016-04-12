package com.iot.sensor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SENSOR_TYPE")//传感器型号表
public class SensorType implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SENSORTYPEID")
	private Integer sensortypeid;//编号
	
	@Column(name = "SENSORTYPE")
	private Integer sensorTYPE;//传感器型号

	public Integer getSensortypeid() {
		return sensortypeid;
	}

	public void setSensortypeid(Integer sensortypeid) {
		this.sensortypeid = sensortypeid;
	}

	public Integer getSensorTYPE() {
		return sensorTYPE;
	}

	public void setSensorTYPE(Integer sensorTYPE) {
		this.sensorTYPE = sensorTYPE;
	}
	
	
}

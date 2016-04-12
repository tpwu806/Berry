package com.iot.supervise.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK_TASK")//任务表
public class Task implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//任务编号
	
	@Column(name = "DEVICENAME")
	private Integer devicename;//设备名称
	
	@Column(name = "SENSORNAME")
	private Integer sensorname;//传感器名称
	
	@Column(name = "STARTTIME")
	private Timestamp starttime;//任务开始时间
	
	@Column(name = "STOPTIME")
	private Timestamp stoptime;//任务结束时间
	
	@Column(name = "TASKSTATUS")
	private String taskstatus;//任务状态
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public Integer getDevicename() {
		return devicename;
	}

	public void setDevicename(Integer devicename) {
		this.devicename = devicename;
	}

	public Integer getSensorname() {
		return sensorname;
	}

	public void setSensorname(Integer sensorname) {
		this.sensorname = sensorname;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getStoptime() {
		return stoptime;
	}

	public void setStoptime(Timestamp stoptime) {
		this.stoptime = stoptime;
	}
	
	
}

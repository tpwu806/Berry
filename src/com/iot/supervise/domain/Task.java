package com.iot.supervise.domain;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "TASK_TASK")//任务表
public class Task implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//任务编号
	
	
	@Column(name = "DEVICEID")
	private Integer deviceid;//设备id
	
	@Column(name = "STARTTIME")
	private Timestamp starttime;//任务开始时间
	
	@Column(name = "STOPTIME")
	private Timestamp stoptime;//任务结束时间
	
	@Column(name = "TASKSTATUS")
	private Integer taskstatus;//任务状态
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public Integer getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}

	public Integer getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(Integer taskstatus) {
		this.taskstatus = taskstatus;
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

package com.iot.supervise.dto;

import java.sql.Timestamp;

public class TaskDO {

	private Integer id;//任务编号
	private Integer deviceid;//设备id
	private Timestamp starttime;//任务开始时间
	private Timestamp stoptime;//任务结束时间
	private Integer taskstatus;//任务状态
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}
	public Timestamp getStarttime() {
		return starttime;
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
	public Integer getTaskstatus() {
		return taskstatus;
	}
	public void setTaskstatus(Integer taskstatus) {
		this.taskstatus = taskstatus;
	}

}

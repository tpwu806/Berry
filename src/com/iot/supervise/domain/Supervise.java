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
import com.iot.sensor.domain.Sensor;
/**
 * *****************************************************************
 * Created on 2016年4月7日  16:40:10
 * @author wutongpeng (mailto:wutongpeng803@163.com)
 * 功能说明：监控器dao
 *
 * 修改历史
 * Revision 1.0.1   2016年 月 日  10:06:10 by 
 * Update: ------  ------
 * 
 * 
 ******************************************************************
 */
@Entity
@Table(name = "SUPERVISE_SUPERVISE")//设备表名
public class Supervise implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//任务号
	
	@Column(name = "SUPERVISE_NAME")
	private String supervisename;//任务名称
	
	@Column(name = "DEVICENAME")
	private Integer devicename;//多对一设备名称
	@ManyToOne(targetEntity = Device.class)
	@JoinColumn(name = "DEVICENAME", updatable = false, insertable = false)
	private Device device;//设备集合
	
	@Column(name = "SENSORNAME")
	private Integer sensorname;//多对一传感器名称
	@ManyToOne(targetEntity = Sensor.class)
	@JoinColumn(name = "SENSORNAME", updatable = false, insertable = false)
	private Sensor sensor;//传感器集合
	
	@Column(name = "SUPERVISE_STARTIME")
	private Timestamp supervisestarttime;//开启时间
	
	@Column(name = "SUPERVISE_STOPTIME")
	private Timestamp supervisestoptime;//关闭时间
	
	@Column(name = "SUPERVISE_TYPE")
	private String supervisetype;//开关状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupervisename() {
		return supervisename;
	}

	public void setSupervisename(String supervisename) {
		this.supervisename = supervisename;
	}

	public Integer getDevicename() {
		return devicename;
	}

	public void setDevicename(Integer devicename) {
		this.devicename = devicename;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Integer getSensorname() {
		return sensorname;
	}

	public void setSensorname(Integer sensorname) {
		this.sensorname = sensorname;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Timestamp getSupervisestarttime() {
		return supervisestarttime;
	}

	public void setSupervisestarttime(Timestamp supervisestarttime) {
		this.supervisestarttime = supervisestarttime;
	}

	public Timestamp getSupervisestoptime() {
		return supervisestoptime;
	}

	public void setSupervisestoptime(Timestamp supervisestoptime) {
		this.supervisestoptime = supervisestoptime;
	}

	public String getSupervisetype() {
		return supervisetype;
	}

	public void setSupervisetype(String supervisetype) {
		this.supervisetype = supervisetype;
	}

	
	
}

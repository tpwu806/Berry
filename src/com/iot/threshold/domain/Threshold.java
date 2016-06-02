package com.iot.threshold.domain;

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
 * 功能说明：阈值daomain
 *
 * 修改历史
 * Revision 1.0.1   2016年 月 日  10:06:10 by 
 * Update: ------  ------
 * 
 * 
 ******************************************************************
 */
@Entity
@Table(name = "THRESHOLD_THRESHOLD")//阈值设置表
public class Threshold implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4952756336977332943L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//阈值编号

	@Column(name = "MAXTEMPERATURE")
	private Integer maxtemperature;//
	
	@Column(name = "MINTEMPERATURE")
	private Integer mintemperature;//

	@Column(name = "MAXHUMIDITY")
	private Integer  maxhumidity;//
	
	@Column(name = "MINHUMIDITY")
	private Integer  minhumidity;//

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMaxtemperature() {
		return maxtemperature;
	}

	public void setMaxtemperature(Integer maxtemperature) {
		this.maxtemperature = maxtemperature;
	}

	public Integer getMintemperature() {
		return mintemperature;
	}

	public void setMintemperature(Integer mintemperature) {
		this.mintemperature = mintemperature;
	}

	public Integer getMaxhumidity() {
		return maxhumidity;
	}

	public void setMaxhumidity(Integer maxhumidity) {
		this.maxhumidity = maxhumidity;
	}

	public Integer getMinhumidity() {
		return minhumidity;
	}

	public void setMinhumidity(Integer minhumidity) {
		this.minhumidity = minhumidity;
	}

	
}

package com.iot.raspberry.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DHT11_DHT11")
public class Dht11 implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "USER_NAME")
	private String dht11name;
	
	@Column(name = "DHT11_TEM")
	private Integer dht11tem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDht11name() {
		return dht11name;
	}

	public void setDht11name(String dht11name) {
		this.dht11name = dht11name;
	}

	public Integer getDht11tem() {
		return dht11tem;
	}

	public void setDht11tem(Integer dht11tem) {
		this.dht11tem = dht11tem;
	}
	
	
}

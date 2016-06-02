package com.iot.threshold.dto;

public class ThresholdDO {

	private Integer id;//	
    private Integer maxtemperature;//	
	private Integer mintemperature;//
	private Integer  maxhumidity;//	
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

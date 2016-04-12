package com.iot.sensor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.sensor.domain.SensorType;

public interface SensorTypeDAO extends JpaRepository<SensorType, Integer> {

}

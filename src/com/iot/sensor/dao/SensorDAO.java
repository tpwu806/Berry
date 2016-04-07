package com.iot.sensor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.sensor.domain.Sensor;

public interface SensorDAO extends JpaRepository<Sensor, Integer>{

}

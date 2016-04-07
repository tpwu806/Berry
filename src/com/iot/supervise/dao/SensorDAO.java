package com.iot.supervise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.supervise.domain.Sensor;

public interface SensorDAO extends JpaRepository<Sensor, Integer>{

}

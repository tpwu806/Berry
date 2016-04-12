package com.iot.device.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.device.domain.DeviceType;

public interface DeviceTypeDAO extends JpaRepository<DeviceType, Integer> {

}

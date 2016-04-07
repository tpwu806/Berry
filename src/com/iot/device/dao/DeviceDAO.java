package com.iot.device.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.device.domain.Device;


public interface DeviceDAO extends JpaRepository<Device, Integer>{

}

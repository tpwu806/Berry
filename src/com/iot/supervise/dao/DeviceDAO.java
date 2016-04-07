package com.iot.supervise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.supervise.domain.Device;


public interface DeviceDAO extends JpaRepository<Device, Integer>{

}

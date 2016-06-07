package com.iot.threshold.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iot.threshold.domain.Threshold;


public interface ThresholdDAO extends JpaRepository<Threshold, Integer>{
	
	@Query("select s from Threshold s ") 
    Threshold findOrderBySupervisetimeDesc();
}

package com.iot.supervise.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.iot.supervise.domain.Supervise;


public interface SuperviseDAO extends JpaRepository<Supervise, Integer>{
	
	@Query("select s from Supervise s order by Supervisetime desc") 
	public abstract List<Supervise> findOrderBySupervisetimeDesc();
}

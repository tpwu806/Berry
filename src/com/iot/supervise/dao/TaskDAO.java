package com.iot.supervise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.supervise.domain.Task;

public interface TaskDAO extends JpaRepository<Task, Integer> {

}

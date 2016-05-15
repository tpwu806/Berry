package com.iot.supervise.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iot.supervise.domain.Task;

public interface TaskDAO extends JpaRepository<Task, Integer> {
	
	@Query("select t from Task t where t.taskstatus = :taskstatus ") 
	public abstract Task findByTaskStatus(@Param("taskstatus")Integer taskstatus);
	
	@Query("select t from Task t where t.deviceid = :deviceid ") 
	public abstract Task findByDeviceId(@Param("deviceid")Integer deviceid);
	
	@Query("select t from Task t order by starttime desc") 
	public abstract List<Task> findTask();
}

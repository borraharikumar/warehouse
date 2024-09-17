package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.WhUserType;

public interface IWhUserTypeRepository extends JpaRepository<WhUserType, String> {
	
	@Query("SELECT COUNT(userCode) FROM WhUserType WHERE userCode=:userCode")
	public Integer getUserCodeCount(String userCode);
	
	@Query("SELECT COUNT(userEmail) FROM WhUserType WHERE userEmail=:email")
	public Integer getUserEmailCount(String email);
	
	@Query("SELECT COUNT(userIdNumber) FROM WhUserType WHERE userEmail=:userIdNumber")
	public Integer getUserIdNumberCount(String userIdNumber);
	
	@Query("SELECT userType, COUNT(userType) FROM WhUserType GROUP BY userType")
	public List<Object[]> getUserTypeAndCount();
	
	@Query("SELECT id, userCode FROM WhUserType WHERE userType=:userType")
	public List<Object[]> getUserIdAndCodeByType(String userType);

}

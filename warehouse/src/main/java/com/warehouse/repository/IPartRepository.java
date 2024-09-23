package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.Part;

public interface IPartRepository extends JpaRepository<Part, String> {
	
	@Query("SELECT COUNT(partCode) FROM Part WHERE partCode=:partCode")
	public Integer getCountOfOrderCode(String partCode);
	
	@Query("SELECT id, partCode FROM Part")
	public List<Object[]> getPartIdAndCode();

}

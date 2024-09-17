package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.OrderMethod;

public interface IOrderMethodRepository extends JpaRepository<OrderMethod, String> {
	
	@Query("SELECT COUNT(orderCode) FROM OrderMethod WHERE orderCode=:orderCode")
	public Integer getOrderCodeCount(String orderCode);
	
	@Query("SELECT orderMode, COUNT(orderMode) FROM OrderMethod GROUP BY orderMode")
	public List<Object[]> getOrderModeAndCount();
	
	@Query("SELECT id, orderCode FROM OrderMethod WHERE orderMode=:orderMode")
	public List<Object[]> getOrderIdAndCodeByMode(String orderMode);

}

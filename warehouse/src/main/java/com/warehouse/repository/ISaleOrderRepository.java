package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.SaleOrder;

public interface ISaleOrderRepository extends JpaRepository<SaleOrder, String> {
	
	@Query("SELECT COUNT(:type) FROM SaleOrder WHERE :type=:value")
	public Integer getCountOfValue(String type, String value);
	
	@Modifying
	@Query("UPDATE SaleOrder SET status=:status WHERE id=:id")
	public void updateStatus(String id, String status);

}

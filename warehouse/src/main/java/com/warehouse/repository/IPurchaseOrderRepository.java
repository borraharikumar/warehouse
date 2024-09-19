package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.PurchaseOrder;

public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, String> {
	
	@Query("SELECT qltyCheck, COUNT(qltyCheck) FROM PurchaseOrder GROUP BY qltyCheck")
	public List<Object[]> getPurchaseOrderCountByQltyCheck();

}

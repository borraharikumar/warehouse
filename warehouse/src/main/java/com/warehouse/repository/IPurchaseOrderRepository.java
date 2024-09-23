package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.PurchaseOrder;

public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, String> {
	
	@Query("SELECT qltyCheck, COUNT(qltyCheck) FROM PurchaseOrder GROUP BY qltyCheck")
	public List<Object[]> getPurchaseOrderCountByQltyCheck();
	
	@Query("SELECT COUNT(orderCode) FROM PurchaseOrder WHERE orderCode=:orderCode")
	public Integer getCountOfOrderCode(String orderCode);
	
	@Query("SELECT COUNT(refNumber) FROM PurchaseOrder WHERE refNumber=:refNumber")
	public Integer getCountOfRefNumber(String refNumber);
	
	@Query("SELECT id, orderCode FROM PurchaseOrder")
	public List<Object[]> getOrderIdAndCode();
	
	@Modifying
	@Query("UPDATE PurchaseOrder po SET po.status=:status WHERE po.id=:id")
	public void changeOrderStatusById(String id, String status);

}

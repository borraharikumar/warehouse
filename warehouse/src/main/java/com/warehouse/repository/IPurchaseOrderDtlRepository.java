package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.PurchaseOrderDtl;

public interface IPurchaseOrderDtlRepository extends JpaRepository<PurchaseOrderDtl, Long> {
	
	@Query("SELECT dtl FROM PurchaseOrderDtl dtl JOIN dtl.purchaseOrder AS order WHERE order.id=:poId")
	public List<PurchaseOrderDtl> getPurchaseOrderDtlsByOrderId(String poId);

}

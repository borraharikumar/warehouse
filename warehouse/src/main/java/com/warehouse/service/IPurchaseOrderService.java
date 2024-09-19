package com.warehouse.service;

import java.util.List;

import com.warehouse.model.PurchaseOrder;

public interface IPurchaseOrderService {
	
	public String insertPurchaseOrder(PurchaseOrder purchaseOrder);
	public PurchaseOrder getOnePurchaseOrder(String id);
	public String updatePurchaseOrder(PurchaseOrder purchaseOrder);
	public String deletePurchaseOrder(String id);
	public List<PurchaseOrder> getPurchaseOrderData();
	
	public List<Object[]> getPurchaseOrderCountByQltyCheck();

}

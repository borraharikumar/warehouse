package com.warehouse.service;

import java.util.List;
import java.util.Map;

import com.warehouse.model.PurchaseOrder;
import com.warehouse.model.PurchaseOrderDtl;

public interface IPurchaseOrderService {
	
	//********************************* PurchaseOrder methods *********************************//
	public String insertPurchaseOrder(PurchaseOrder purchaseOrder);
	public PurchaseOrder getOnePurchaseOrder(String id);
	public String updatePurchaseOrder(PurchaseOrder purchaseOrder);
	public String deletePurchaseOrder(String id);
	public List<PurchaseOrder> getPurchaseOrderData();
	
	public List<Object[]> getPurchaseOrderCountByQltyCheck();
	public Boolean isOrderCodeExist(String orderCode);
	public Boolean isRefNumberExist(String refNumber);
	public Map<String, String> getPurchaseOrderIdAndCode();
	public void changePurchaseOrderStatus(String id, String status);
	public Map<String, String> getOrderIdAndCodeByOrderStatus(String status);
	
	//******************************** PurchaseOrderDtl methods *******************************//
	public void inserPurchaseOrderDtl(PurchaseOrderDtl purchaseOrderDtl);
	public List<PurchaseOrderDtl> getPurchaseOrderDtlDataByPoId(String poId);
	public void deletePurchaseOrderDtl(Long id);

}

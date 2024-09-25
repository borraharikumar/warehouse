package com.warehouse.service;

import java.util.List;

import com.warehouse.model.SaleOrder;
import com.warehouse.model.SaleOrderDtl;

public interface ISaleOrderService {
	
	public SaleOrder saveSaleOrder(SaleOrder saleOrder);
	public SaleOrder getOneSaleOrder(String id);
	public SaleOrder updateSaleOrder(SaleOrder saleOrder);
	public void deleteSaleOrder(String id);
	public List<SaleOrder> getSaleOrderData();
	
	//************************************ Validation Operations ************************************//
	public Boolean isValueAvailable(String type, String value);
	
	//************************************* Screen#2 Operations *************************************//
	public void updateStatus(String id, String status);
	public List<SaleOrderDtl> getSaleOrderDtlData(String soId);
	public void addSaleOrderDtl(SaleOrderDtl dtl);
	public void removeSaleOrderDtl(Integer sodId);

}

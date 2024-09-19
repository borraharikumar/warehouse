package com.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.PurchaseOrderNotFoundException;
import com.warehouse.model.PurchaseOrder;
import com.warehouse.repository.IPurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private IPurchaseOrderRepository purchaseOrderRepository;
	
	@Override
	public String insertPurchaseOrder(PurchaseOrder purchaseOrder) {
		return purchaseOrderRepository.save(purchaseOrder).getId();
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(String id) {
		return purchaseOrderRepository.findById(id)
				.orElseThrow(()->new PurchaseOrderNotFoundException("PurchaseOrder not found with id '" + id + "'"));
	}

	@Override
	public String updatePurchaseOrder(PurchaseOrder purchaseOrder) {
		if(getOnePurchaseOrder(purchaseOrder.getId())!=null) purchaseOrderRepository.save(purchaseOrder);
		return "PurchaseOrder with id '" + purchaseOrder.getId() + "' details updated successfully...!";
	}

	@Override
	public String deletePurchaseOrder(String id) {
		if(getOnePurchaseOrder(id)!=null) purchaseOrderRepository.deleteById(id);
		return "PurchaseOrder with id '" + id + "' deleted successfully...!";
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderData() {
		List<PurchaseOrder> list = purchaseOrderRepository.findAll();
		return list!=null?list:null;
	}

	@Override
	public List<Object[]> getPurchaseOrderCountByQltyCheck() {
		return purchaseOrderRepository.getPurchaseOrderCountByQltyCheck();
	}

}

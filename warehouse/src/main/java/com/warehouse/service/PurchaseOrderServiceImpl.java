package com.warehouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.PurchaseOrderNotFoundException;
import com.warehouse.model.PurchaseOrder;
import com.warehouse.model.PurchaseOrderDtl;
import com.warehouse.repository.IPurchaseOrderDtlRepository;
import com.warehouse.repository.IPurchaseOrderRepository;
import com.warehouse.util.CollectionUtil;

import jakarta.transaction.Transactional;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private IPurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private IPurchaseOrderDtlRepository purchaseOrderDtlRepository;
	
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

	@Override
	public Boolean isOrderCodeExist(String orderCode) {
		return purchaseOrderRepository.getCountOfOrderCode(orderCode)>0;
	}

	@Override
	public Boolean isRefNumberExist(String refNumber) {
		return purchaseOrderRepository.getCountOfRefNumber(refNumber)>0;
	}

	@Override
	public Map<String, String> getPurchaseOrderIdAndCode() {
		return CollectionUtil.converListToMap(purchaseOrderRepository.getOrderIdAndCode());
	}

	@Override
	public void inserPurchaseOrderDtl(PurchaseOrderDtl purchaseOrderDtl) {
		purchaseOrderDtlRepository.save(purchaseOrderDtl);
	}

	@Override
	public List<PurchaseOrderDtl> getPurchaseOrderDtlDataByPoId(String poId) {
		List<PurchaseOrderDtl> list = purchaseOrderDtlRepository.getPurchaseOrderDtlsByOrderId(poId);
		return list.isEmpty()?null:list;
	}

	@Override
	public void deletePurchaseOrderDtl(Long id) {
		purchaseOrderDtlRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void changePurchaseOrderStatus(String id, String status) {
		purchaseOrderRepository.changeOrderStatusById(id, status);
	}

	@Override
	public Map<String, String> getOrderIdAndCodeByOrderStatus(String status) {
		return CollectionUtil.converListToMap(purchaseOrderRepository.getOrderIdAndCodeByOrderStatus(status));
	}

}

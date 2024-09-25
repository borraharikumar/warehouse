package com.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.SaleOrderNotFoundException;
import com.warehouse.model.SaleOrder;
import com.warehouse.model.SaleOrderDtl;
import com.warehouse.repository.ISaleOrderDtlReposirory;
import com.warehouse.repository.ISaleOrderRepository;

import jakarta.transaction.Transactional;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {
	
	@Autowired
	private ISaleOrderRepository saleOrderRepository;
	
	@Autowired
	private ISaleOrderDtlReposirory saleOrderDtlReposirory;

	@Override
	public SaleOrder saveSaleOrder(SaleOrder saleOrder) {
		return saleOrderRepository.save(saleOrder);
	}

	@Override
	public SaleOrder getOneSaleOrder(String id) {
		return saleOrderRepository.findById(id)
				.orElseThrow(()->new SaleOrderNotFoundException("SaleOrder not found with id '" + id + "'"));
	}

	@Override
	public SaleOrder updateSaleOrder(SaleOrder saleOrder) {
		SaleOrder so = getOneSaleOrder(saleOrder.getId());
		if(so!=null) so=saveSaleOrder(saleOrder);
		return so;
	}

	@Override
	public void deleteSaleOrder(String id) {
		SaleOrder so = getOneSaleOrder(id);
		if(so!=null) saleOrderRepository.deleteById(id);
	}

	@Override
	public List<SaleOrder> getSaleOrderData() {
		List<SaleOrder> list = saleOrderRepository.findAll();
		return list.isEmpty()?null:list;
	}

	@Override
	public Boolean isValueAvailable(String type, String value) {
		return saleOrderRepository.getCountOfValue(type, value)>0;
	}

	@Transactional
	@Override
	public void updateStatus(String id, String status) {
		saleOrderRepository.updateStatus(id, status);
	}

	@Override
	public List<SaleOrderDtl> getSaleOrderDtlData(String soId) {
		List<SaleOrderDtl> list = saleOrderDtlReposirory.getSaleOrderDtlDataBySoId(soId);
		return list.isEmpty()?null:list;
	}

	@Override
	public void addSaleOrderDtl(SaleOrderDtl dtl) {
		saleOrderDtlReposirory.save(dtl);
	}

	@Override
	public void removeSaleOrderDtl(Integer sodId) {
		saleOrderDtlReposirory.deleteById(sodId);
	}

}

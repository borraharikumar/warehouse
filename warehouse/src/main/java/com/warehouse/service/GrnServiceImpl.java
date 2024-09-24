package com.warehouse.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.GrnNotFoundException;
import com.warehouse.model.Grn;
import com.warehouse.model.GrnDtl;
import com.warehouse.model.PurchaseOrderDtl;
import com.warehouse.repository.IGrnDtlRepository;
import com.warehouse.repository.IGrnRepository;

import jakarta.transaction.Transactional;

@Service
public class GrnServiceImpl implements IGrnService {

	@Autowired
	private IGrnRepository grnRepository;
	
	@Autowired
	private IGrnDtlRepository grnDtlRepository;
	
	@Override
	public Grn insertGrn(Grn grn) {
		return grnRepository.save(grn);
	}

	@Override
	public List<Grn> getGrnData() {
		List<Grn> list = grnRepository.findAll();
		return list.isEmpty()?null:list;
	}

	@Override
	public Grn getOneGrn(String grnId) {
		return grnRepository.findById(grnId)
							.orElseThrow(()->new GrnNotFoundException("GRN not found with id '" + grnId + "'"));
	}

	@Override
	public void insertGrnDtls(List<PurchaseOrderDtl> orderDtls, Grn grn) {
		List<GrnDtl> grnDtls = new LinkedList<GrnDtl>();
		orderDtls.forEach(x->{
			GrnDtl grnDtl = new GrnDtl();
			Double pPrice = x.getPart().getPartBasePrice();
			Integer qty = x.getQty();
			Double lotValue = (pPrice*qty) + (pPrice*qty*18)/100;
			grnDtl.setItemCode(x.getPart().getPartCode());
			grnDtl.setBaseCost(pPrice);
			grnDtl.setQty(qty);
			grnDtl.setLotValue(lotValue);
			grnDtl.setGrn(grn);
			grnDtl.setStatus(null);
			grnDtls.add(grnDtl);
		});
		grnDtlRepository.saveAll(grnDtls);
	}

	@Override
	public List<GrnDtl> getGrnDtlsByGrnId(String grnId) {
		return grnDtlRepository.getGrnDtlsByGrnId(grnId);
	}

	@Transactional
	@Override
	public void updateGrnDtlStatusById(String status, Integer grnDtlId) {
		grnDtlRepository.updateGrnDtlStatusById(status, grnDtlId);
	}

}

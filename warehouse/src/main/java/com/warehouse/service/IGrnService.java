package com.warehouse.service;

import java.util.List;

import com.warehouse.model.Grn;
import com.warehouse.model.GrnDtl;
import com.warehouse.model.PurchaseOrderDtl;

public interface IGrnService {

	public Grn insertGrn(Grn grn);
	public List<Grn> getGrnData();
	public Grn getOneGrn(String grnId);
	
	public void insertGrnDtls(List<PurchaseOrderDtl> orderDtls, Grn grn);
	public List<GrnDtl> getGrnDtlsByGrnId(String grnId);
	public void updateGrnDtlStatusById(String status, Integer grnDtlId);

}

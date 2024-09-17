package com.warehouse.service;

import java.util.List;
import java.util.Map;

import com.warehouse.model.Uom;

public interface IUomService {
	
	//CURD Operations
	public String insertUom(Uom uom);
	public Uom getUom(String id);
	public String updateUom(Uom uom);
	public String deleteUom(String id);
	public List<Uom> getUomData();
	
	public Boolean isUomModelExist(String uomModel);
	public List<Object[]> getUomTypeCount();
	public Map<String, String> getUomIdAndModel();
	
}

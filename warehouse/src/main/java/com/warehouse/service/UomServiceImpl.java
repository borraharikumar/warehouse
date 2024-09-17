package com.warehouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.UomNotFoundException;
import com.warehouse.model.Uom;
import com.warehouse.repository.IUomRepository;
import com.warehouse.util.CollectionUtil;

@Service
public class UomServiceImpl implements IUomService {
	
	@Autowired
	private IUomRepository uomRepository;

	@Override
	public String insertUom(Uom uom) {
		return "UOM is saved with id '"+ uomRepository.save(uom).getId() +"' successfully...!";
	}

	@Override
	public Uom getUom(String id) {
		return uomRepository.findById(id)
				.orElseThrow(()->new UomNotFoundException("UOM not exist with id :: '" + id +"'"));
	}

	@Override
	public String updateUom(Uom uom) {
		if(getUom(uom.getId())!=null) {
			insertUom(uom);
		}
		return "UOM with ID '"+ uom.getId() +"' details updated successfully...!";
	}

	@Override
	public String deleteUom(String id) {
		if(getUom(id)!=null) {
			uomRepository.deleteById(id);
		}
		return "UOM with ID '"+ id +"' deleted successfully...!";
	}

	@Override
	public List<Uom> getUomData() {
		List<Uom> uomData = uomRepository.findAll();
		if(uomData.isEmpty()) {
			return null;
		} else {
			return uomData;
		}
	}

	@Override
	public Boolean isUomModelExist(String uomModel) {
		return uomRepository.getUomModelCount(uomModel)==0?false:true;
	}

	@Override
	public List<Object[]> getUomTypeCount() {
		return uomRepository.getUomTypeAndCount();
	}

	@Override
	public Map<String, String> getUomIdAndModel() {
		return CollectionUtil.converListToMap(uomRepository.getUomIdAndModel());
	}

}

package com.warehouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.ShipmentTypeNotFoundException;
import com.warehouse.model.ShipmentType;
import com.warehouse.repository.IShipmentTypeRepository;
import com.warehouse.util.CollectionUtil;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {
	
	@Autowired
	private IShipmentTypeRepository shipmentTypeRepository;

	@Override
	public String insertShipmentType(ShipmentType st) {
		return "Shipment saved with id '" + shipmentTypeRepository.save(st).getId() + "' successfully...!";
	}

	@Override
	public String updateShipmentType(ShipmentType st) {
		if(getShipmentById(st.getId())!=null) shipmentTypeRepository.save(st);
		return "Shipment with id '" + st.getId() + "' details updated successfully...!";
	}

	@Override
	public String deleteShipmentType(String id) {
		if(getShipmentById(id)!=null) shipmentTypeRepository.deleteById(id);
		return "Shipment with id '" + id + "' deleted successfully...!";
	}

	@Override
	public ShipmentType getShipmentById(String id) {
		return shipmentTypeRepository.findById(id)
				.orElseThrow(()->new ShipmentTypeNotFoundException("Shipment not found with id '" + id + "'"));
	}

	@Override
	public List<ShipmentType> getShipmentData() {
		List<ShipmentType> list = shipmentTypeRepository.findAll();
		if(list.isEmpty()) return null;
		else return list;
	}

	@Override
	public Boolean isShipmentCodeExist(String code) {
		return shipmentTypeRepository.getShipmentCodeCount(code)==0?false:true;
	}

	@Override
	public List<Object[]> getShipmentTypeAndCount() {
		return shipmentTypeRepository.getShipmentModeAndCount();
	}

	@Override
	public Map<String, String> getShipmentIdAndCode() {
		return CollectionUtil.converListToMap(shipmentTypeRepository.getShipmentIdAndCode());
	}

}

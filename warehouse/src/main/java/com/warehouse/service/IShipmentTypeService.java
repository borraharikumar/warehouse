package com.warehouse.service;

import java.util.List;
import java.util.Map;

import com.warehouse.model.ShipmentType;

public interface IShipmentTypeService {
	
	public String insertShipmentType(ShipmentType st);
	public String updateShipmentType(ShipmentType st);
	public String deleteShipmentType(String id);
	public ShipmentType getShipmentById(String id);
	public List<ShipmentType> getShipmentData();
	
	public Boolean isShipmentCodeExist(String code);
	public List<Object[]> getShipmentTypeAndCount();
	public Map<String, String> getShipmentIdAndCode();

}

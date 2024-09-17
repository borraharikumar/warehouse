package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.ShipmentType;

public interface IShipmentTypeRepository extends JpaRepository<ShipmentType, String> {
	
	@Query("SELECT COUNT(shipmentCode) FROM ShipmentType WHERE shipmentCode=:code")
	public Integer getShipmentCodeCount(String code);
	
	@Query("SELECT shipmentMode, COUNT(shipmentMode) FROM ShipmentType GROUP BY shipmentMode")
	public List<Object[]> getShipmentModeAndCount();
	
	@Query("SELECT id, shipmentCode FROM ShipmentType WHERE enableShipment='YES'")
	public List<Object[]> getShipmentIdAndCode();

}

package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.Uom;

public interface IUomRepository extends JpaRepository<Uom, String> {
	
	@Query("SELECT COUNT(uomModel) FROM Uom WHERE uomModel=:uomModel")
	public Integer getUomModelCount(String uomModel);
	
	@Query("SELECT uomType, COUNT(uomType) FROM Uom GROUP BY uomType")
	public List<Object[]> getUomTypeAndCount();
	
	@Query("SELECT id, uomModel FROM Uom")
	public List<Object[]> getUomIdAndModel();

}

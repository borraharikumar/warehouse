package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.SaleOrderDtl;

public interface ISaleOrderDtlReposirory extends JpaRepository<SaleOrderDtl, Integer> {
	
	@Query("SELECT dtl FROM SaleOrderDtl dtl JOIN dtl.so AS so WHERE so.id=:soId")
	public List<SaleOrderDtl> getSaleOrderDtlDataBySoId(String soId);

}

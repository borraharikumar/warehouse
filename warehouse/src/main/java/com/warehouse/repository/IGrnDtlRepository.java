package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.GrnDtl;

public interface IGrnDtlRepository extends JpaRepository<GrnDtl, Integer> {
	
	@Query("SELECT dtl FROM GrnDtl dtl JOIN dtl.grn AS grn WHERE grn.id=:grnId")
	public List<GrnDtl> getGrnDtlsByGrnId(String grnId);
	
	@Modifying
	@Query("UPDATE GrnDtl dtl SET dtl.status=:status WHERE dtl.id=:grnDtlId")
	public void updateGrnDtlStatusById(String status, Integer grnDtlId);

}

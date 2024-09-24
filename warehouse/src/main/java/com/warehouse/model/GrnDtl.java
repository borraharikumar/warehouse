package com.warehouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "grn_dtl_tab")
public class GrnDtl {
	
	@Id
	@GeneratedValue(generator = "grn_id_gen")
	@SequenceGenerator(name = "grn_id_gen", sequenceName = "grn_id_seq", initialValue = 1, allocationSize = 1)
	private Integer id;
	
	@Column(name = "item_code_col", nullable = false)
	private String itemCode;
	@Column(name = "base_cost_col", nullable = false)
	private Double baseCost;
	@Column(name = "item_qty_col", nullable = false)
	private Integer qty;
	@Column(name = "lot_value_col", nullable = false)
	private Double lotValue;
	@Column(name = "lot_status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "grn_id_fk_col", nullable = false)
	private Grn grn;

}

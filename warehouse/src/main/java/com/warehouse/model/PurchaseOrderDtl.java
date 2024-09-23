package com.warehouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "po_dtl_tab")
public class PurchaseOrderDtl {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "part_id_fk_col", nullable = false)
	private Part part;
	
	@Column(name = "po_qty_col", nullable = false)
	private Integer qty;
	
	@ManyToOne
	@JoinColumn(name = "po_id_fk_col", nullable = false)
	private PurchaseOrder purchaseOrder;

}

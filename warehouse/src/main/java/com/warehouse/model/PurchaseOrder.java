package com.warehouse.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("deprecation")
@Data
@Entity
@Table(name = "purchase_order_tab")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(generator = "po_id_gen")
	@GenericGenerator(name = "po_id_gen", strategy = "com.warehouse.generator.PurchaseOrderIdGenerator")
	private String id;
	
	@Column(name = "po_code_col", nullable = false, unique = true)
	private String orderCode;
	
	@ManyToOne
	@JoinColumn(name = "st_id_fk_col", nullable = false)
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name = "wut_id_fk_col", nullable = false)
	private WhUserType vendor;
	
	@Column(name = "po_ref_num_col", nullable = false)
	private String refNumber;
	
	@Column(name = "po_qlty_check_col", nullable = false)
	private String qltyCheck;
	
	@Column(name = "po_status_col", nullable = false)
	private String status = "OPEN";
	
	@Column(name = "po_description")
	private String description;

}

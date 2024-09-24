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
@Table(name = "grn_tab")
public class Grn {
	
	@Id
	@GeneratedValue(generator = "grn_id_gen")
	@GenericGenerator(name = "grn_id_gen", strategy = "com.warehouse.generator.GrnIdGenerator")
	@Column(name = "grn_id_col")
	private String id;
	
	@Column(name = "grn_code_col", nullable = false)
	private String grnCode;
	
	@Column(name = "grn_type_col", nullable = false)
	private String grnType;
	
	@ManyToOne
	@JoinColumn(name = "order_id_fk_col", unique = true, nullable = false)
	private PurchaseOrder po;
	
	@Column(name = "grn_desc_col")
	private String decscription;

}

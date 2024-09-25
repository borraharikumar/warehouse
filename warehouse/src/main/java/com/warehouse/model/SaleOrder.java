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
@Table(name = "sale_order_tab")
public class SaleOrder {

	@Id
	@GeneratedValue(generator = "so_id_gen")
	@GenericGenerator(name = "so_id_gen", strategy = "com.warehouse.generator.SaleOrderIdGenerator")
	@Column(name = "so_id_col")
	private String id;
	
	@Column(name = "so_code_col", nullable = false, unique = true)
	private String orderCode;
	@Column(name = "so_refnum_col",nullable = false , unique = true)
	private String refNumber;
	@Column(name = "stock_mode_col", nullable = false)
	private String stockMode;
	@Column(name = "stock_source_col", nullable = false)
	private String stockSource;
	@Column(name = "so_status_col", nullable = false)
	private String status;
	@Column(name = "so_description_col")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "st_id_fk_col", nullable = false)
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name = "wut_id_fk_col", nullable = false)
	private WhUserType customer;

}

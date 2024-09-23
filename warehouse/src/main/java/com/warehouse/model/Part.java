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
@Entity
@Data
@Table(name = "part_tab")
public class Part {
	
	@Id
	@GeneratedValue(generator = "part_id_gen")
	@GenericGenerator(name = "part_id_gen", strategy = "com.warehouse.generator.PartIdGenerator")
	private String id; // PART+5 digits
	
	@Column(name = "part_code_col")
	private String partCode; // /^[A-Z0-9\s\-\_]{4,15}$/
	
	@Column(name = "part_length_col")
	private Double partLength;
	@Column(name = "part_width_col")
	private Double partWidth;
	@Column(name = "part_height_col")
	private Double partHeight;
	
	@Column(name = "part_base_price_col")
	private Double partBasePrice;
	@Column(name = "part_base_currency_col")
	private String partBaseCurrency;
	
	@ManyToOne
	@JoinColumn(name = "uom_id_fk_col")
	private Uom uom; // Use previously generated Uom data
	
	@ManyToOne
	@JoinColumn(name = "om_sale_id_fk_col")
	private OrderMethod omSale; // Use previously generated OrderMethod data where orderMode==SALE
	
	@ManyToOne
	@JoinColumn(name = "om_purchase_id_fk_col")
	private OrderMethod omPurchase; // Use previously generated OrderMethod data where orderMode==PURCHASE
	
	@Column(name = "part_desc_col")
	private String description;
	
}

package com.warehouse.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("deprecation")
@Entity
@Data
@Table(name = "uom_tab")
public class Uom {

	@Id
	@Column(name = "uom_id_col")
	@GeneratedValue(generator = "uom_id_gen")
	@GenericGenerator(name = "uom_id_gen", strategy = "com.warehouse.generator.UomIdGenerator")
	//@SequenceGenerator(name = "uom_id_gen", sequenceName = "uom_id_seq", initialValue = 1000, allocationSize = 1)
	private String id;
	
	@Column(name = "uom_type_col", nullable = false)
	private String uomType;
	
	@Column(name = "uom_model_col", unique = true, nullable = false)
	private String uomModel;
	
	@Column(name = "uom_descr_col")
	private String description;

}

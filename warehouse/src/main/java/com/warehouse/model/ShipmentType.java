package com.warehouse.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("deprecation")
@Data
@Entity
@Table(name = "shipment_type_tab")
public class ShipmentType {
	
	@Id
	@GeneratedValue(generator = "st_id_gen")
	@GenericGenerator(name = "st_id_gen", strategy = "com.warehouse.generator.ShipmentTypeIdGenerator")
	//@SequenceGenerator(name = "st_id_gen", sequenceName = "shipment_id_seq", initialValue = 10000, allocationSize = 1)
	@Column(name = "shipment_id_col")
	private String id;
	
	@Column(name = "shipemnt_mode_col", nullable = false)
	private String shipmentMode; // values[AIR, TRUCK, SHIP, TRAIN]
	
	@Column(name = "shipment_code_col", unique = true, nullable = false)
	private String shipmentCode; // /^[A-Z\-\s]{4,15}$/
	
	@Column(name = "enable_shipment_col", nullable = false)
	private String enableShipment; // values[YES, NO]
	
	@Column(name = "shipment_grade_col", nullable = false)
	private Character shipmentGrade; //values[A, B, C]
	
	@Column(name = "description_col")
	private String description;

}

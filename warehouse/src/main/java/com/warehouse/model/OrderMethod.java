package com.warehouse.model;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("deprecation")
@Data
@Entity
@Table(name = "order_method_tab")
public class OrderMethod {
	
	@Id
	@GeneratedValue(generator = "order_id_gen")
	@GenericGenerator(name = "order_id_gen", strategy = "com.warehouse.generator.OrderMethodIdGenerator")
	//@SequenceGenerator(name = "order_id_gen", sequenceName = "order_id_seq", initialValue = 100, allocationSize = 1)
	@Column(name = "order_id_col")
	private String id;
	
	@Column(name = "order_mode_col", nullable = false)
	private String orderMode; // values[SALE, PURCHASE]
	
	@Column(name = "order_code_col", unique = true, nullable = false)
	private String orderCode; ///^[A-Z\-\s]{4,15}$/
	
	@Column(name = "order_type_col", nullable = false)
	private String orderType; // values[FIFO, LIFO, FCFO, FEFO]
	
	@ElementCollection
	@CollectionTable(name = "order_accept_tab", joinColumns = @JoinColumn(referencedColumnName = "order_id_col"))
	@Column(name = "order_accept_col")
	private Set<String> orderAccept; // values[Multi-Model, Accept return]
	
	@Column(name = "description_tab")
	private String description;

}

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
@Table(name = "so_dtl_tab")
public class SaleOrderDtl {
	
	@Id
	@GeneratedValue(generator = "so_id_gen")
	@SequenceGenerator(name = "so_id_gen", sequenceName = "so_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "so_dtl_id_col")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "part_id_fk_col", nullable = false)
	private Part part;
	
	@Column(name = "part_qty_col", nullable = false)
	private Integer qty;
	
	@ManyToOne
	@JoinColumn(name = "so_id_fk_col", nullable = false)
	private SaleOrder so;

}

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
@Table(name = "wh_user_type_tab")
public class WhUserType {
	
	@Id
	@GeneratedValue(generator = "wh_user_id_gen")
	@GenericGenerator(name = "wh_user_id_gen", strategy = "com.warehouse.generator.WhUserTypeIdGenerator")
	//@SequenceGenerator(name = "wh_user_id_gen", sequenceName = "wh_user_id_seq", allocationSize = 1, initialValue = 1000)
	@Column(name = "user_id_col")
	private String id;
	
	@Column(name = "user_type_col", nullable = false)
	private String userType;
	
	@Column(name = "user_code_col", unique = true, nullable = false)
	private String userCode;
	
	@Column(name = "user_for_col", nullable = false)
	private String userFor;
	
	@Column(name = "user_email_col", nullable = false)
	private String userEmail;
	
	@Column(name = "user_contact_col", nullable = false)
	private String userContact;
	
	@Column(name = "user_id_type_col", nullable = false)
	private String userIdType;
	
	@Column(name = "user_id_number_col")
	private String userIdNumber;
	
}

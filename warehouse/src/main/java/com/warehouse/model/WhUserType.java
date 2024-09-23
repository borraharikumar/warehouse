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
	private String userType; // values[CUSTOMER, VENDOR]
	
	@Column(name = "user_code_col", unique = true, nullable = false)
	private String userCode; // /^[A-Z0-9\s\-]{4,15}$/
	
	@Column(name = "user_for_col", nullable = false)
	private String userFor; // values[if userType==CUSTOMER then SALE else PURCHASE]
	
	@Column(name = "user_email_col", nullable = false)
	private String userEmail;	// /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
	
	@Column(name = "user_contact_col", nullable = false)
	private String userContact; // /^[A-Za-z0-9\,\.\-\s]{5,50}$/
	
	@Column(name = "user_id_type_col", nullable = false)
	private String userIdType;	// values[AADHAR, PAN, VOTER ID, OTHER]
	
	@Column(name = "user_id_number_col")
	private String userIdNumber; // if userIdType==AADHAR, then userIdNumber==/^[0-9]{12}$/
								 // if userIdType==PAN, then userIdNumber==/^[A-Z]{5}[0-9]{4}[A-Z]$/
								 // if userIdType==VOTER ID, then userIdNumber==/^[A-Z]{3}[0-9]{7}$/
								 // if userIdType==OTHER, then userIdNumber==/^[A-Z0-9\s\-]{4,15}$/
	
}

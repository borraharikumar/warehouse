package com.warehouse.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("deprecation")
@Data
@Entity
@Table(name = "documents_tab")
public class Document {
	
	@Id
	@GeneratedValue(generator = "doc_id_gen")
	@GenericGenerator(name = "doc_id_gen", strategy = "com.warehouse.generator.DocumentIdGenerator")
	@Column(name = "doc_id_col")
	private String docId;
	
	@Column(name = "doc_name_col", nullable = false)
	private String docName;
	
	@Lob
	@Column(name = "doc_content_col", columnDefinition = "BLOB", nullable = false)
	private byte[] docContent;

}

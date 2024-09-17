package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.model.Document;

public interface IDocumentRepository extends JpaRepository<Document, String> {
	
	@Query("SELECT docId, docName FROM Document")
	public List<Object[]> findDocuments();

}

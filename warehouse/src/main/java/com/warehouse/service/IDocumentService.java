package com.warehouse.service;

import java.util.List;

import com.warehouse.model.Document;

public interface IDocumentService {
	
	public String insertDocument(Document document);
	public List<Object[]> getAllDocuments();
	public String deleteDocument(String id);
	public Document findOneDocument(String id);

}

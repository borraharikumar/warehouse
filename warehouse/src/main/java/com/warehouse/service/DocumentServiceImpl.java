package com.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.DocumentNotFoundException;
import com.warehouse.model.Document;
import com.warehouse.repository.IDocumentRepository;

@Service
public class DocumentServiceImpl implements IDocumentService {
	
	@Autowired
	private IDocumentRepository documentRepository;

	@Override
	public String insertDocument(Document document) {
		return "Document saved with ID '" + documentRepository.save(document).getDocId() + "' successfully...!";
	}
	
	@Override
	public List<Object[]> getAllDocuments() {
		List<Object[]> docs = documentRepository.findDocuments();
		return docs.isEmpty()?null:docs;
	}

	@Override
	public String deleteDocument(String id) {
		if(findOneDocument(id)!=null) documentRepository.deleteById(id);
		return "Document with id '" + id + "' deleted successfully...!";
	}

	@Override
	public Document findOneDocument(String id) {
		return documentRepository.findById(id)
				.orElseThrow(()->new DocumentNotFoundException("Document not found with id '" + id + "'"));
	}

}

package com.warehouse.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.warehouse.model.Document;
import com.warehouse.service.IDocumentService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/doc")
public class DocumentController {
	
	@Autowired
	private IDocumentService documentService;
	
	@GetMapping("/documents")
	public String documentsData(Model model) {
		model.addAttribute("docData", documentService.getAllDocuments());
		return "doc/documents";
	}
	
	@PostMapping("/save")
	public String saveDocument(@RequestParam String docName, @RequestParam MultipartFile file,
			RedirectAttributes redirectAttributes) throws IOException {
		//Get file extension
		String originalFileName = file.getOriginalFilename();
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		//Create Document object & Set data
		Document document = new Document();
		document.setDocName(docName + "." + fileExtension);
		document.setDocContent(file.getBytes());
		//Insert document in database
		redirectAttributes.addFlashAttribute("message", documentService.insertDocument(document));
		return "redirect:documents";
	}
	
	@GetMapping("/delete")
	public String deleteDocument(@RequestParam String id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", documentService.deleteDocument(id));
		return "redirect:documents";
	}
	
	@GetMapping("/download")
	public void downloadDocument(@RequestParam String id, HttpServletResponse response) throws IOException {
		//Get document object
		Document document = documentService.findOneDocument(id);
		//Add response header
		response.addHeader("Content-Disposition", "attachment;filename=" + document.getDocName());
		//Copy file data into Response OutputStream
		FileCopyUtils.copy(document.getDocContent(), response.getOutputStream());
	}

}

package com.warehouse.pdf;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.warehouse.model.PurchaseOrder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class PurchaseOrderPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Set pdf to landscape mode
		document.setPageSize(PageSize.A4.rotate());
		// Open pdf
		document.open();
		// Create document heading
		Font titleFont = new Font(Font.COURIER, 25, Font.BOLD, new Color(25, 10, 245));
		Paragraph title = new Paragraph("PURCHASE ORDER DATA", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(5.0f);
		// Create table & add data
		Font tableHeader = new Font(Font.COURIER, 15, Font.BOLD, new Color(232, 37, 125));
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100);
		table.setTotalWidth(new float[] { 0.5f, 0.5f, 1.0f, 0.8f, 0.6f, 0.9f, 0.5f, 1.0f });
		table.addCell(new Phrase("ID", tableHeader));
		table.addCell(new Phrase("CODE", tableHeader));
		table.addCell(new Phrase("SHIPMENT CODE", tableHeader));
		table.addCell(new Phrase("VENDOR", tableHeader));
		table.addCell(new Phrase("REF NUMBER", tableHeader));
		table.addCell(new Phrase("QLTY CHECK", tableHeader));
		table.addCell(new Phrase("STATUS", tableHeader));
		table.addCell(new Phrase("DESCRIPTION", tableHeader));
		for (PurchaseOrder po : (List<PurchaseOrder>) model.get("data")) {
			table.addCell(po.getId());
			table.addCell(po.getOrderCode());
			table.addCell(po.getShipmentType().getShipmentCode());
			table.addCell(po.getVendor().getUserCode());
			table.addCell(po.getRefNumber());
			table.addCell(po.getQltyCheck());
			table.addCell(po.getStatus());
			table.addCell(po.getDescription());
		}
		table.setSpacingAfter(5.0f);
		// Add date
		Paragraph date = new Paragraph("Generated on : " + new Date());
		// Add elements to the document
		document.add(title);
		document.add(table);
		document.add(date);
		// Close pdf
		document.close();
	}

}

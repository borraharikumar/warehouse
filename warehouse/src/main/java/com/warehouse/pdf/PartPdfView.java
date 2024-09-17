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
import com.warehouse.model.Part;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class PartPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Set pdf document to landscape mode
		document.setPageSize(PageSize.A4.rotate());
		//Open pdf document to write
		document.open();
		//Create document heading
		Font titleFont = new Font(Font.COURIER, 25, Font.BOLD, new Color(25, 10, 245));
		Paragraph title = new Paragraph("PART DATA", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(5.0f);
		//Create table & add data
		Font tableHeader = new Font(Font.COURIER, 15, Font.BOLD, new Color(232, 37, 125));
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setTotalWidth(new float[] {0.7f, 0.6f, 1.2f, 0.8f, 0.3f, 0.9f, 0.95f,1.0f, 1.0f});
		table.addCell(new Phrase("ID", tableHeader));
		table.addCell(new Phrase("CODE", tableHeader));
		table.addCell(new Phrase("DIMENSIONS\n(L : W : H)", tableHeader));
		table.addCell(new Phrase("PRICE", tableHeader));
		table.addCell(new Phrase("CUR", tableHeader));
		table.addCell(new Phrase("UOM MODEL", tableHeader));
		table.addCell(new Phrase("SALE ORDER CODE", tableHeader));
		table.addCell(new Phrase("PURCHASE ORDER CODE", tableHeader));
		table.addCell(new Phrase("DESCRIPTION", tableHeader));
		for(Part part : (List<Part>)model.get("data")) {
			table.addCell(part.getId());
			table.addCell(part.getPartCode());
			table.addCell(part.getPartLength()+" : "+part.getPartWidth()+" : "+part.getPartHeight());
			table.addCell(part.getPartBasePrice().toString());
			table.addCell(part.getPartBaseCurrency());
			table.addCell(part.getUom().getUomModel());
			table.addCell(part.getOmSale().getOrderCode());
			table.addCell(part.getOmPurchase().getOrderCode());
			table.addCell(part.getDescription());
		}
		table.setSpacingAfter(5.0f);
		//Add date
		Paragraph date = new Paragraph("Generated on : " + new Date());
		//Add elements to the document
		document.add(title);
		document.add(table);
		document.add(date);
		//Close pdf document
		document.close();
	}

}

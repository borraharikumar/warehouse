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
import com.warehouse.model.Uom;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class UomPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Set document to landscape mode
		document.setPageSize(PageSize.A4.rotate());
		//Open pdf document to write
		document.open();
		document.setMargins(10.0f, 10.0f, 10.0f, 10.0f);
		//Get Uom data
		List<Uom> data = (List<Uom>) model.get("data");
		//Create element
		Font headingFont = new Font(Font.COURIER, 22, Font.BOLD, new Color(25, 10, 245));
		Paragraph title = new Paragraph("UOM DATA", headingFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(5.0f);
		Font tableHeaderFont = new Font(Font.COURIER, 16, Font.BOLD, new Color(232, 37, 125));
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setTotalWidth(new float[] {0.8f, 1.0f, 2.0f, 2.5f});
		table.addCell(new Phrase("ID", tableHeaderFont));
		table.addCell(new Phrase("TYPE", tableHeaderFont));
		table.addCell(new Phrase("MODEL", tableHeaderFont));
		table.addCell(new Phrase("DESCRIPTION", tableHeaderFont));
		for(Uom uom : data) {
			table.addCell(String.valueOf(uom.getId()));
			table.addCell(uom.getUomType());
			table.addCell(uom.getUomModel());
			table.addCell(uom.getDescription());
		}
		table.setSpacingAfter(5.0f);
		Paragraph date = new Paragraph("Generated on : " + new Date());
		//Add element to document
		document.add(title);
		document.add(table);
		document.add(date);
		//Close pdf document
		document.close();
	}

}

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
import com.warehouse.model.ShipmentType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class ShipmentTypePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Set pdf document landscape mode
		document.setPageSize(PageSize.A4.rotate());
		//Open pdf document to write
		document.open();
		document.setMargins(10.0f, 10.0f, 10.0f, 10.0f);
		//Set page heading
		Font headingFont = new Font(Font.COURIER, 25, Font.BOLD, new Color(25, 10, 245));
		Paragraph title = new Paragraph("SHIPMENT TYPE DATA", headingFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(5.0f);
		//Create table
		Font tableHeader = new Font(Font.COURIER, 15, Font.BOLD, new Color(232, 37, 125));
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setTotalWidth(new float[] {0.6f, 0.5f, 0.8f, 0.7f, 0.5f, 3.0f});
		//Add table header
		table.addCell(new Phrase("ID", tableHeader));
		table.addCell(new Phrase("MODE", tableHeader));
		table.addCell(new Phrase("CODE", tableHeader));
		table.addCell(new Phrase("ENABLED", tableHeader));
		table.addCell(new Phrase("GRADE", tableHeader));
		table.addCell(new Phrase("DESCRIPTION", tableHeader));
		//Add table body
		for(ShipmentType st : (List<ShipmentType>)model.get("data")) {
			table.addCell(String.valueOf(st.getId()));
			table.addCell(st.getShipmentMode());
			table.addCell(st.getShipmentCode());
			table.addCell(st.getEnableShipment());
			table.addCell(String.valueOf(st.getShipmentGrade()));
			table.addCell(st.getDescription());
		}
		//Add date
		Paragraph date = new Paragraph("Generated on : " + new Date());
		//Add elements to the page
		document.add(title);
		document.add(table);
		document.add(date);
		//Close pdf document
		document.close();
	}

}

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
import com.warehouse.model.WhUserType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class WhUserTypePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Set pdf to landscape mode
		document.setPageSize(PageSize.A4.rotate());
		//Open document to write
		document.open();
		document.setMargins(10.0f, 10.0f, 10.0f, 10.0f);
		//Create elements & decorate them
		Font headingFont = new Font(Font.COURIER, 25, Font.BOLD, new Color(25, 10, 245));
		Font tableHeadingFont = new Font(Font.COURIER, 15, Font.BOLD, new Color(232, 37, 125));
		Paragraph title = new Paragraph("WAREHOUSE USER TYPE DATA", headingFont);
		title.setSpacingAfter(5.0f);
		title.setAlignment(Element.ALIGN_CENTER);
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100);
		table.setTotalWidth(new float[] {0.7f, 0.9f, 0.7f, 0.9f, 2.5f, 1.5f, 0.8f, 1.2f});
		table.addCell(new Phrase("ID", tableHeadingFont));
		table.addCell(new Phrase("TYPE", tableHeadingFont));
		table.addCell(new Phrase("CODE", tableHeadingFont));
		table.addCell(new Phrase("FOR", tableHeadingFont));
		table.addCell(new Phrase("EMAIL", tableHeadingFont));
		table.addCell(new Phrase("CONTACT", tableHeadingFont));
		table.addCell(new Phrase("ID TYPE", tableHeadingFont));
		table.addCell(new Phrase("ID NUMBER", tableHeadingFont));
		for(WhUserType user : (List<WhUserType>)model.get("data")) {
			table.addCell(user.getId());
			table.addCell(user.getUserType());
			table.addCell(user.getUserCode());
			table.addCell(user.getUserFor());
			table.addCell(user.getUserEmail());
			table.addCell(user.getUserContact());
			table.addCell(user.getUserIdType());
			table.addCell(user.getUserIdNumber());
		}
		table.setSpacingAfter(5.0f);
		Paragraph date = new Paragraph("Generated on : " + new Date());
		//Add elements to the document
		document.add(title);
		document.add(table);
		document.add(date);
		//Close document
		document.close();
	}

}

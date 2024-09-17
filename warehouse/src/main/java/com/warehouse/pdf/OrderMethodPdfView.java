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
import com.warehouse.model.OrderMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class OrderMethodPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Set pdf document to landscape mode
		document.setPageSize(PageSize.A4.rotate());
		//Open pdf document to write
		document.open();
		//Create elements and decorate them
		Font titleFont = new Font(Font.COURIER, 25, Font.BOLD, new Color(25, 10, 245));
		Font tableHeader = new Font(Font.COURIER, 15, Font.BOLD, new Color(232, 37, 125));
		Paragraph title = new Paragraph("ORDER METHOD DATA", titleFont);
		title.setSpacingAfter(5.0f);
		title.setAlignment(Element.ALIGN_CENTER);
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setTotalWidth(new float[] {0.6f, 0.6f, 1.0f, 0.4f, 0.7f, 2.5f});
		table.addCell(new Phrase("ID", tableHeader));
		table.addCell(new Phrase("MODE", tableHeader));
		table.addCell(new Phrase("CODE", tableHeader));
		table.addCell(new Phrase("TYPE", tableHeader));
		table.addCell(new Phrase("ACCEPT", tableHeader));
		table.addCell(new Phrase("DESCRIPTION", tableHeader));
		for(OrderMethod order : (List<OrderMethod>)model.get("data")) {
			table.addCell(order.getId());
			table.addCell(order.getOrderMode());
			table.addCell(order.getOrderCode());
			table.addCell(order.getOrderType());
			table.addCell(String.valueOf(order.getOrderAccept()));
			table.addCell(order.getDescription());
		}
		table.setSpacingAfter(5.0f);
		Paragraph date = new Paragraph("Generated on : " + new Date());
		//Add elements to the document
		document.add(title);
		document.add(table);
		document.add(date);
		//Close pdf document
		document.close();
	}

}

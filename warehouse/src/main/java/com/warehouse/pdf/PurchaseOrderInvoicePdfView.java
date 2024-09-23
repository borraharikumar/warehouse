package com.warehouse.pdf;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.warehouse.model.PurchaseOrder;
import com.warehouse.model.PurchaseOrderDtl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class PurchaseOrderInvoicePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Get PurchaseOrder
		PurchaseOrder po = (PurchaseOrder) model.get("po");
		//Get PurchaseOrderDtl Data
		List<PurchaseOrderDtl> poDtl = (List<PurchaseOrderDtl>) model.get("poDtl");
		//Create title
		Font titleFont = new Font(Font.COURIER, 25, Font.BOLD, new Color(2, 7, 250));
		Paragraph title = new Paragraph("VENDOR INVOICE", titleFont);
		title.setSpacingAfter(20);
		title.setAlignment(Element.ALIGN_CENTER);
		//Get Final Cost
		Double subTotal = 0.0;
		Double totalCost = 0.0;
		Double cGst = 0.0;
		Double sGst = 0.0;
		for(PurchaseOrderDtl dtl : poDtl) {
			subTotal += dtl.getPart().getPartBasePrice()*dtl.getQty();
		}
		cGst = (subTotal*7)/100;
		sGst = (subTotal*7)/100;
		totalCost = subTotal + cGst + sGst;
		//Add generated date & time
		Font dateFont = new Font(Font.TIMES_ROMAN, 13, Font.ITALIC, new Color(189, 23, 36));
		Paragraph date = new Paragraph("Generated on : " + new Date(), dateFont);
		//Add elements to the document
		document.add(title);
		document.add(createMetaData(po, document, totalCost));
		document.add(createOrderDtl(poDtl, document));
		document.add(createFooter(subTotal, cGst, sGst, totalCost));
		document.add(date);
	}

	private PdfPTable createMetaData(PurchaseOrder po, Document document, Double finalCost) throws DocumentException {
		PdfPTable metaData = new PdfPTable(2);
		metaData.setWidths(new int[] {5, 10});
		Font metaDataFont = new Font(Font.HELVETICA, 13, Font.NORMAL, new Color(14, 14, 15));
		PdfPCell cell1 = new PdfPCell(new Phrase("Vendor code", metaDataFont));
		PdfPCell cell2 = new PdfPCell(new Phrase(":   " + po.getVendor().getUserCode(), metaDataFont));
		PdfPCell cell3 = new PdfPCell(new Phrase("Order code", metaDataFont));
		PdfPCell cell4 = new PdfPCell(new Phrase(":   " + po.getOrderCode(), metaDataFont));
		PdfPCell cell5 = new PdfPCell(new Phrase("Final cost", metaDataFont));
		PdfPCell cell6 = new PdfPCell(new Phrase(":   " + finalCost, metaDataFont));
		PdfPCell cell7 = new PdfPCell(new Phrase("Shipment type", metaDataFont));
		PdfPCell cell8 = new PdfPCell(new Phrase(":   " + po.getShipmentType().getShipmentCode(), metaDataFont));
		cell1.setBorder(PdfPCell.NO_BORDER);
		cell2.setBorder(PdfPCell.NO_BORDER);
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell7.setBorder(PdfPCell.NO_BORDER);
		cell8.setBorder(PdfPCell.NO_BORDER);
		metaData.addCell(cell1);
		metaData.addCell(cell2);
		metaData.addCell(cell3);
		metaData.addCell(cell4);
		metaData.addCell(cell5);
		metaData.addCell(cell6);
		metaData.addCell(cell7);
		metaData.addCell(cell8);
		metaData.setSpacingAfter(20);
		return metaData;
	}
	
	private Element createOrderDtl(List<PurchaseOrderDtl> poDtl, Document document) {
		PdfPTable data = new PdfPTable(4);
		data.setWidthPercentage(100);
		Font dataHeaderFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD, new Color(85, 60, 140));
		PdfPCell cell1 = new PdfPCell(new Phrase("ITEM_CODE", dataHeaderFont));
		PdfPCell cell2 = new PdfPCell(new Phrase("BASE_COST", dataHeaderFont));
		PdfPCell cell3 = new PdfPCell(new Phrase("QUANTITY", dataHeaderFont));
		PdfPCell cell4 = new PdfPCell(new Phrase("TOTAL_VALUE", dataHeaderFont));
		cell1.setBorder(PdfPCell.BOTTOM);
		cell2.setBorder(PdfPCell.BOTTOM);
		cell3.setBorder(PdfPCell.BOTTOM);
		cell4.setBorder(PdfPCell.BOTTOM);
		data.addCell(cell1);
		data.addCell(cell2);
		data.addCell(cell3);
		data.addCell(cell4);
		for(PurchaseOrderDtl dtl : poDtl) {
			PdfPCell cell5 = new PdfPCell(new Phrase(dtl.getPart().getPartCode()));
			PdfPCell cell6 = new PdfPCell(new Phrase(String.valueOf(dtl.getPart().getPartBasePrice())));
			PdfPCell cell7 = new PdfPCell(new Phrase(String.valueOf(dtl.getQty())));
			PdfPCell cell8 = new PdfPCell(new Phrase(String.valueOf(dtl.getPart().getPartBasePrice()*dtl.getQty())));
			cell5.setBorder(PdfPCell.BOTTOM);
			cell6.setBorder(PdfPCell.BOTTOM);
			cell7.setBorder(PdfPCell.BOTTOM);
			cell8.setBorder(PdfPCell.BOTTOM);
			data.addCell(cell5);
			data.addCell(cell6);
			data.addCell(cell7);
			data.addCell(cell8);
		}
		data.setSpacingAfter(5f);
		return data;
	}
	
	private Element createFooter(Double subTotal, Double cGst, Double sGst, Double totalCost) throws DocumentException {
		PdfPTable footer = new PdfPTable(2);
		footer.setWidths(new float[] {0.19f, 0.1f});
		footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Font metaDataFont = new Font(Font.HELVETICA, 13, Font.NORMAL, new Color(36, 66, 36));
		PdfPCell cell1 = new PdfPCell(new Phrase("Sub-total", metaDataFont));
		PdfPCell cell2 = new PdfPCell(new Phrase(":   " + subTotal));
		PdfPCell cell3 = new PdfPCell(new Phrase("Central GST", metaDataFont));
		PdfPCell cell4 = new PdfPCell(new Phrase(":   " + cGst));
		PdfPCell cell5 = new PdfPCell(new Phrase("State GST", metaDataFont));
		PdfPCell cell6 = new PdfPCell(new Phrase(":   " + sGst));
		PdfPCell cell7 = new PdfPCell(new Phrase("Total cost", metaDataFont));
		PdfPCell cell8 = new PdfPCell(new Phrase(":   " + totalCost));
		cell1.setBorder(PdfPCell.NO_BORDER);
		cell2.setBorder(PdfPCell.NO_BORDER);
		cell3.setBorder(PdfPCell.NO_BORDER);
		cell4.setBorder(PdfPCell.NO_BORDER);
		cell5.setBorder(PdfPCell.NO_BORDER);
		cell6.setBorder(PdfPCell.NO_BORDER);
		cell7.setBorder(PdfPCell.NO_BORDER);
		cell8.setBorder(PdfPCell.NO_BORDER);
		footer.addCell(cell1);
		footer.addCell(cell2);
		footer.addCell(cell3);
		footer.addCell(cell4);
		footer.addCell(cell5);
		footer.addCell(cell6);
		footer.addCell(cell7);
		footer.addCell(cell8);
		footer.setSpacingAfter(20);
		return footer;
	}

}

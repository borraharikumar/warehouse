package com.warehouse.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.warehouse.model.PurchaseOrder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class PurchaseOrderExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Set file name
		response.addHeader("Content-Disposition", "attachment;filename=PURCHASE_ORDER.xlsx");
		//Get data
		List<PurchaseOrder> data = (List<PurchaseOrder>) model.get("data");
		//Create sheet
		Sheet sheet = workbook.createSheet("PURCHASE ORDER");
		//Set header
		generateHeader(sheet);
		//Set body
		generateBody(sheet, data);
	}

	private void generateHeader(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("SHIPMENT MODE");
		row.createCell(3).setCellValue("VENDOR CODE");
		row.createCell(4).setCellValue("REF NUMBER");
		row.createCell(5).setCellValue("QLTY CHECK");
		row.createCell(6).setCellValue("STATUS");
		row.createCell(7).setCellValue("DESCRIPTION");
	}

	private void generateBody(Sheet sheet, List<PurchaseOrder> data) {
		int rowNum = 1;
		for(PurchaseOrder po : data) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(po.getId());
			row.createCell(1).setCellValue(po.getOrderCode());
			row.createCell(2).setCellValue(po.getShipmentType().getShipmentCode());
			row.createCell(3).setCellValue(po.getVendor().getUserCode());
			row.createCell(4).setCellValue(po.getRefNumber());
			row.createCell(5).setCellValue(po.getQltyCheck());
			row.createCell(6).setCellValue(po.getStatus());
			row.createCell(7).setCellValue(po.getDescription());
		}
	}

}

package com.warehouse.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.warehouse.model.OrderMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OrderMethodExcelView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Set file name
		response.addHeader("Content-Disposition", "attachment;filename=ORDER_METHODS.xlsx");
		//Get data
		List<OrderMethod> data = (List<OrderMethod>) model.get("data");
		//Create Sheet
		Sheet sheet = workbook.createSheet("ORDER_METHOD");
		//Add header & body
		addHeader(sheet);
		addbody(sheet, data);
	}

	private void addHeader(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("TYPE");
		row.createCell(4).setCellValue("ACCEPT");
		row.createCell(5).setCellValue("DESCRIPTION");
	}

	private void addbody(Sheet sheet, List<OrderMethod> data) {
		int rowNum = 1;
		for(OrderMethod order : data) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(order.getId());
			row.createCell(1).setCellValue(order.getOrderMode());
			row.createCell(2).setCellValue(order.getOrderCode());
			row.createCell(3).setCellValue(order.getOrderType());
			row.createCell(4).setCellValue(String.valueOf(order.getOrderAccept()));
			row.createCell(5).setCellValue(order.getDescription());
		}
	}

}

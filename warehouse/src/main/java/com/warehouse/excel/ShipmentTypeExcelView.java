package com.warehouse.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.warehouse.model.ShipmentType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShipmentTypeExcelView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Set file name
		response.addHeader("Content-Disposition", "attachment;filename=SHIPMENT.xlsx");
		//Get shipment data
		List<ShipmentType> data = (List<ShipmentType>) model.get("data");
		//Create Sheet
		Sheet sheet = workbook.createSheet("SHIPMENT");
		//Create File header
		createHeader(sheet);
		//Create file body
		createBody(sheet, data);
	}

	private void createHeader(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("ENABLED_SHIPMENT");
		row.createCell(4).setCellValue("GRADE");
		row.createCell(5).setCellValue("DESCRIPTION");
	}
	
	private void createBody(Sheet sheet, List<ShipmentType> data) {
		int rowNum = 1;
		for(ShipmentType st : data) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(String.valueOf(st.getId()));
			row.createCell(1).setCellValue(st.getShipmentMode());
			row.createCell(2).setCellValue(st.getShipmentCode());
			row.createCell(3).setCellValue(st.getEnableShipment());
			row.createCell(4).setCellValue(String.valueOf(st.getShipmentGrade()));
			row.createCell(5).setCellValue(st.getDescription());
		}
	}

}

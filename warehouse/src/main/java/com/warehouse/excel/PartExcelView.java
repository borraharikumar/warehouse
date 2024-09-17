package com.warehouse.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.warehouse.model.Part;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class PartExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Set document name
		response.addHeader("Content-Disposition", "attachment;filename=PART_DATA.xlsx");
		//Get data
		List<Part> list = (List<Part>) model.get("data");
		//Create sheet
		Sheet sheet = workbook.createSheet("PARTS");
		//Set header
		createHeader(sheet);
		createBody(sheet, list);
		//Set body
	}

	private void createHeader(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("DIMENSIONS");
		row.createCell(3).setCellValue("PRICE");
		row.createCell(4).setCellValue("CURRENCY");
		row.createCell(5).setCellValue("UOM MODEL");
		row.createCell(6).setCellValue("SALE ORDER CODE");
		row.createCell(7).setCellValue("PURCHASE ORDER CODE");
	}

	private void createBody(Sheet sheet, List<Part> list) {
		int rowNum = 1;
		for(Part part : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(part.getId());
			row.createCell(1).setCellValue(part.getPartCode());
			row.createCell(2).setCellValue(part.getPartLength()+" : "+part.getPartWidth()+" : "+part.getPartHeight());
			row.createCell(3).setCellValue(part.getPartBasePrice());
			row.createCell(4).setCellValue(part.getPartBaseCurrency());
			row.createCell(5).setCellValue(part.getUom().getUomModel());
			row.createCell(6).setCellValue(part.getOmSale().getOrderCode());
			row.createCell(7).setCellValue(part.getOmPurchase().getOrderCode());
		}
	}

}

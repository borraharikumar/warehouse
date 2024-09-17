package com.warehouse.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.warehouse.model.Uom;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UomExcelView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		//Se file name
		response.addHeader("Content-Disposition", "attachment;filename=UOMS.xlsx");
		//Create Sheet
		Sheet sheet = workbook.createSheet("UOMS");
		//Create Header
		setHeader(sheet, workbook);
		//Get data from model object
		List<Uom> data = (List<Uom>) model.get("data");
		//Create Body
		setBody(sheet, data);
	}
	
	private CellStyle highlightHeader(Workbook workbook) {
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setFillBackgroundColor(IndexedColors.DARK_GREEN.getIndex());
		return cellStyle;
	}

	private void setHeader(Sheet sheet, Workbook workbook) {
		//Create Row
		Row row = sheet.createRow(0);
		//Create Cells & Set value to the cells
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("MODEL");
		row.createCell(3).setCellValue("DESCRIPTION");
		//Highlight Header
		row.setRowStyle(highlightHeader(workbook));
	}
	
	private void setBody(Sheet sheet, List<Uom> data) {
		if(!data.isEmpty()) {
			int rowNum = 1;
			for(Uom uom : data) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(uom.getId());
				row.createCell(1).setCellValue(uom.getUomType());
				row.createCell(2).setCellValue(uom.getUomModel());
				row.createCell(3).setCellValue(uom.getDescription());
			}
		}
	}

}

package com.warehouse.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.warehouse.model.WhUserType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WhUserTypeExcelView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Set file name
		response.addHeader("Content-Disposition", "attachment;filename=WH_USER.xlsx");
		//Get data
		List<WhUserType> data = (List<WhUserType>) model.get("data");
		//Create Sheet & body
		Sheet sheet = workbook.createSheet("USERS");
		addHeader(sheet, workbook);
		addBody(sheet, data);
	}

	private void addHeader(Sheet sheet, Workbook workbook) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("FOR");
		row.createCell(4).setCellValue("EMAIL");
		row.createCell(5).setCellValue("CONTACT");
		row.createCell(6).setCellValue("ID TYPE");
		row.createCell(7).setCellValue("ID NUMBER");
		row.setRowStyle(styleHeader(workbook));
	}

	private CellStyle styleHeader(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.getFillForegroundColor();
		style.setFont(font);
		return style;
	}

	private void addBody(Sheet sheet, List<WhUserType> data) {
		int rowNum = 1;
		for(WhUserType user : data) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getUserType());
			row.createCell(2).setCellValue(user.getUserCode());
			row.createCell(3).setCellValue(user.getUserFor());
			row.createCell(4).setCellValue(user.getUserEmail());
			row.createCell(5).setCellValue(user.getUserContact());
			row.createCell(6).setCellValue(user.getUserIdType());
			row.createCell(7).setCellValue(user.getUserIdNumber());
		}
	}

}

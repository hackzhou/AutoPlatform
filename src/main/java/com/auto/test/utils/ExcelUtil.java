package com.auto.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.entity.AInterface;
import com.auto.test.entity.AProject;

public class ExcelUtil {
	
	public static void main(String[] args) {
		try {
			new ExcelUtil().readXls(new FileInputStream(new File("C:/Users/Administrator/Desktop/test.xlsx")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<AInterface> readXls(InputStream is) throws Exception {
		List<AInterface> list = null;
		AInterface aInterface = null;
		Workbook wb = null;
		try {
			wb = new XSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			list = new ArrayList<AInterface>();
			while (rows.hasNext()) {
				Row row = rows.next();
				if(row.getRowNum() == 0){
					continue;
				}
				aInterface = new AInterface();
	            Iterator<Cell> cells = row.cellIterator();
	            while (cells.hasNext()) {
	                Cell cell = cells.next();
	                String cellValue = trimStr(cell.getStringCellValue());
	                switch (cell.getColumnIndex()) {
	                	case 0:
	                		aInterface.setProjecto(new AProject(getApiProjectID(cellValue, row.getRowNum() + 1)));
	                		break;
	                	case 1:
	                		aInterface.setName(cellValue);
		                    break;
	                	case 2:
	                		aInterface.setType(cellValue);
		                    break;
	                	case 3:
	                		aInterface.setUrl(cellValue);
		                    break;
	                	case 4:
	                		aInterface.setDescription(cellValue);
		                    break;
	                	default:
		                    break;
	                }
	            }
	            aInterface.setMemo(String.valueOf(row.getRowNum() + 1));
	            list.add(aInterface);
	        }
		} finally {
			if(is != null){
				is.close();
			}
			if(wb != null){
				wb.close();
			}
		}
		return list;
	}
	
	private Integer getApiProjectID(String str, Integer row) throws Exception{
		if(str != null){
			str.replace("（", "(").replace("）", ")");
			if(str.contains("(") && str.contains(")")){
				return Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(")")));
			}
		}
		throw new BusinessException("【第" + row + "行】项目ID获取失败！");
	}
	
	private String trimStr(String str){
		if(str != null && !str.isEmpty()){
			return str.trim();
		}
		return "";
	}

}

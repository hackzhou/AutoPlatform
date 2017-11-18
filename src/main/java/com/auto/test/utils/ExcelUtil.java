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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auto.test.common.bean.AInterfaceCase;
import com.auto.test.common.exception.BusinessException;

public class ExcelUtil {
	
	public static void main(String[] args) {
		try {
			new ExcelUtil().readXls(new FileInputStream(new File("C:/Users/Administrator/Desktop/test.xlsx")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<AInterfaceCase> readXls(InputStream is) throws Exception {
		List<AInterfaceCase> list = null;
		AInterfaceCase aInterfaceCase = null;
		Workbook wb = null;
		try {
			wb = new XSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			list = new ArrayList<AInterfaceCase>();
			while (rows.hasNext()) {
				Row row = rows.next();
				if(row.getRowNum() == 0){
					continue;
				}
				aInterfaceCase = new AInterfaceCase();
	            Iterator<Cell> cells = row.cellIterator();
	            while (cells.hasNext()) {
	                Cell cell = cells.next();
	                String cellValue = trimStr(cell.getStringCellValue());
	                switch (cell.getColumnIndex()) {
	                	case 0:
	                		aInterfaceCase.setProject(cellValue);
	                		break;
	                	case 1:
	                		aInterfaceCase.setName(cellValue);
		                    break;
	                	case 2:
	                		aInterfaceCase.setType(cellValue);
		                    break;
	                	case 3:
	                		aInterfaceCase.setUrl(cellValue);
		                    break;
	                	case 4:
	                		aInterfaceCase.setDescription(cellValue);
		                    break;
	                	case 5:
	                		aInterfaceCase.setVersion(cellValue);
		                    break;
	                	case 6:
	                		aInterfaceCase.setLogin(cellValue);
		                    break;
	                	case 7:
	                		aInterfaceCase.setOnce(cellValue);
		                    break;
	                	case 8:
	                		aInterfaceCase.setReady(validateNum(cellValue, row.getRowNum() + 1));
		                    break;
	                	case 9:
	                		aInterfaceCase.setBody(getRequestBoy(cellValue, row.getRowNum() + 1));
		                    break;
	                	case 10:
	                		aInterfaceCase.setResult(getRequestBoy(cellValue, row.getRowNum() + 1));
		                    break;
	                	case 11:
	                		aInterfaceCase.setStrategy(splitStr(cellValue));
		                    break;
	                	default:
		                    break;
	                }
	            }
	            aInterfaceCase.setRowNum(row.getRowNum() + 1);
	            list.add(aInterfaceCase);
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
	
	private String getRequestBoy(String text, Integer row){
		if(text != null){
			text = text.trim();
			if(text.startsWith("{") && text.endsWith("}")){
				try {
					JSONObject json = JSON.parseObject(text);
					return JSON.toJSONString(json, false);
				} catch (Exception e) {
					throw new BusinessException("【第" + row + "行】JSON格式错误！");
				}
			}
		}
		return "";
	}
	
	@SuppressWarnings("unused")
	private Integer getApiID(String text, String str, Integer row) throws Exception{
		if(str != null){
			str = str.trim().replace("（", "(").replace("）", ")");
			if(str.contains("(") && str.contains(")")){
				return Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(")")));
			}
		}
		throw new BusinessException("【第" + row + "行】" + text + "ID获取失败！");
	}
	
	private String splitStr(String str){
		if(str != null && !str.isEmpty()){
			str = str.replace("，", ",").trim();
			if(str.contains(",")){
				String result = "";
				for (String s : str.split(",")) {
					if(s != null && !s.trim().isEmpty()){
						result += "," + s.trim();
					}
				}
				return result.startsWith(",") ? result.substring(1) : result;
			}else{
				return str;
			}
		}
		return "";
	}
	
	private String trimStr(String str){
		if(str != null && !str.isEmpty()){
			return str.trim();
		}
		return "";
	}
	
	private String validateNum(String str, Integer row){
		try {
			int i = Integer.parseInt(str);
			if(i < 0 || i > 100){
				throw new BusinessException("【第" + row + "行】数值区间[0-100]！");
			}
			return str;
		} catch (Exception e) {
			throw new BusinessException("【第" + row + "行】非数字格式！");
		}
	}

}

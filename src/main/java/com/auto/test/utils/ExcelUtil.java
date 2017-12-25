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
		AInterfaceCase aInterfaceCaseFirst = null;
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
				aInterfaceCaseFirst = aInterfaceCase;
				aInterfaceCase = new AInterfaceCase();
	            Iterator<Cell> cells = row.cellIterator();
	            while (cells.hasNext()) {
	                Cell cell = cells.next();
	                switch (cell.getColumnIndex()) {
	                	case 0:
	                		aInterfaceCase.setProject(trimStr(cell.getStringCellValue()));
	                		break;
	                	case 1:
	                		aInterfaceCase.setName(trimStr(cell.getStringCellValue()));
		                    break;
	                	case 2:
	                		aInterfaceCase.setType(trimStr(cell.getStringCellValue()));
		                    break;
	                	case 3:
	                		aInterfaceCase.setUrl(trimStr(cell.getStringCellValue()));
		                    break;
	                	case 4:
	                		aInterfaceCase.setDescription(trimStr(cell.getStringCellValue()));
		                    break;
	                	case 5:
	                		aInterfaceCase.setVersion(trimStr(cell.getStringCellValue()));
		                    break;
	                	case 6:
	                		aInterfaceCase.setLogin(trimStr(cell.getStringCellValue()));
		                    break;
	                	case 7:
	                		aInterfaceCase.setOnce(trimStr(cell.getStringCellValue()));
		                    break;
	                	case 8:
	                		aInterfaceCase.setReady(parseNum(cell.getNumericCellValue(), row.getRowNum() + 1));
		                    break;
	                	case 9:
	                		aInterfaceCase.setLink(parseLink(cell.getStringCellValue(), row.getRowNum() + 1));
		                    break;
	                	case 10:
	                		aInterfaceCase.setBody(getRequestBoy(trimStr(cell.getStringCellValue()), row.getRowNum() + 1));
		                    break;
	                	case 11:
	                		aInterfaceCase.setResult(getRequestBoy(trimStr(cell.getStringCellValue()), row.getRowNum() + 1));
		                    break;
	                	case 12:
	                		aInterfaceCase.setStrategy(splitStr(trimStr(cell.getStringCellValue())));
		                    break;
	                	case 13:
	                		aInterfaceCase.setValidate(splitStr(trimStr(cell.getStringCellValue())));
		                    break;
	                	default:
		                    break;
	                }
	            }
	            aInterfaceCase.setRowNum(row.getRowNum() + 1);
	            setAInterface(aInterfaceCase, aInterfaceCaseFirst);
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
	
	private void setAInterface(AInterfaceCase aInterfaceCase, AInterfaceCase aInterfaceCaseFirst){
		if(aInterfaceCase.getProject().isEmpty() && aInterfaceCaseFirst != null){
			aInterfaceCase.setProject(aInterfaceCaseFirst.getProject());
			aInterfaceCase.setName(aInterfaceCaseFirst.getName());
			aInterfaceCase.setType(aInterfaceCaseFirst.getType());
			aInterfaceCase.setUrl(aInterfaceCaseFirst.getUrl());
			aInterfaceCase.setDescription(aInterfaceCaseFirst.getDescription());
		}
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
	
	private int parseNum(double d, Integer row){
		try {
			int i = new Double(d).intValue();
			return i < 0 ? 0 : i;
		} catch (Exception e) {
			throw new BusinessException("【第" + row + "行】[" + d + "]数字格式转换错误！");
		}
	}
	
	private String parseLink(String text, Integer row){
		if(text == null || text.trim().isEmpty() || text.trim().equals("-")){
			return null;
		}
		String[] links = text.trim().replace("S", "").split("-");
		if(links.length == 2){
			try {
				Integer.parseInt(links[0]);
				Integer.parseInt(links[1]);
				return text.trim();
			} catch (Exception e) {
				throw new BusinessException("【第" + row + "行】[" + text + "]数字转换错误！");
			}
		}else{
			throw new BusinessException("【第" + row + "行】[" + text + "]格式错误！");
		}
	}

}

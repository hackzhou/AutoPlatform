package com.auto.test.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.constant.Const;
import com.auto.test.common.controller.BaseController;
import com.auto.test.core.ui.DynamicEngine;
import com.auto.test.entity.UCode;
import com.auto.test.service.IUiCodeService;
import com.auto.test.utils.FileUtil;

@RestController
@RequestMapping(value = "ui/code")
public class UiCodeController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(UiCodeController.class);
	
	@Resource
	private IUiCodeService uiCodeService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getUiCode(HttpServletRequest request) {
		return success("ui/code", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/default/code", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDefaultCodeData() {
		String className = getRandomClassName();
		StringBuffer data = new StringBuffer();
		data.append("import org.slf4j.Logger;").append("\r\n");
		data.append("import org.slf4j.LoggerFactory;").append("\r\n");
		data.append("import java.util.Date;").append("\r\n");
		data.append("import com.alibaba.fastjson.JSON;").append("\r\n");
		data.append("import com.auto.test.common.constant.Const;").append("\r\n").append("\r\n");
		data.append("public class ").append(className).append(" {").append("\r\n");
		data.append("\t").append("private static Logger logger = LoggerFactory.getLogger(").append(className).append(".class);").append("\r\n").append("\r\n");
		data.append("\t").append("public static void main(String[] args) {").append("\r\n");
		data.append("\t\t").append("logger.info(\"Hello log test !!!\");").append("\r\n");
		data.append("\t\t").append("System.out.println(\"Hello World !!!\");").append("\r\n");
		data.append("\t\t").append("System.out.println(new Date());").append("\r\n");
		data.append("\t\t").append("System.out.println(Const.AUTO_PLATFORM);").append("\r\n");
		data.append("\t\t").append("System.out.println(JSON.parseObject(\"{\\\"A\\\":\\\"B\\\"}\").toJSONString());").append("\r\n");
		data.append("\t").append("}").append("\r\n");
		data.append("}").append("\r\n");
		return successJson(data.toString());
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(HttpServletRequest request, @RequestParam("ui-code-java") String code) {
		String fileName = subStr(code, "class", "\\{") + ".java";
		logger.info("Save[" + Const.UI_CODE_PATH + File.separator + fileName + "]\r\n" + code);
		List<UCode> codeList = uiCodeService.findByCls(fileName);
		FileUtil fileUtil = new FileUtil();
		if(codeList != null && !codeList.isEmpty()){
			fileUtil.delete(Const.UI_CODE_PATH, fileName);
			if(fileUtil.writeJavaFile(Const.UI_CODE_PATH, fileName, code)){
				UCode uCode = new UCode(codeList.get(0).getId(), Const.UI_CODE_PATH, fileName, getCurrentUserName(request));
				uiCodeService.update(uCode);
				return successJson();
			}else{
				logger.error("文件保存失败" + "[" + Const.UI_CODE_PATH + File.separator + fileName + "]");
				return failedJson("文件保存失败" + "[" + Const.UI_CODE_PATH + File.separator + fileName + "]");
			}
		}else{
			if(fileUtil.writeJavaFile(Const.UI_CODE_PATH, fileName, code)){
				UCode uCode = new UCode(null, Const.UI_CODE_PATH, fileName, getCurrentUserName(request));
				uiCodeService.create(uCode);
				return successJson();
			}else{
				logger.error("文件保存失败" + "[" + Const.UI_CODE_PATH + File.separator + fileName + "]");
				return failedJson("文件保存失败" + "[" + Const.UI_CODE_PATH + File.separator + fileName + "]");
			}
		}
	}
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> runCode(HttpServletRequest request, @RequestParam("ui-code-java") String code) {
		String className = subStr(code, "class", "\\{");
		String fileName = className + ".java";
		FileUtil fileUtil = new FileUtil();
		File file = fileUtil.getFile(Const.UI_CODE_PATH, fileName);
		if(file.exists()){
			String javaCode = fileUtil.readJavaFile(file);
			logger.info("Run[" + Const.UI_CODE_PATH + File.separator + fileName + "]\r\n" + javaCode);
			DynamicEngine de = new DynamicEngine();
			Object instance = de.javaCodeToObject(className, javaCode);
			logger.info("Run[" + instance + "]");
			return successJson();
		}else{
			logger.error("文件不存在" + "[" + Const.UI_CODE_PATH + File.separator + fileName + "]");
			return failedJson("文件不存在" + "[" + Const.UI_CODE_PATH + File.separator + fileName + "]");
		}
	}
	
	public String getRandomClassName(){
		String className = randomClassName();
		while (true) {
			List<UCode> codeList = uiCodeService.findByCls(className + ".java");
			if(codeList != null && !codeList.isEmpty()){
				className = randomClassName();
			}else{
				return className;
			}
		}
	}
	
	public static String randomClassName(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'");
		StringBuffer sb = new StringBuffer("A" + sdf.format(new Date()));
		for (int i = 0; i < 10; i++) {
			if((int)(Math.random() * 10) % 2 == 0){
				sb.append((int)(Math.random() * 10));
			}else{
				sb.append((char)(Math.random() * 26 + 65));
			}
		}
		return sb.toString();
	}
	
	public File[] concat(File[] a, File[] b) {
		File[] c = new File[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
	
	private String subStr(String text, String start, String end){
		if(text != null && !text.isEmpty()){
			if(text.contains(start)){
				text = text.split(start)[1];
				text = text.split(end)[0];
				return text.trim();
			}
		}
		return "";
	}
}

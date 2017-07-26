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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import com.alibaba.fastjson.JSON;
import com.auto.test.common.constant.Const;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.controller.BaseController;
import com.auto.test.core.ui.DynaCompileExe;
import com.auto.test.entity.UCode;
import com.auto.test.service.IUiCodeService;
import com.auto.test.utils.FileUtil;

@RestController
@RequestMapping(value = "ui/code")
public class UiCodeController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UiCodeController.class);
	
	@Resource
	private IUiCodeService uiCodeService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getUiCode(HttpServletRequest request) {
		logger.info("[Code]==>请求页面[ui/code],登录用户[" + getCurrentUserName(request) + "]");
		return success("ui/code", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/page/id={id}", method = RequestMethod.GET)
	public ModelAndView getUiCodeClass(HttpServletRequest request, @PathVariable("id") String id) {
		logger.info("[Code]==>请求页面[ui/code],登录用户[" + getCurrentUserName(request) + "],id[" + id + "]");
		UCode uCode = uiCodeService.findById(Integer.parseInt(id));
		if(uCode != null){
			UCode code = new UCode(uCode.getCls().replace(".java", ""), HtmlUtils.htmlEscape(uCode.getDescription()), uCode.getDevices());
			return success(JSON.toJSONString(code), "ui/code", getCurrentUserName(request));
		}else{
			return success("ui/code", getCurrentUserName(request));
		}
	}
	
	@RequestMapping(value = "/default/code/cls={cls}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDefaultCodeData(@PathVariable("cls") String cls) {
		if(cls == null || cls.isEmpty()){
			String className = getRandomClassName();
			StringBuffer data = new StringBuffer();
			data.append("import com.auto.test.core.ui.log.Log;").append("\r\n");
			data.append("\r\n");
			data.append("public class ").append(className).append(" {").append("\r\n");
			data.append("\t").append("public static Log log = new Log(").append(className).append(".class);").append("\r\n").append("\r\n");
			data.append("\t").append("public void execute() {").append("\r\n");
			data.append("\t\t").append("log.info(\"" + className + " Start...\");").append("\r\n");
			data.append("\t\t").append("\r\n");
			data.append("\t\t").append("log.info(\"" + className + " End...\");").append("\r\n");
			data.append("\t").append("}").append("\r\n");
			data.append("\t").append("\r\n");
			data.append("}");
			logger.info("[Code]==>Init:\r\n" + data.toString());
			return successJson(data.toString());
		}else{
			String path = Const.UI_CODE_PATH + File.separator + cls;
			String fileName = cls + ".java";
			FileUtil fileUtil = new FileUtil();
			File file = fileUtil.getFile(path, fileName);
			if(file.exists()){
				String javaCode = fileUtil.readJavaFile(file);
				logger.info("[Code]==>InitCode:\r\n" + javaCode);
				String logName = fileUtil.getLastLogFile(path);
				if(logName != null && !logName.isEmpty()){
					File logFile = fileUtil.getFile(path, logName);
					String logText = fileUtil.readJavaFile(logFile);
					logger.info("[Code]==>InitLog:\r\n" + logText);
					return successJson(javaCode, logText);
				}
				return successJson(javaCode);
			}else{
				logger.error("[Code]==>文件不存在" + "[" + path + File.separator + fileName + "]");
				return failedJson("文件不存在" + "[" + path + File.separator + "\r\n" + fileName + "]");
			}
		}
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(HttpServletRequest request, @RequestParam("ui-code-desc") String desc, @RequestParam("ui-code-devices") String devices, @RequestParam("ui-code-java") String code) {
		String className = subStr(code, "class", "\\{");
		String path = Const.UI_CODE_PATH + File.separator + className;
		String fileName = className + ".java";
		logger.info("[Code]==>Save[" + path + File.separator + fileName + "]\r\n" + code);
		List<UCode> codeList = uiCodeService.findByCls(fileName);
		FileUtil fileUtil = new FileUtil();
		if(codeList != null && !codeList.isEmpty()){
			fileUtil.deleteFile(path, fileName);
			if(fileUtil.writeJavaFile(path, fileName, code)){
				UCode uCode = new UCode(codeList.get(0).getId(), trimArray(devices), path, fileName, desc.trim(), getCurrentUserName(request));
				uiCodeService.update(uCode);
				return successJson(uCode);
			}else{
				logger.error("[Code]==>文件保存失败" + "[" + path + File.separator + fileName + "]");
				return failedJson("文件保存失败" + "[" + path + File.separator + "\r\n" + fileName + "]");
			}
		}else{
			if(fileUtil.writeJavaFile(path, fileName, code)){
				UCode uCode = new UCode(trimArray(devices), path, fileName, desc.trim(), getCurrentUserName(request));
				uiCodeService.create(uCode);
				return successJson(uCode);
			}else{
				logger.error("[Code]==>文件保存失败" + "[" + path + File.separator + fileName + "]");
				return failedJson("文件保存失败" + "[" + path + File.separator + "\r\n" + fileName + "]");
			}
		}
	}
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> runCode(HttpServletRequest request, @RequestParam("ui-code-device") String device, @RequestParam("ui-code-java") String code) {
		String className = subStr(code, "class", "\\{");
		String path = Const.UI_CODE_PATH + File.separator + className;
		String fileName = className + ".java";
		FileUtil fileUtil = new FileUtil();
		File file = fileUtil.getFile(path, fileName);
		if(file.exists()){
			String javaCode = fileUtil.readJavaFile(file);
			logger.info("[Code]==>Run[" + path + File.separator + fileName + "]\r\n" + javaCode);
			DynaCompileExe dce = (DynaCompileExe) SpringContext.getBean("dynaCompileExe");
			dce.execute(className, javaCode);
			return successJson();
		}else{
			logger.error("[Code]==>文件不存在" + "[" + path + File.separator + fileName + "]");
			return failedJson("文件不存在" + "[" + path + File.separator + "\r\n" + fileName + "]");
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
		SimpleDateFormat sdf = new SimpleDateFormat("'A'yyyyMMddHHmmss'T'");
		StringBuffer sb = new StringBuffer(sdf.format(new Date()));
		for (int i = 0; i < 4; i++) {
			if((int)(Math.random() * 10) % 2 == 0){
				sb.append((int)(Math.random() * 10));
			}else{
				sb.append((char)(Math.random() * 26 + 65));
			}
		}
		return sb.toString();
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
	
	public File[] concat(File[] a, File[] b) {
		File[] c = new File[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
}

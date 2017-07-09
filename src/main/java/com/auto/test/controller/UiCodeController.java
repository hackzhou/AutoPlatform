package com.auto.test.controller;

import java.io.File;
import java.util.Map;
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
import com.auto.test.core.ui.DynamicCompileExecute;
import com.auto.test.utils.FileUtil;

@RestController
@RequestMapping(value = "ui/code")
public class UiCodeController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(UiCodeController.class);

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getUiCode(HttpServletRequest request) {
		return success("ui/code", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(HttpServletRequest request, @RequestParam("ui-code-id") String id, @RequestParam("ui-code-java") String code) {
		System.out.println("id:" + id);
		System.out.println("code:" + code);
		String dirPath = System.getProperty("user.home") + File.separator + Const.AUTO_PLATFORM + File.separator + Const.UI_CODE_TEMP + File.separator + subStr(code, "package", ";");
		System.out.println("dirPath:" + dirPath);
		FileUtil.writeJavaFile(dirPath, subStr(code, "class", "\\{") + ".java", code);
		//run
		run();
		return successJson();
	}
	
	private void run(){
		String[] fileNames = {"Hello.java"};
		String packageName = "temp002";
		String className = "Hello";
		String methodName = "execute";
		File[] files = DynamicCompileExecute.getFiles(packageName, fileNames);
		if(DynamicCompileExecute.compile(files)){
			DynamicCompileExecute.execute(packageName, className, methodName);
		}else{
			System.out.println("Error!!!");
		}
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

package com.auto.test.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;

@RestController
@RequestMapping(value = "api/upload")
public class ApiUploadController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiUploadController.class);
	
	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public ModelAndView  fileUpload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws IOException {
		System.out.println("okokok");
		long startTime = System.currentTimeMillis();
		System.out.println("fileName：" + file.getOriginalFilename());
		String path = "C:/Users/Administrator/Desktop/" + file.getOriginalFilename();
		File newFile = new File(path);
		file.transferTo(newFile);
		long endTime = System.currentTimeMillis();
		System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + " ms");
		return success("redirect:/api/setting/list", getCurrentUserName(request));
	}
	
}

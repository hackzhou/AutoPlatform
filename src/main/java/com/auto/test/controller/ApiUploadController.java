package com.auto.test.controller;

import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AInterface;
import com.auto.test.service.IApiInterfaceService;
import com.auto.test.utils.ExcelUtil;

@RestController
@RequestMapping(value = "api/upload")
public class ApiUploadController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiUploadController.class);
	
	@Resource
	private IApiInterfaceService interfaceService;
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public ModelAndView fileUpload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws Exception {
		if(file.isEmpty()){
			return failMsg("文件是空或者不存在！", "api/setting");
		}
		if(!file.getOriginalFilename().endsWith("xlsx")){
            return failMsg("文件不是Excel！", "api/setting");
        }
		List<AInterface> list = new ExcelUtil().readXls(file.getInputStream());
		interfaceService.exportApiInterface(list);
		return success("success", "redirect:/api/setting/list", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {
		File file = ResourceUtils.getFile("classpath:template/ExportIntetface.xlsx");
		String fileName = new String("接口批量导入模板.xlsx".getBytes("UTF-8"),"iso-8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

}

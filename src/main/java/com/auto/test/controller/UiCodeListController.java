package com.auto.test.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.auto.test.common.constant.Const;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.UCode;
import com.auto.test.entity.UDevice;
import com.auto.test.service.IUiCodeService;
import com.auto.test.utils.FileUtil;

@RestController
@RequestMapping(value = "ui/list")
public class UiCodeListController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UiCodeListController.class);
	
	@Resource
	private IUiCodeService uiCodeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getCode(HttpServletRequest request) {
		logger.info("[CodeList]==>请求页面[ui/list],登录用户[" + getCurrentUserName(request) + "]");
		return success("ui/list", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCode() {
		logger.info("[CodeList]==>获取所有UI编码数据！");
		List<UCode> list = uiCodeService.findAll();
		if(list != null && !list.isEmpty()){
			for (UCode uCode : list) {
				uiCodeService.evict(uCode);
				uCode.setPath(uCode.getPath().replace(System.getProperty("user.home"), "") + File.separator + uCode.getCls());
				uCode.setDescription(HtmlUtils.htmlEscape(uCode.getDescription()));
				List<UDevice> devList = uiCodeService.findByDevices(uCode.getDevices());
				if(devList != null && !devList.isEmpty()){
					String udids = "";
					for (UDevice uDevice : devList) {
						udids += "," + uDevice.getUdid() ;
					}
					if(udids.startsWith(",")){
						udids = udids.substring(1);
					}
					uCode.setMemo(udids);
				}
			}
		}
		return successJson(list);
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteCode(@PathVariable("id") String id) {
		try {
			UCode uCode = uiCodeService.findById(Integer.parseInt(id));
			if(uCode != null){
				new FileUtil().deleteDir(Const.UI_CODE_PATH + File.separator + uCode.getCls().replaceAll(".java", ""));
				uiCodeService.delete(Integer.parseInt(id));
				logger.info("[CodeList]==>删除UI编码[id=" + id + "]成功！");
			}
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[CodeList]==>删除UI编码失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
}

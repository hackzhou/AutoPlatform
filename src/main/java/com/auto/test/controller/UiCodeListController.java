package com.auto.test.controller;

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

import com.auto.test.common.constant.Const;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.UCode;
import com.auto.test.service.IUiCodeService;
import com.auto.test.utils.FileUtil;

@RestController
@RequestMapping(value = "ui/list")
public class UiCodeListController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(UiCodeListController.class);
	
	@Resource
	private IUiCodeService uiCodeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getCode(HttpServletRequest request) {
		return success("ui/list", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCode() {
		logger.info("[Code]==>获取所有UI编码数据！");
		List<UCode> list = uiCodeService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteCode(@PathVariable("id") String id) {
		try {
			UCode uCode = uiCodeService.findById(Integer.parseInt(id));
			if(uCode != null){
				new FileUtil().delete(Const.UI_CODE_PATH, uCode.getCls());
				uiCodeService.delete(Integer.parseInt(id));
				logger.info("[Code]==>删除UI编码[id=" + id + "]成功！");
			}
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Code]==>删除UI编码失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.AAccount;
import com.auto.test.service.IApiAccountService;

@RestController
@RequestMapping(value = "api/account")
public class ApiAccountController extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ApiAccountController.class);

	@Resource
	private IApiAccountService accountService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllAccount(HttpServletRequest request) {
		List<AAccount> list = accountService.getAllAccount();
		return success(list, "api/account", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccountData() {
		List<AAccount> list = accountService.getAllAccount();
		return successJson(list);
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-account-id") String id, @RequestParam("api-account-loginname") String loginname, @RequestParam("api-account-password") String password) {
		if(id == null || id.isEmpty()){
			Integer pid = accountService.create(new AAccount(loginname, password));
			if(pid != null){
				return successJson();
			}else{
				return failedJson();
			}
		}else{
			AAccount aAccount = accountService.update(new AAccount(Integer.parseInt(id), loginname, password));
			if(aAccount != null){
				return successJson();
			}else{
				return failedJson();
			}
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteAccount(@PathVariable("id") String id) {
		accountService.delete(Integer.parseInt(id));
		return successJson();
	}
	
}

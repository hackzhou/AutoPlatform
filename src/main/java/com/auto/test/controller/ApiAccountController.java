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
		List<AAccount> list = accountService.findAll();
		return success(list, "api/account", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccountData() {
		List<AAccount> list = accountService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAccountById(@PathVariable("id") String id) {
		AAccount aAccount = accountService.findById(Integer.parseInt(id));
		if(aAccount != null){
			return successJson(aAccount);
		}
		return failedJson("该账号不存在！");
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAccountByName(@RequestParam("api-account-id") String id, @RequestParam("api-account-loginname") String loginname) {
		if(id != null && !id.isEmpty()){
			AAccount aAccount = accountService.findById(Integer.parseInt(id));
			if(aAccount != null && aAccount.getLoginname() != null && aAccount.getLoginname().equals(loginname)){
				return successJson();
			}
		}
		List<AAccount> list = accountService.findByName(loginname);
		if(list == null || list.isEmpty()){
			return successJson();
		}
		return failedJson("该账号[" + loginname + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-account-id") String id, @RequestParam("api-account-loginname") String loginname, @RequestParam("api-account-password") String password) {
		try {
			if(id == null || id.isEmpty()){
				Integer pid = accountService.create(new AAccount(loginname.trim(), password.trim()));
				if(pid != null){
					return successJson();
				}else{
					return failedJson("添加账号失败！");
				}
			}else{
				AAccount aAccount = accountService.update(new AAccount(Integer.parseInt(id), loginname.trim(), password.trim()));
				if(aAccount != null){
					return successJson();
				}else{
					return failedJson("更新账号失败！");
				}
			}
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteAccount(@PathVariable("id") String id) {
		try {
			accountService.delete(Integer.parseInt(id));
			return successJson();
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
}

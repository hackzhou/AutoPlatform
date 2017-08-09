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
	private static Logger logger = LoggerFactory.getLogger(ApiAccountController.class);

	@Resource
	private IApiAccountService accountService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllAccount(HttpServletRequest request) {
		logger.info("[Account]==>请求页面[api/account],登录用户[" + getCurrentUserName(request) + "]");
		return success("api/account", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccountData() {
		logger.info("[Account]==>获取所有测试账号数据！");
		List<AAccount> list = accountService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/list/data/type={type}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccountDataByType(@PathVariable("type") String type) {
		logger.info("[Account]==>获取所有测试账号数据！");
		List<AAccount> list = accountService.findByTypeOrder(isNull(type) ? "0" : type);
		return successJson(list);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAccountById(@PathVariable("id") String id) {
		AAccount aAccount = accountService.findById(Integer.parseInt(id));
		if(aAccount != null){
			logger.info("[Account]==>获取测试账号[id=" + id + "]数据！");
			return successJson(aAccount);
		}
		logger.error("[Account]==>获取测试账号[id=" + id + "]不存在！");
		return failedJson("获取测试账号[id=" + id + "]不存在！");
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAccountByName(@RequestParam("api-account-id") String id, @RequestParam("api-account-loginname") String loginname) {
		if(!isNull(id)){
			AAccount aAccount = accountService.findById(Integer.parseInt(id));
			if(aAccount != null && aAccount.getLoginname() != null && aAccount.getLoginname().equals(loginname)){
				logger.info("[Account]==>验证测试账号[loginname=" + loginname + "]是本身！");
				return successJson();
			}
		}
		List<AAccount> list = accountService.findByName(loginname);
		if(list == null || list.isEmpty()){
			logger.info("[Account]==>验证测试账号[loginname=" + loginname + "]不存在！");
			return successJson();
		}
		logger.error("[Account]==>验证测试账号[loginname=" + loginname + "]已存在！");
		return failedJson("验证测试账号[loginname=" + loginname + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-account-id") String id, @RequestParam("api-account-token") String token, @RequestParam("api-account-loginname") String loginname, @RequestParam("api-account-password") String password) {
		try {
			if(isNull(id)){
				Integer aid = accountService.create(new AAccount(token.trim(), loginname.trim(), trimArray(password)));
				if(aid != null){
					logger.info("[Account]==>添加测试账号[id=" + aid + ",loginname=" + loginname + "]成功！");
					return successJson();
				}else{
					logger.error("[Account]==>添加测试账号[loginname=" + loginname + "]失败！");
					return failedJson("添加测试账号[loginname=" + loginname + "]失败！");
				}
			}else{
				AAccount aAccount = accountService.update(new AAccount(Integer.parseInt(id), token.trim(), loginname.trim(), trimArray(password)));
				if(aAccount != null){
					logger.info("[Account]==>更新测试账号[id=" + id + ",loginname=" + loginname + "]成功！");
					return successJson();
				}else{
					logger.error("[Account]==>更新测试账号[id=" + id + ",loginname=" + loginname + "]失败！");
					return failedJson("更新测试账号[id=" + id + ",loginname=" + loginname + "]失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Account]==>添加/更新测试账号失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteAccount(@PathVariable("id") String id) {
		try {
			accountService.delete(Integer.parseInt(id));
			logger.info("[Account]==>删除测试账号[id=" + id + "]成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Account]==>删除测试账号失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
}

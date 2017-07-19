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
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiVersionService;

@RestController
@RequestMapping(value = "api/version")
public class ApiVersionController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ApiVersionController.class);
	
	@Resource
	private IApiVersionService versionService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllVersion(HttpServletRequest request) {
		logger.info("[Version]==>请求页面[api/version],登录用户[" + getCurrentUserName(request) + "]");
		return success("api/version", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllVersionData() {
		logger.info("[Version]==>获取所有版本数据！");
		List<AVersion> list = versionService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getVersionById(@PathVariable("id") String id) {
		AVersion aVersion = versionService.findById(Integer.parseInt(id));
		if(aVersion != null){
			logger.info("[Version]==>获取版本[id=" + id + "]数据！");
			return successJson(aVersion);
		}
		logger.error("[Version]==>获取版本[id=" + id + "]不存在！");
		return failedJson("获取版本[id=" + id + "]不存在！");
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getVersionByVersion(@RequestParam("api-version-id") String id, @RequestParam("api-version-version") String version) {
		if(!isNull(id)){
			AVersion aVersion = versionService.findById(Integer.parseInt(id));
			if(aVersion != null && aVersion.getVersion() != null && aVersion.getVersion().equals(version)){
				logger.info("[Version]==>验证版本[version=" + version + "]是本身！");
				return successJson();
			}
		}
		List<AVersion> list = versionService.findByVersion(version);
		if(list == null || list.isEmpty()){
			logger.info("[Version]==>验证版本[version=" + version + "]不存在！");
			return successJson();
		}
		logger.error("[Version]==>验证版本[version=" + version + "]已存在！");
		return failedJson("验证版本[version=" + version + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-version-id") String id, @RequestParam("api-version-version") String version, @RequestParam("api-version-channel") String channel) {
		try {
			if(isNull(id)){
				Integer vid = versionService.create(new AVersion(version.trim(), trimArray(channel)));
				if(vid != null){
					logger.info("[Version]==>添加版本[id=" + vid + ",version=" + version + "]成功！");
					return successJson();
				}else{
					logger.error("[Version]==>添加版本[version=" + version + "]失败！");
					return failedJson("添加版本[version=" + version + "]失败！");
				}
			}else{
				AVersion aVersion = versionService.update(new AVersion(Integer.parseInt(id), version.trim(), trimArray(channel)));
				if(aVersion != null){
					logger.info("[Version]==>更新版本[id=" + id + ",version=" + version + "]成功！");
					return successJson();
				}else{
					logger.error("[Version]==>更新版本[id=" + id + ",version=" + version + "]失败！");
					return failedJson("更新版本[id=" + id + ",version=" + version + "]失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Version]==>添加/更新版本失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteVersion(@PathVariable("id") String id) {
		try {
			versionService.deleteCascade(Integer.parseInt(id));
			logger.info("[Version]==>删除版本[id=" + id + "]以及版本下案例成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Version]==>删除版本失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
}

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
import com.auto.test.entity.AInterface;
import com.auto.test.service.IApiInterfaceService;

@RestController
@RequestMapping(value = "api/interface")
public class ApiInterfaceController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(ApiInterfaceController.class);
	
	@Resource
	private IApiInterfaceService interfaceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllInterface(HttpServletRequest request) {
		logger.info("[Interface]==>请求页面[api/interface],登录用户[" + getCurrentUserName(request) + "]");
		return success("api/interface", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllInterfaceData() {
		logger.info("[Interface]==>获取所有接口数据！");
		List<AInterface> list = interfaceService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/list/data/projectid={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDataByProject(@PathVariable("id") String id) {
		logger.info("[Interface]==>获取接口[project=" + id + "]数据！");
		if(id != null && !id.isEmpty()){
			List<AInterface> list = interfaceService.findByProjectId(Integer.parseInt(id));
			return successJson(list);
		}else{
			return successJson();
		}
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getInterfaceById(@PathVariable("id") String id) {
		AInterface aInterface = interfaceService.findById(Integer.parseInt(id));
		if(aInterface != null){
			logger.info("[Interface]==>获取接口[id=" + id + "]数据！");
			return successJson(aInterface);
		}
		logger.error("[Interface]==>获取接口[id=" + id + "]不存在！");
		return failedJson("获取接口[id=" + id + "]不存在！");
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInterfaceByUrl(@RequestParam("api-interface-id") String id, @RequestParam("api-interface-url") String url, @RequestParam("api-interface-project") String project) {
		if(id != null && !id.isEmpty()){
			AInterface aInterface = interfaceService.findById(Integer.parseInt(id));
			if(aInterface != null && aInterface.getUrl() != null && aInterface.getUrl().equals(url)){
				logger.info("[Interface]==>验证项目下接口[url=" + url + "]是本身！");
				return successJson();
			}
		}
		List<AInterface> list = interfaceService.findByProjectUrl(Integer.parseInt(project), url);
		if(list == null || list.isEmpty()){
			logger.info("[Interface]==>验证项目下接口[url=" + url + "]不存在！");
			return successJson();
		}
		logger.error("[Interface]==>验证项目下接口[url=" + url + "]已存在！");
		return failedJson("验证项目下接口[url=" + url + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-interface-id") String id, @RequestParam("api-interface-project") String project, @RequestParam("api-interface-name") String name, @RequestParam("api-interface-type") String type, @RequestParam("api-interface-url") String url, @RequestParam("api-interface-description") String description) {
		try {
			if(id == null || id.isEmpty()){
				Integer iid = interfaceService.create(new AInterface(Integer.parseInt(project), name.trim(), type.trim(), url.trim(), description.trim()));
				if(iid != null){
					logger.info("[Interface]==>添加接口[id=" + iid + ",url=" + url + "]成功！");
					return successJson();
				}else{
					logger.error("[Interface]==>添加接口[url=" + url + "]失败！");
					return failedJson("添加接口[url=" + url + "]失败！");
				}
			}else{
				AInterface aInterface = interfaceService.update(new AInterface(Integer.parseInt(id), Integer.parseInt(project), name.trim(), type.trim(), url.trim(), description.trim()));
				if(aInterface != null){
					logger.info("[Interface]==>更新接口[id=" + id + ",url=" + url + "]成功！");
					return successJson();
				}else{
					logger.error("[Interface]==>更新接口[id=" + id + ",url=" + url + "]失败！");
					return failedJson("更新接口[id=" + id + ",url=" + url + "]失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Interface]==>添加/更新接口失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteInterface(@PathVariable("id") String id) {
		try {
			interfaceService.deleteCascade(Integer.parseInt(id));
			logger.info("[Interface]==>删除接口[id=" + id + "]以及接口下案例成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Interface]==>删除接口失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
}

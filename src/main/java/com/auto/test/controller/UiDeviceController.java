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
import com.auto.test.entity.UDevice;
import com.auto.test.service.IUiDeviceService;

@RestController
@RequestMapping(value = "ui/device")
public class UiDeviceController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UiDeviceController.class);

	@Resource
	private IUiDeviceService deviceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllDevice(HttpServletRequest request) {
		logger.info("[Device]==>请求页面[ui/device],登录用户[" + getCurrentUserName(request) + "]");
		return success("ui/device", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllDeviceData() {
		logger.info("[Device]==>获取所有设备数据！");
		List<UDevice> list = deviceService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDeviceById(@PathVariable("id") String id) {
		UDevice uDevice = deviceService.findById(Integer.parseInt(id));
		if(uDevice != null){
			logger.info("[Device]==>获取设备[id=" + id + "]数据！");
			return successJson(uDevice);
		}
		logger.error("[Device]==>获取设备[id=" + id + "]不存在！");
		return failedJson("获取设备[id=" + id + "]不存在！");
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDeviceByName(@RequestParam("ui-device-id") String id, @RequestParam("ui-device-udid") String udid) {
		if(!isNull(id)){
			UDevice uDevice = deviceService.findById(Integer.parseInt(id));
			if(uDevice != null && uDevice.getUdid() != null &&  uDevice.getUdid().equals(udid)){
				logger.info("[Device]==>验证设备[udid=" + udid + "]是本身！");
				return successJson();
			}
		}
		List<UDevice> list = deviceService.findByUdid(udid);
		if(list == null || list.isEmpty()){
			logger.info("[Device]==>验证设备[udid=" + udid + "]不存在！");
			return successJson();
		}
		logger.error("[Device]==>验证设备[udid=" + udid + "]已存在！");
		return failedJson("验证设备[udid=" + udid + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("ui-device-id") String id, @RequestParam("ui-platform-name") String pname, 
			@RequestParam("ui-platform-version") String pver, @RequestParam("ui-nickname") String nickname, 
			@RequestParam("ui-device-name") String deviceName, @RequestParam("ui-device-udid") String udid, 
			@RequestParam("ui-server-url") String serverUrl, @RequestParam("ui-device-app") String app) {
		try {
			if(isNull(id)){
				Integer did = deviceService.create(new UDevice(pname.trim(), pver.trim(), nickname.trim(), deviceName.trim(), udid.trim(), serverUrl.trim(), app.trim()));
				if(did != null){
					logger.info("[Device]==>添加设备[id=" + did + ",udid=" + udid + "]成功！");
					return successJson();
				}else{
					logger.error("[Device]==>添加设备[udid=" + udid + "]失败！");
					return failedJson("添加设备[udid=" + udid + "]失败！");
				}
			}else{
				UDevice uDevice = deviceService.update(new UDevice(Integer.parseInt(id), pname.trim(), pver.trim(), nickname.trim(), deviceName.trim(), udid.trim(), serverUrl.trim(), app.trim()));
				if(uDevice != null){
					logger.info("[Device]==>更新设备[id=" + id + ",udid=" + udid + "]成功！");
					return successJson();
				}else{
					logger.error("[Device]==>更新设备[id=" + id + ",udid=" + udid + "]失败！");
					return failedJson("更新设备[id=" + id + ",udid=" + udid + "]失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Device]==>添加/更新设备失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteDevice(@PathVariable("id") String id) {
		try {
			deviceService.delete(Integer.parseInt(id));
			logger.info("[Device]==>删除设备[id=" + id + "]成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Device]==>删除设备失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}

}

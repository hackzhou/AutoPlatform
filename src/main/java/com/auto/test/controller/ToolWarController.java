package com.auto.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.bean.SimpleJsonResult;
import com.auto.test.common.constant.Const;
import com.auto.test.common.controller.BaseController;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.utils.FileUtil;
import com.auto.test.utils.HttpUtil;
import com.auto.test.utils.SvnUtil;
import com.auto.test.utils.WarUtil;

@RestController
@RequestMapping(value = "tool/war")
public class ToolWarController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ToolWarController.class);

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getToolWar(HttpServletRequest request) {
		logger.info("[War]==>请求页面[tool/war],登录用户[" + getCurrentUserName(request) + "]");
		return success("tool/war", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/types", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getToolWarTypes() {
		logger.info("[War]==>获取所有SVN列表数据！");
		try {
			List<String> list = new SvnUtil().getAllFileName();
			return successJson(list);
		} catch (Exception e) {
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView runToolWar(HttpServletRequest request, @RequestParam("tool-war-ip") String ip, @RequestParam("tool-war-name") String name, @RequestParam("file") CommonsMultipartFile file) {
		if(ip == null || ip.isEmpty()){
			logger.error("[War]==>服务器地址不能为空！");
			return failMsg("服务器地址不能为空！", "tool/war");
		}else if(name == null || name.isEmpty()){
			logger.error("[War]==>对比文件不能为空！");
			return failMsg("对比文件不能为空！", "tool/war");
		}else if(file == null || file.isEmpty()){
			logger.error("[War]==>上传War包不能为空！");
			return failMsg("上传War包不能为空！", "tool/war");
		}else if(!file.getOriginalFilename().endsWith("war")){
			logger.error("[War]==>上传文件不是War包！");
            return failMsg("上传文件不是War包！", "tool/war");
        }
		try {
			run(ip, name, file);
			return success("redirect:/tool/war/page", getCurrentUserName(request));
		} catch (Exception e) {
			return failMsg(e.getMessage(), "redirect:/tool/war/page");
		}
	}
	
	private synchronized void run(String ip, String name, CommonsMultipartFile file) throws Exception{
		saveWarFile(file);
		if(!new WarUtil().compareZip(name, file.getOriginalFilename())){
			throw new BusinessException("svn上文件内容为空或者在war包的application.properties文件中存在新增字段，请及时更新上传到svn之后再部署！");
		}
		restartServer(ip, file.getOriginalFilename());
	}
	
	private void restartServer(String ip, String fileName){
		String port = "8080";
		String startUrl = "http://" + ip + ":" + port + "/AutoTest/rs/servers/server/start";
		String stopUrl = "http://" + ip + ":" + port + "/AutoTest/rs/servers/server/stop";
		String deleteUrl = "http://" + ip + ":" + port + "/AutoTest/rs/files/file/delete/name=" + fileName.split(".")[0];
		String uploadUrl = "http://" + ip + ":" + port + "/AutoTest/rs/files/file/upload";
		HttpUtil hu = new HttpUtil();
		SimpleJsonResult stopResult = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(stopUrl));
		if(stopResult.isSuccess()){
			SimpleJsonResult deleteResult = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(deleteUrl));
			if(deleteResult.isSuccess()){
				SimpleJsonResult uploadResult = hu.json2JavaBean(SimpleJsonResult.class, hu.sendPost(uploadUrl, new File(Const.PATH_FILE + File.separator + fileName)));
				if(uploadResult.isSuccess()){
					SimpleJsonResult startResult = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(startUrl));
					if(!startResult.isSuccess()){
						throw new BusinessException(stopResult.getMsg());
					}
				}else{
					throw new BusinessException(stopResult.getMsg());
				}
			}else{
				throw new BusinessException(stopResult.getMsg());
			}
		}else{
			throw new BusinessException(stopResult.getMsg());
		}
	}
	
	private void saveWarFile(CommonsMultipartFile file) throws IllegalStateException, IOException{
		new FileUtil().deleteDir(Const.PATH_FILE_WAR);
		File tmpFile = new File(Const.PATH_FILE_WAR);
		if(!tmpFile.exists()){
			tmpFile.mkdirs();
		}
		tmpFile = new File(Const.PATH_FILE_WAR + File.separator + file.getOriginalFilename());
		file.transferTo(tmpFile);
	}
}

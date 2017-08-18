package com.auto.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
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
	private static String WAR_PROJECT = "AutoTest";
	private static Integer WAR_PORT = 8090;

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
	
	@RequestMapping(value = "/ip={ip}/servers", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getToolWarServers(@PathVariable("ip") String ip) {
		logger.info("[War]==>获取服务器[" + ip +"]所有服务数据！");
		try {
			ip = isNull(ip) ? "192.168.101.181" : ip;
			HttpUtil hu = new HttpUtil();
			if(hu.isAvailablePort(ip, WAR_PORT)){
				SimpleJsonResult sjr = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet("http://" + ip + ":" + WAR_PORT + "/" + WAR_PROJECT +"/rs/files/file/list"));
				if(sjr.isSuccess()){
					return successJson(sjr.getData());
				}else{
					return failedJson(sjr.getMsg());
				}
			}else{
				return failedJson("服务未启动[" + ip +":" + WAR_PORT + "][" + WAR_PROJECT +"]");
			}
		} catch (Exception e) {
			return failedJson("服务错误[" + ip + "][" + e.getMessage() + "]");
		}
	}
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView runToolWar(HttpServletRequest request, @RequestParam("tool-war-ip") String ip, @RequestParam("tool-war-server") String server, @RequestParam("tool-war-name") String name, @RequestParam("file") CommonsMultipartFile file) {
		if(ip == null || ip.isEmpty()){
			logger.error("[War]==>服务器地址不能为空！");
			return failMsg("服务器地址不能为空！", "tool/war");
		}else if(server == null || server.isEmpty()){
			logger.error("[War]==>部署项目不能为空！");
			return failMsg("部署项目不能为空！", "tool/war");
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
			runWar(ip, name, file);
			restartServer(ip, server, file.getOriginalFilename());
			return success("redirect:/tool/war/page", getCurrentUserName(request));
		} catch (Exception e) {
			return failMsg(e.getMessage(), "tool/war");
		}
	}
	
	private synchronized void runWar(String ip, String name, CommonsMultipartFile file) throws Exception{
		saveWarFile(file);
		logger.info("[War]==Svn[" + name + "],War[" + file.getOriginalFilename() + "]");
		if(!new WarUtil().compareZip(name, file.getOriginalFilename())){
			logger.error("svn上文件内容为空或者在war包的application.properties文件中存在新增字段，请及时更新上传到svn之后再部署！");
			throw new BusinessException("svn上文件内容为空或者在war包的application.properties文件中存在新增字段，请及时更新上传到svn之后再部署！");
		}
	}
	
	private synchronized void restartServer(String ip, String server, String fileName){
		String startUrl = "http://" + ip + ":" + WAR_PORT + "/" + WAR_PROJECT + "/rs/servers/server/server=" + server + "/start";
		String stopUrl = "http://" + ip + ":" + WAR_PORT + "/" + WAR_PROJECT + "/rs/servers/server/server=" + server + "/kill";
		String deleteUrl = "http://" + ip + ":" + WAR_PORT + "/" + WAR_PROJECT + "/rs/files/file/server=" + server + "/project=" + fileName.split(".")[0] + "/delete";
		String uploadUrl = "http://" + ip + ":" + WAR_PORT + "/" + WAR_PROJECT + "/rs/files/file/server=" + server + "/upload";
		HttpUtil hu = new HttpUtil();
		if(hu.isAvailablePort(ip, WAR_PORT)){
			logger.info("[War]==>关闭服务地址[" + stopUrl + "]");
			SimpleJsonResult stopResult = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(stopUrl));
			if(stopResult.isSuccess()){
				logger.info("[War]==>关闭服务结果[" + stopResult.toString() + "]");
				logger.info("[War]==>删除项目地址[" + deleteUrl + "]");
				SimpleJsonResult deleteResult = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(deleteUrl));
				if(deleteResult.isSuccess()){
					logger.info("[War]==>删除项目结果[" + deleteResult.toString() + "]");
					logger.info("[War]==>上传项目地址[" + uploadUrl + "]");
					SimpleJsonResult uploadResult = hu.json2JavaBean(SimpleJsonResult.class, hu.sendPost(uploadUrl, new File(Const.PATH_FILE + File.separator + fileName)));
					if(uploadResult.isSuccess()){
						logger.info("[War]==>上传项目结果[" + uploadResult.toString() + "]");
						logger.info("[War]==>启动服务地址[" + startUrl + "]");
						SimpleJsonResult startResult = hu.json2JavaBean(SimpleJsonResult.class, hu.sendGet(startUrl));
						if(startResult.isSuccess()){
							logger.info("[War]==>启动服务结果[" + startResult.toString() + "]");
						}else{
							logger.error(startResult.toString());
							throw new BusinessException(startResult.getMsg());
						}
					}else{
						logger.error(uploadResult.toString());
						throw new BusinessException(uploadResult.getMsg());
					}
				}else{
					logger.error(deleteResult.toString());
					throw new BusinessException(deleteResult.getMsg());
				}
			}else{
				logger.error(stopResult.toString());
				throw new BusinessException(stopResult.getMsg());
			}
		}else{
			throw new BusinessException("服务未启动[" + ip +":" + WAR_PORT + "][" + WAR_PROJECT +"]");
		}
	}
	
	private synchronized void saveWarFile(CommonsMultipartFile file) throws IllegalStateException, IOException{
		logger.info("[War]==>删除文件夹[" + Const.PATH_FILE + "]");
		new FileUtil().deleteDir(Const.PATH_FILE);
		File tmpFile = new File(Const.PATH_FILE_WAR);
		if(!tmpFile.exists()){
			logger.info("[War]==>创建文件夹[" + Const.PATH_FILE_WAR + "]");
			tmpFile.mkdirs();
		}
		logger.info("[War]==>复制文件[" + Const.PATH_FILE_WAR + File.separator + file.getOriginalFilename() + "]");
		tmpFile = new File(Const.PATH_FILE_WAR + File.separator + file.getOriginalFilename());
		file.transferTo(tmpFile);
	}
}

package com.auto.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.auto.test.common.constant.ApiRunType;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.controller.BaseController;
import com.auto.test.core.api.service.IApiRunService;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiCaseService;

@Controller
@RequestMapping(value = "api/case")
public class ApiCaseController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ApiCaseController.class);
	
	@Resource
	private IApiCaseService caseService;
	
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> runCase(HttpServletRequest request, @RequestParam("api-case-run-id") String runid, @RequestParam("api-case-run-account") String account) {
		try {
			logger.info("[Case]==>运行案例[id=" + runid + ",account=" + account + ",user=" + getCurrentUserName(request) + "]");
			IApiRunService apiRunService = (IApiRunService) SpringContext.getBean("apiRunService");
			apiRunService.run(ApiRunType.CASE, Integer.parseInt(runid), Integer.parseInt(account), null, getCurrentUserName(request), false, null);
			logger.info("[Case]==>运行案例成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Case]==>运行案例失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllCase(HttpServletRequest request) {
		logger.info("[Case]==>请求页面[api/case],登录用户[" + getCurrentUserName(request) + "]");
		return success("api/case", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCaseData() {
		logger.info("[Case]==>获取所有案例数据！");
		List<ACase> list = caseService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/list/data/pid={pid}/vid={vid}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCaseDataListByProjectVersion(HttpServletRequest request, @PathVariable("pid") String pid, @PathVariable("vid") String vid) {
		logger.info("[Case]==>获取案例[project=" + pid + ",version=" + vid + "]数据！");
		List<ACase> list = null;
		if(isNull(pid)){
			if(isNull(vid)){
				list = caseService.findByMinProjectMaxVersion();
			}else{
				list = caseService.findByMinProjectVersion(Integer.parseInt(vid));
			}
		}else{
			if(isNull(vid)){
				list = caseService.findByProjectMaxVersion(Integer.parseInt(pid));
			}else{
				list = caseService.findByProjectVersion(Integer.parseInt(pid), Integer.parseInt(vid));
			}
		}
		return successJson(list != null ? caseImgUrl2Net(request, list) : new ArrayList<ACase>());
	}
	
	@RequestMapping(value = "/list/data/projectid={pid}/versionid={vid}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCaseDataByProjectVersion(@PathVariable("pid") String pid, @PathVariable("vid") String vid) {
		logger.info("[Case]==>获取案例[project=" + pid + ",version=" + vid + "]数据！");
		if(!isNull(pid) && !isNull(vid)){
			List<ACase> list = caseService.findByProjectVersion(Integer.parseInt(pid), Integer.parseInt(vid));
			return successJson(list);
		}else{
			List<ACase> list = caseService.findByMinProjectMaxVersion();
			return successJson(list);
		}
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCaseById(@PathVariable("id") String id) {
		ACase aCase = caseService.findById(Integer.parseInt(id));
		if(aCase != null){
			caseService.evict(aCase);
			aCase.setBody(jsonFormat(aCase.getBody(), true));
			logger.info("[Case]==>获取案例[id=" + id + "]数据！");
			return successJson(aCase);
		}
		logger.error("[Case]==>获取案例[id=" + id + "]不存在！");
		return failedJson("获取案例[id=" + id + "]不存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(@RequestParam("api-case-id") String id, @RequestParam("api-case-interface") String inter, 
			@RequestParam("api-case-version") String version, @RequestParam("api-case-name") String name, @RequestParam("api-case-strategy") String strategy, 
			@RequestParam("api-case-flag") String flag, @RequestParam("api-case-run") String run,  @RequestParam("api-case-login") String login, 
			@RequestParam("api-case-body") String body, @RequestParam("api-case-result") String result, @RequestParam("api-case-link") String links, 
			@RequestParam("api-case-is-body") String isBody, @RequestParam("api-case-img-path") String img, @RequestParam("api-case-update-img") String uImg) {
		try {
			if("0".equals(isBody)){
				body = null;
			}
			/*if("0".equals(isResult)){
				result = null;
			}*/
			if(result != null && result.trim().isEmpty()){
				result = null;
			}
			if(isNull(id)){
				Integer cid = caseService.create(new ACase(new AVersion(Integer.parseInt(version)), new AInterface(Integer.parseInt(inter)), name.trim(), jsonFormat(body, false), jsonFormat(result, false), trimArray(strategy), trimArray(links), img, Integer.parseInt(flag), Integer.parseInt(run), Integer.parseInt(login)));
				if(cid != null){
					logger.info("[Case]==>添加案例[id=" + cid + ",name=" + name + "]成功！");
					return successJson();
				}else{
					logger.error("[Case]==>添加案例[name=" + name + "]失败！");
					return failedJson("添加案例[name=" + name + "]失败！");
				}
			}else{
				ACase tempCase = caseService.findById(Integer.parseInt(id));
				if(tempCase != null){
					String imgPath = tempCase.getImg();
					if(imgPath != null && !imgPath.isEmpty()){
						if("1".equals(uImg)){
							delImg(imgPath);
							logger.info("[Case]==>删除图片[img=" + imgPath + "]成功！");
						}else{
							img = imgPath;
						}
					}
				}
				ACase aCase = caseService.update(new ACase(Integer.parseInt(id), new AVersion(Integer.parseInt(version)), new AInterface(Integer.parseInt(inter)), name.trim(), jsonFormat(body, false), jsonFormat(result, false), trimArray(strategy), trimArray(links), img, Integer.parseInt(flag), Integer.parseInt(run), Integer.parseInt(login)));
				if(aCase != null){
					logger.info("[Case]==>更新案例[id=" + id + ",name=" + name + "]成功！");
					return successJson();
				}else{
					logger.error("[Case]==>更新案例[id=" + id + ",name=" + name + "]失败！");
					return failedJson("更新案例[id=" + id + ",name=" + name + "]失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Case]==>添加/更新案例失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteCase(@PathVariable("id") String id) {
		try {
			ACase aCase = caseService.findById(Integer.parseInt(id));
			if(aCase != null){
				String img = aCase.getImg();
				caseService.delete(Integer.parseInt(id));
				logger.info("[Case]==>删除案例[id=" + id + "]成功！");
				if(img != null && !img.isEmpty()){
					delImg(img);
					logger.info("[Case]==>删除图片[img=" + img + "]成功！");
				}
			}
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Case]==>删除案例失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/is/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isJson(@RequestParam("api-case-is-body") String isBody, @RequestParam("api-case-body") String body, @RequestParam("api-case-result") String result) {
		try {
			if("1".equals(isBody)){
				JSON.parseObject(body);
			}
			/*if("1".equals(isResult)){
				JSON.parseObject(result);
			}*/
			if(result != null && !result.isEmpty()){
				JSON.parseObject(result);
			}
			logger.info("[Case]==>JSON数据验证成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Case]==>JSON数据验证失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fileUpload(HttpServletRequest request, @RequestParam("api-case-img") CommonsMultipartFile file) throws Exception {
		if(file == null || file.isEmpty()){
			return failedJson("上传图片为空！");
		}else{
			OutputStream os = null;
			InputStream inputStream = null;
			String rootPath = request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replaceAll("/", "").replace("\\", ""), "ROOT");
			String imgPath = rootPath + "images";
			String fullPath = imgPath + File.separator + timeStamp() + file.getOriginalFilename();
			try {
				File tempFile = new File(rootPath);
				if(!tempFile.exists() && !tempFile.isDirectory()){
					return failedJson("[Case]==>图片上传[" + fullPath + "]路径错误！");
				}
				tempFile = new File(imgPath);
				if(!tempFile.exists() && !tempFile.isDirectory()){
					tempFile.mkdir();
				}
				inputStream = file.getInputStream();
				int len;
				byte[] bs = new byte[1024];
				os = new FileOutputStream(fullPath);
				while ((len = inputStream.read(bs)) != -1) {
	                os.write(bs, 0, len);
	            }
			} finally {
				if(os != null){
					os.close();
				}
				if(inputStream != null){
					inputStream.close();
				}
			}
			return successJson(fullPath);
		}
	}
	
	private List<ACase> caseImgUrl2Net(HttpServletRequest request, List<ACase> list){
		if(list != null && !list.isEmpty()){
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/";
			for (ACase aCase : list) {
				String img = aCase.getImg();
				if(img != null && img.contains("images")){
					aCase.setImg(url + img.split("images")[1].substring(1));
				}
			}
		}
		return list;
	}
	
	private void delImg(String filePath){
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			file.delete();
		}
	}
	
	private String timeStamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS'_'");
		return sdf.format(new Date());
	}
	
}

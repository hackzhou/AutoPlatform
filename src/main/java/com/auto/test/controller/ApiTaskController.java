package com.auto.test.controller;

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
import org.springframework.web.servlet.ModelAndView;
import com.auto.test.common.controller.BaseController;
import com.auto.test.entity.ATask;
import com.auto.test.service.IApiTaskService;

@Controller
@RequestMapping(value = "api/task")
public class ApiTaskController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(ApiTaskController.class);
	
	@Resource
	private IApiTaskService apiTaskService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllTask(HttpServletRequest request) {
		logger.info("[Task]==>请求页面[api/task],登录用户[" + getCurrentUserName(request) + "]");
		return success("api/task", getCurrentUserName(request));
	}
	
	@RequestMapping(value = "/list/data", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllTaskData() {
		logger.info("[Task]==>获取所有定时任务数据！");
		List<ATask> list = apiTaskService.findAll();
		return successJson(list);
	}
	
	@RequestMapping(value = "/repeat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTaskByTime(@RequestParam("api-task-id") String id, @RequestParam("api-task-time") String time) {
		if(id != null && !id.isEmpty()){
			ATask aTask = apiTaskService.findById(Integer.parseInt(id));
			if(aTask != null && aTask.getRunTime() != null && aTask.getRunTime().equals(time)){
				logger.info("[Task]==>验证定时任务[time=" + time + "]是本身！");
				return successJson();
			}
		}
		List<ATask> list = apiTaskService.findByTime(time);
		if(list == null || list.isEmpty()){
			logger.info("[Task]==>验证定时任务[time=" + time + "]不存在！");
			return successJson();
		}
		logger.error("[Task]==>验证定时任务[time=" + time + "]已存在！");
		return failedJson("验证定时任务[time=" + time + "]已存在！");
	}
	
	@RequestMapping(value = "/create/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createOrUpdate(HttpServletRequest request, @RequestParam("api-task-id") String id, @RequestParam("api-task-run") String run,
			@RequestParam("api-task-time") String time, @RequestParam("api-task-project") String project, @RequestParam("api-task-version") String version, 
			@RequestParam("api-task-account") String account) {
		try {
			if(id == null || id.isEmpty()){
				Integer tid = apiTaskService.create(new ATask(project, version, account, Integer.parseInt(run), time, getCurrentUserName(request)));
				if(tid != null){
					logger.info("[Task]==>添加定时任务[id=" + tid + ",project=" + project + ",time=" + time + "]成功！");
					return successJson();
				}else{
					logger.error("[Task]==>添加定时任务[project=" + project + ",time=" + time + "]失败！");
					return failedJson("添加定时任务[project=" + project + ",time=" + time + "]失败！");
				}
			}else{
				ATask aTask = apiTaskService.update(new ATask(Integer.parseInt(id), project, version, account, Integer.parseInt(run), time, getCurrentUserName(request)));
				if(aTask != null){
					logger.info("[Task]==>更新定时任务[id=" + id + ",project=" + project + ",time=" + time + "]成功！");
					return successJson();
				}else{
					logger.error("[Task]==>更新定时任务[id=" + id + ",project=" + project + ",time=" + time + "]失败！");
					return failedJson("更新定时任务[id=" + id + ",project=" + project + ",time=" + time + "]失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Task]==>添加/更新定时任务失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteTask(@PathVariable("id") String id) {
		try {
			apiTaskService.delete(Integer.parseInt(id));
			logger.info("[Task]==>删除定时任务[id=" + id + "]成功！");
			return successJson();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[Task]==>删除定时任务失败[" + e.getMessage() + "]");
			return failedJson(e.getMessage());
		}
	}

}
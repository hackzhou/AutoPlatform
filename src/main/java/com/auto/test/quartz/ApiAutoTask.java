package com.auto.test.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.constant.ApiRunType;
import com.auto.test.common.constant.Const;
import com.auto.test.common.context.SpringContext;
import com.auto.test.core.api.service.IApiRunService;
import com.auto.test.entity.ATask;
import com.auto.test.service.IApiTaskService;

public class ApiAutoTask {
	private Logger logger = LoggerFactory.getLogger(ApiAutoTask.class);
	
	@Resource
	private IApiTaskService apiTaskService;
	
	public void autoRun() throws Exception {
		if(Const.IP_ONLINE.equals(Const.IP_CURRENT)){
			String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
			List<ATask> list = apiTaskService.findAll();
			if(list != null && !list.isEmpty()){
				for (ATask aTask : list) {
					if(new Integer(1).equals(aTask.getRunFlag()) && currentTime.equals(aTask.getRunTime())){
						Integer pid = aTask.getProjecto().getId();
						Integer aid = aTask.getAccounto().getId();
						Integer vid = aTask.getVersiono().getId();
						logger.info("[Task]==>定时任务运行项目[id=" + pid + ",account=" + aid + ",version=" + vid + ",user=" + aTask.getRunby() + "]");
						IApiRunService apiRunService = (IApiRunService) SpringContext.getBean("apiRunService");
						apiRunService.run(ApiRunType.PROJECT, pid, aid, vid, aTask.getRunby());
						logger.info("[Task]==>定时任务运行项目成功！");
					}
				}
			}
		}
	}

}

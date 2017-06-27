package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiTaskDao;
import com.auto.test.entity.ATask;
import com.auto.test.service.IApiTaskService;

@Service("apiTaskService")
public class ApiTaskService implements IApiTaskService {
	
	@Resource(name="apiTaskDao")
	private IApiTaskDao dao;

	@Override
	public List<ATask> findAll() {
		return dao.findAllOrder();
	}
	
	@Override
	public List<ATask> findByProject(Integer pid) {
		return dao.findByProject(pid);
	}

	@Override
	public List<ATask> findByVersion(Integer vid) {
		return dao.findByVersion(vid);
	}

	@Override
	public List<ATask> findByAccount(Integer aid) {
		return dao.findByAccount(aid);
	}

	@Override
	public List<ATask> findByTime(String time) {
		return dao.findByTime(time);
	}
	
	@Override
	public ATask findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(ATask aTask) {
		if(aTask != null){
			aTask.setCreateTime(new Date());
			dao.create(aTask);
			return aTask.getId();
		}
		return null;
	}

	@Override
	public ATask update(ATask aTask) {
		if(aTask != null){
			ATask task = dao.findById(aTask.getId());
			if(task != null){
				task.update(aTask);
				dao.update(task);
				return task;
			}
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		if(id != null){
			dao.deleteById(id);
		}
	}

}

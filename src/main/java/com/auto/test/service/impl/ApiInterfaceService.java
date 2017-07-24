package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.common.constant.HttpType;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.dao.IApiInterfaceDao;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;
import com.auto.test.service.IApiInterfaceService;

@Service("apiInterfaceService")
public class ApiInterfaceService implements IApiInterfaceService {
	
	@Resource(name="apiInterfaceDao")
	private IApiInterfaceDao dao;
	
	@Resource(name="apiCaseDao")
	private IApiCaseDao daoCase;
	
	@Resource(name="apiProjectDao")
	private IApiProjectDao projectDao;

	@Override
	public List<AInterface> findAll() {
		return dao.findAllOrder();
	}
	
	@Override
	public List<AInterface> findByProjectId(Integer id) {
		return dao.findByProjectId(id);
	}
	
	@Override
	public List<AInterface> findByUrl(String url) {
		return dao.findByUrl(url);
	}
	
	@Override
	public List<AInterface> findByProjectUrl(Integer id, String url) {
		return dao.findByProjectUrl(id, url);
	}
	
	@Override
	public AInterface findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(AInterface aInterface) {
		if(aInterface != null){
			aInterface.setCreateTime(new Date());
			dao.create(aInterface);
			return aInterface.getId();
		}
		return null;
	}

	@Override
	public AInterface update(AInterface aInterface) {
		if(aInterface != null){
			AInterface inter = dao.findById(aInterface.getId());
			if(inter != null){
				inter.update(aInterface);
				dao.update(inter);
				return inter;
			}
		}
		return null;
	}

	@Override
	public void delete(AInterface aInterface) {
		if(aInterface != null){
			dao.delete(aInterface);
		}
	}
	
	@Override
	public void delete(Integer id) {
		if(id != null){
			dao.deleteById(id);
		}
	}
	
	@Override
	public void deleteCascade(Integer id) throws Exception{
		List<ACase> caseList = daoCase.findByInterfaceId(id);
		if(caseList != null && !caseList.isEmpty()){
			for (ACase aCase : caseList) {
				daoCase.delete(aCase);
			}
		}
		delete(id);
	}

	@Override
	public void exportApiInterface(List<AInterface> list) {
		if(list != null && !list.isEmpty()){
			for (AInterface aInterface : list) {
				if(aInterface.getProjecto() == null){
					throw new BusinessException("【第" + aInterface.getMemo() + "行】发现【所属项目】为空！");
				}else if(aInterface.getType() == null){
					throw new BusinessException("【第" + aInterface.getMemo() + "行】发现【接口类型】为空！");
				}else if(aInterface.getName() == null){
					throw new BusinessException("【第" + aInterface.getMemo() + "行】发现【接口名称】为空！");
				}else if(aInterface.getUrl() == null){
					throw new BusinessException("【第" + aInterface.getMemo() + "行】发现【接口地址】为空！");
				}else{
					if(projectDao.findById(aInterface.getProjecto().getId()) == null){
						throw new BusinessException("【第" + aInterface.getMemo() + "行】发现【所属项目ID是" + aInterface.getProjecto().getId() + "】平台项目不存在！");
					}else if(!HttpType.GET.name().equalsIgnoreCase(aInterface.getType()) && !HttpType.POST.name().equalsIgnoreCase(aInterface.getType())){
						throw new BusinessException("【第" + aInterface.getMemo() + "行】发现【接口类型不是" + HttpType.GET + "或者" + HttpType.POST + "】");
					}else if(!aInterface.getUrl().startsWith("/") || aInterface.getUrl().endsWith("/")){
						throw new BusinessException("【第" + aInterface.getMemo() + "行】发现【接口地址】格式错误！");
					}else{
						List<AInterface> interList = findByProjectUrl(aInterface.getProjecto().getId(), aInterface.getUrl());
						if(interList != null && !interList.isEmpty()){
							AInterface aInterfaceDB = interList.get(0);
							aInterfaceDB.update(aInterface);
							update(aInterfaceDB);
						}else{
							create(aInterface);
						}
					}
				}
			}
		}else{
			throw new BusinessException("文件数据为空！");
		}
	}

}

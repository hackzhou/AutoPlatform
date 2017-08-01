package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.common.bean.AInterfaceCase;
import com.auto.test.common.constant.HttpType;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.dao.IApiCaseDao;
import com.auto.test.dao.IApiInterfaceDao;
import com.auto.test.dao.IApiProjectDao;
import com.auto.test.dao.IApiVersionDao;
import com.auto.test.entity.ACase;
import com.auto.test.entity.AInterface;
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiInterfaceService;

@Service("apiInterfaceService")
public class ApiInterfaceService implements IApiInterfaceService {
	
	@Resource(name="apiInterfaceDao")
	private IApiInterfaceDao dao;
	
	@Resource(name="apiCaseDao")
	private IApiCaseDao casedDao;
	
	@Resource(name="apiProjectDao")
	private IApiProjectDao projectDao;
	
	@Resource(name="apiVersionDao")
	private IApiVersionDao versionDao;

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
		List<ACase> caseList = casedDao.findByInterfaceId(id);
		if(caseList != null && !caseList.isEmpty()){
			for (ACase aCase : caseList) {
				casedDao.delete(aCase);
			}
		}
		delete(id);
	}

	@Override
	public void exportApiInterface(List<AInterfaceCase> list) {
		if(list != null && !list.isEmpty()){
			for (AInterfaceCase aInterfaceCase : list) {
				if(aInterfaceCase.getProject() == null){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【所属项目】为空！");
				}else if(isNull(aInterfaceCase.getType())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【接口类型】为空！");
				}else if(isNull(aInterfaceCase.getName())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【接口名称】为空！");
				}else if(isNull(aInterfaceCase.getUrl())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【接口地址】为空！");
				}else if(aInterfaceCase.getVersion() == null){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【案例版本】为空！");
				}else{
					if(projectDao.findById(aInterfaceCase.getProject()) == null){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【所属项目ID是" + aInterfaceCase.getProject() + "】平台项目不存在！");
					}else if(!HttpType.GET.name().equalsIgnoreCase(aInterfaceCase.getType()) && !HttpType.POST.name().equalsIgnoreCase(aInterfaceCase.getType())){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【接口类型不是" + HttpType.GET + "或者" + HttpType.POST + "】");
					}else if(!aInterfaceCase.getUrl().startsWith("/") || aInterfaceCase.getUrl().endsWith("/")){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【接口地址】格式错误！");
					}else if(versionDao.findById(aInterfaceCase.getVersion()) == null){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】【所属版本ID是" + aInterfaceCase.getProject() + "】平台版本不存在！");
					}else{
						try {
							Integer iid = batchInterface(aInterfaceCase);
							batchCase(iid, aInterfaceCase);
						} catch (Exception e) {
							throw new BusinessException(e.getMessage());
						}
					}
				}
			}
		}else{
			throw new BusinessException("文件数据为空！");
		}
	}
	
	private Integer batchInterface(AInterfaceCase aInterfaceCase){
		List<AInterface> interList = findByProjectUrl(aInterfaceCase.getProject(), aInterfaceCase.getUrl());
		if(interList != null && !interList.isEmpty()){
			AInterface aInterfaceDB = interList.get(0);
			aInterfaceDB.update(new AInterface(aInterfaceCase));
			update(aInterfaceDB);
			return aInterfaceDB.getId();
		}else{
			return create(new AInterface(aInterfaceCase));
		}
	}
	
	private void batchCase(Integer iid, AInterfaceCase aInterfaceCase){
		Date d = null;
		List<ACase> list = casedDao.findByInterfaceIdFlag(iid, 1);
		if(list != null && !list.isEmpty()){
			for (ACase aCase : list) {
				d = aCase.getCreateTime();
				casedDao.delete(aCase);
			}
		}
		ACase c = new ACase(new AVersion(aInterfaceCase.getVersion()), new AInterface(iid), aInterfaceCase.getName(), aInterfaceCase.getBody(), null, null, null, 1, 1);
		if(d == null){
			c.setCreateTime(new Date());
		}else{
			c.setCreateTime(d);
			c.setUpdateTime(new Date());
		}
		casedDao.create(c);
	}
	
	private boolean isNull(String text){
		if(text == null || text.trim().isEmpty()){
			return true;
		}
		return false;
	}

}

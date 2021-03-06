package com.auto.test.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
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
import com.auto.test.entity.AProject;
import com.auto.test.entity.AVersion;
import com.auto.test.service.IApiInterfaceService;

@Service("apiInterfaceService")
public class ApiInterfaceService implements IApiInterfaceService {
	private static final String TOMCAT_ROOT	= ApiInterfaceService.class.getClassLoader().getResource("").getPath().substring(1).split("webapps")[0] + "webapps/ROOT/";
	private static final String DATA_IMG	= "\"_DATA_IMG\"";
	
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
	public List<AInterface> findByMinProjectId() {
		return dao.findByProjectId(projectDao.findMinCount());
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
	public List<AInterface> findByNotBacth(Integer pid, String batch) {
		return dao.findByNotBacth(pid, batch);
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
	public void deleteCascade(Integer id) {
		List<ACase> caseList = casedDao.findByInterfaceId(id);
		if(caseList != null && !caseList.isEmpty()){
			for (ACase aCase : caseList) {
				casedDao.delete(aCase);
			}
		}
		delete(id);
	}
	
	@Override
	public void deleteCascade(Integer pid, String batch) {
		List<AInterface> iList = findByNotBacth(pid, batch);
		if(iList != null && !iList.isEmpty()){
			for (AInterface aInterface : iList) {
				deleteCascade(aInterface.getId());
			}
		}
		List<ACase> cList = casedDao.findByProjectNotBatch(pid, batch);
		if(cList != null && !cList.isEmpty()){
			for (ACase aCase : cList) {
				casedDao.delete(aCase);
			}
		}
	}

	@Override
	public void exportApiInterface(List<AInterfaceCase> list) {
		if(list != null && !list.isEmpty()){
			Integer batchProject = 0;
			String batch = String.valueOf(System.currentTimeMillis());
			Map<String, ACase> linkMap = new HashMap<String, ACase>();
			Map<String, Integer> checkInterMap = new HashMap<String, Integer>();
			int linkCount = 0;
			for (AInterfaceCase aInterfaceCase : list) {
				if(isNull(aInterfaceCase.getProject())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【所属项目】为空！");
				}else if(isNull(aInterfaceCase.getType())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【接口类型】为空！");
				}else if(isNull(aInterfaceCase.getName())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【接口名称】为空！");
				}else if(isNull(aInterfaceCase.getUrl())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【接口地址】为空！");
				}else if(isNull(aInterfaceCase.getVersion())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【案例版本】为空！");
				}else if(isNull(aInterfaceCase.getResult())){
					throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现【验证结果】为空！");
				}else{
					List<AProject> pList = projectDao.findByName(aInterfaceCase.getProject());
					if(pList == null || pList.isEmpty()){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现所属项目名称是【" + aInterfaceCase.getProject() + "】不存在！");
					}else if(pList.size() != 1){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现所属项目名称是【" + aInterfaceCase.getProject() + "】存在【" + pList.size() + "】个！");
					}
					batchProject = pList.get(0).getId();
					List<AVersion> vList = versionDao.findByVersionProject(aInterfaceCase.getVersion(), batchProject);
					if(vList == null || vList.isEmpty()){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】所属版本号是【" + aInterfaceCase.getProject() + "】不存在！");
					}else if(vList.size() != 1){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】所属版本号是【" + aInterfaceCase.getProject() + "】存在【" + vList.size() + "】个！");
					}else if(!HttpType.GET.name().equalsIgnoreCase(aInterfaceCase.getType()) && !HttpType.POST.name().equalsIgnoreCase(aInterfaceCase.getType())){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现接口类型不是【" + HttpType.GET + "或者" + HttpType.POST + "】");
					}else if(!aInterfaceCase.getUrl().startsWith("/") || aInterfaceCase.getUrl().endsWith("/")){
						throw new BusinessException("【第" + aInterfaceCase.getRowNum() + "行】发现接口地址【格式】错误！");
					}else{
						try {
							Integer iid = batchInterface(aInterfaceCase, batchProject, batch, checkInterMap);
							batchCase(linkMap, aInterfaceCase, iid, vList.get(0).getId(), batch);
						} catch (Exception e) {
							throw new BusinessException(e.getMessage());
						}
					}
				}
				if(aInterfaceCase.getLink() != null && aInterfaceCase.getLink().startsWith("S")){
					linkCount++;
				}
			}
			setCaseLink(linkMap, linkCount);
			deleteCascade(batchProject, batch);
		}else{
			throw new BusinessException("文件数据为空！");
		}
	}
	
	private Integer batchInterface(AInterfaceCase aInterfaceCase, Integer pid, String batch, Map<String, Integer> checkInterMap){
		if(checkInterMap.containsKey(aInterfaceCase.getUrl())){
			return checkInterMap.get(aInterfaceCase.getUrl());
		}else{
			List<AInterface> interList = findByProjectUrl(pid, aInterfaceCase.getUrl());
			if(interList != null && !interList.isEmpty()){
				AInterface aInterfaceDB = interList.get(0);
				aInterfaceDB.update(new AInterface(aInterfaceCase, pid));
				aInterfaceDB.setBatch(batch);
				update(aInterfaceDB);
				checkInterMap.put(aInterfaceDB.getUrl(), aInterfaceDB.getId());
				return aInterfaceDB.getId();
			}else{
				AInterface aInterfaceDB = new AInterface(aInterfaceCase, pid);
				aInterfaceDB.setBatch(batch);
				Integer iid = create(aInterfaceDB);
				checkInterMap.put(aInterfaceDB.getUrl(), iid);
				return iid;
			}
		}
	}
	
	private void batchCase(Map<String, ACase> linkMap, AInterfaceCase aInterfaceCase, Integer iid, Integer vid, String batch){
		Integer login = "Yes".equalsIgnoreCase(aInterfaceCase.getLogin()) ? 1 : 0;
		String once = "Yes".equalsIgnoreCase(aInterfaceCase.getOnce()) ? "0" : null;
		String caseImg = null;
		if(aInterfaceCase.getBody() != null && aInterfaceCase.getBody().contains(DATA_IMG)){
			try {
				caseImg = TOMCAT_ROOT + "images/" +  aInterfaceCase.getUrl().replace("/", "_") + ".png";
				FileUtils.copyFile(new File(TOMCAT_ROOT + "tomcat.png"), new File(caseImg));
			} catch (Exception e) {
				throw new BusinessException("自动设置图片异常[" + e.getMessage() + "]");
			}
		}
		ACase c = new ACase(new AVersion(vid), new AInterface(iid), aInterfaceCase.getName(), aInterfaceCase.getBody(), aInterfaceCase.getResult(), aInterfaceCase.getStrategy(), aInterfaceCase.getValidate(), aInterfaceCase.getReady(), null, caseImg, once, 1, 1, login);
		c.setBatch(batch);
		List<ACase> list = casedDao.findByInterfaceIdFlagBody(iid, 1, aInterfaceCase.getBody());
		if(list != null && !list.isEmpty()){
			ACase caseo = list.get(0);
			caseo.update(c);
			casedDao.update(caseo);
			if(aInterfaceCase.getLink() != null){
				linkMap.put(aInterfaceCase.getLink(), caseo);
			}
		}else{
			c.setCreateTime(new Date());
			casedDao.create(c);
			if(aInterfaceCase.getLink() != null){
				linkMap.put(aInterfaceCase.getLink(), c);
			}
		}
	}
	
	private void setCaseLink(Map<String, ACase> linkMap, int linkCount){
		if(linkMap != null && !linkMap.isEmpty() && linkCount > 0){
			List<String> linkList = null;
			for (int i = 1; i <= linkCount; i++) {
				linkList = new ArrayList<String>();
				String links = "";
				String index = i + "-";
				ACase c = linkMap.get("S" + index + "0");
				if(c != null){
					for (String key : linkMap.keySet()) {
						if(!key.contains("S") && key.contains(index)){
							linkList.add(key);
						}
					}
					if(linkList != null && !linkList.isEmpty()){
						for (int j = 1; j <= linkList.size(); j++) {
							ACase cs = linkMap.get(index + j);
							if(cs != null){
								links += "," + cs.getId();
								cs.setRun(0);
								casedDao.update(cs);
							}
						}
					}
					if(links.startsWith(",")){
						c.setLink(links.substring(1));
						casedDao.update(c);
					}
				}
			}
		}
	}
	
	private boolean isNull(String text){
		if(text == null || text.trim().isEmpty()){
			return true;
		}
		return false;
	}

}

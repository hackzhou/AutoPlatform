package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IApiResultDetailDao;
import com.auto.test.entity.AResultDetail;
import com.auto.test.service.IApiResultDetailService;

@Service("apiResultDetailService")
public class ApiResultDetailService implements IApiResultDetailService {

	@Resource(name="apiResultDetailDao")
	private IApiResultDetailDao dao;
	
	@Override
	public List<AResultDetail> findAll() {
		return dao.findAllOrder();
	}
	
	@Override
	public List<AResultDetail> findByAllErr(Integer rid) {
		return dao.findByAllErr(rid);
	}

	@Override
	public List<AResultDetail> findByPingNo(Integer rid) {
		return dao.findByPingNo(rid);
	}

	@Override
	public List<AResultDetail> findByErr500(Integer rid) {
		return dao.findByErr500(rid);
	}
	
	@Override
	public List<AResultDetail> findByResultId(Integer rid) {
		return dao.findByResultId(rid);
	}
	
	@Override
	public List<AResultDetail> findByResultId(Integer rid, Integer filter) {
		return dao.findByResultId(rid, filter);
	}

	@Override
	public AResultDetail findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(AResultDetail aResultDetail) {
		if(aResultDetail != null){
			aResultDetail.setCreateTime(new Date());
			dao.create(aResultDetail);
			return aResultDetail.getId();
		}
		return null;
	}

	@Override
	public AResultDetail update(AResultDetail aResultDetail) {
		if(aResultDetail != null){
			AResultDetail resultDetail = dao.findById(aResultDetail.getId());
			if(resultDetail != null){
				resultDetail.update(aResultDetail);
				dao.update(resultDetail);
				return resultDetail;
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

package com.auto.test.common.service;

import com.auto.test.common.exception.BusinessException;

public interface IBaseService {

	public int getCountByParameter(String queryString, Object[] parameter) throws BusinessException;

}
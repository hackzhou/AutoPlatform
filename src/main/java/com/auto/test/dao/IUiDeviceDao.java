package com.auto.test.dao;

import java.util.List;
import com.auto.test.common.dao.IBaseDao;
import com.auto.test.entity.UDevice;

public interface IUiDeviceDao extends IBaseDao<UDevice> {
	
	List<UDevice> findByUdid(String udid);

}

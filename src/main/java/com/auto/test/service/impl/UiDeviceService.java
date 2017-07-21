package com.auto.test.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IUiDeviceDao;
import com.auto.test.entity.UDevice;
import com.auto.test.service.IUiDeviceService;

@Service("uiDeviceService")
public class UiDeviceService implements IUiDeviceService {
	
	@Resource(name="uiDeviceDao")
	private IUiDeviceDao dao;

	@Override
	public List<UDevice> findAll() {
		return dao.findAllOrder();
	}

	@Override
	public UDevice findById(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	public List<UDevice> findByUdid(String udid) {
		return dao.findByUdid(udid);
	}

	@Override
	public Integer create(UDevice uDevice) {
		if(uDevice != null){
			uDevice.setCreateTime(new Date());
			dao.create(uDevice);
			return uDevice.getId();
		}
		return null;
	}

	@Override
	public UDevice update(UDevice uDevice) {
		if(uDevice != null){
			UDevice device = dao.findById(uDevice.getId());
			if(device != null){
				device.update(uDevice);
				dao.update(device);
				return device;
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

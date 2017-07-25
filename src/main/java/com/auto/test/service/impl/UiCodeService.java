package com.auto.test.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.auto.test.dao.IUiCodeDao;
import com.auto.test.dao.IUiDeviceDao;
import com.auto.test.entity.UCode;
import com.auto.test.entity.UDevice;
import com.auto.test.service.IUiCodeService;

@Service("uiCodeService")
public class UiCodeService implements IUiCodeService {
	
	@Resource(name="uiCodeDao")
	private IUiCodeDao dao;
	
	@Resource(name="uiDeviceDao")
	private IUiDeviceDao deviceDao;


	@Override
	public List<UCode> findAll() {
		return dao.findAllOrder();
	}

	@Override
	public List<UCode> findByCls(String cls) {
		return dao.findByCls(cls);
	}

	@Override
	public UCode findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Integer create(UCode uCode) {
		if(uCode != null){
			uCode.setCreateTime(new Date());
			dao.create(uCode);
			return uCode.getId();
		}
		return null;
	}

	@Override
	public UCode update(UCode uCode) {
		if(uCode != null){
			UCode code = dao.findById(uCode.getId());
			if(code != null){
				code.update(uCode);
				dao.update(code);
				return code;
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

	@Override
	public List<UDevice> findByDevices(String devices) {
		List<UDevice> list = null;
		if(devices != null && !devices.isEmpty()){
			list = new ArrayList<UDevice>();
			for (String dev : devices.split(",")) {
				UDevice device= deviceDao.findById(Integer.parseInt(dev));
				if(device != null){
					list.add(device);
				}
			}
		}
		return list;
	}

}

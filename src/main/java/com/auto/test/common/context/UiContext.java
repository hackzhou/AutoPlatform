package com.auto.test.common.context;

import com.auto.test.core.ui.driver.IDriverExe;
import com.auto.test.core.ui.driver.impl.AndroidDriverExe;
import com.auto.test.entity.UCode;
import com.auto.test.entity.UDevice;
import com.auto.test.entity.UResultSuite;

public class UiContext {
	private String name;
	private UCode code = null;
	private UDevice device = null;
	private IDriverExe driver = null;
	private UResultSuite suite = null;
	
	private String msg = "";
	
	public UiContext() {
		super();
	}
	public UiContext(String className, UCode code, UDevice device, UResultSuite suite) {
		super();
		this.name = className;
		this.code = code;
		this.device = device;
		this.suite = suite;
		this.driver = new AndroidDriverExe(device);
	}

	public UiContext(String msg) {
		super();
		this.msg = msg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UCode getCode() {
		return code;
	}
	public void setCode(UCode code) {
		this.code = code;
	}
	public UDevice getDevice() {
		return device;
	}
	public void setDevice(UDevice device) {
		this.device = device;
	}
	public IDriverExe getDriver() {
		return driver;
	}
	public void setDriver(IDriverExe driver) {
		this.driver = driver;
	}
	public UResultSuite getSuite() {
		return suite;
	}
	public void setSuite(UResultSuite suite) {
		this.suite = suite;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}

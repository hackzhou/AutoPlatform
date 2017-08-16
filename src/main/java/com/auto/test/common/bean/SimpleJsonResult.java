package com.auto.test.common.bean;

public class SimpleJsonResult {
	private boolean success;
	private Object data;
	private String msg;

	public SimpleJsonResult() {
		super();
	}
	public SimpleJsonResult(boolean success) {
		super();
		this.success = success;
	}
	public SimpleJsonResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "SimpleJsonResult [success=" + success + ", data=" + data + ", msg=" + msg + "]";
	}
}

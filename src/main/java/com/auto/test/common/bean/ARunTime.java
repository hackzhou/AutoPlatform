package com.auto.test.common.bean;

public class ARunTime {
	private Integer time;
	
	public ARunTime() {
		super();
		time = 0;
	}

	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public void setTime(Long time) {
		this.time = time.intValue();
	}

	@Override
	public String toString() {
		return "ARunTime [time=" + time + "]";
	}
}

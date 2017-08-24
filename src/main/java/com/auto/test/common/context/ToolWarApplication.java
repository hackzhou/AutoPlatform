package com.auto.test.common.context;

import java.util.List;

public class ToolWarApplication {
	
	/**
	 * 1、保存WAR包
	 * 2、解压WAR包
	 * 3、获取SVN文件
	 * 4、配置文件对比
	 * 5、覆盖配置文件
	 * 6、压缩WAR包
	 * 7、停止线上服务
	 * 8、删除线上项目
	 * 9、上传WAR包
	 * 10、启动线上服务
	 */
	private Integer index;
	private List<String> ips;

	public ToolWarApplication() {
		super();
		index = 0;
	}
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public List<String> getIps() {
		return ips;
	}
	public void setIps(List<String> ips) {
		this.ips = ips;
	}

}

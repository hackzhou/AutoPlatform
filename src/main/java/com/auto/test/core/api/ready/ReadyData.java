package com.auto.test.core.api.ready;

import java.util.List;
import java.util.ArrayList;
import com.auto.test.common.bean.ACaseReady;
import com.auto.test.core.api.ready.impl.ReadyDataImpl;

public class ReadyData {
	private static List<ACaseReady> list = null;
	static{
		list = new ArrayList<ACaseReady>();
		list.add(new ACaseReady(1, "A", "我的测试1"));
		list.add(new ACaseReady(2, "B", "我的测试2"));
		list.add(new ACaseReady(3, "C", "我的测试3"));
	}
	
	public ReadyData(){
		super();
	}
	
	public static void exe(int index){
		switch (index) {
			case 1:
				new ReadyDataImpl().A();
				break;
			case 2:
				new ReadyDataImpl().B();
				break;
			case 3:
				new ReadyDataImpl().C();
				break;
			default:
				break;
		}
	}

	public static List<ACaseReady> getList() {
		return list;
	}
	public static void setList(List<ACaseReady> list) {
		ReadyData.list = list;
	}
}

package com.auto.test.core.api.ready;

import java.util.List;
import java.util.ArrayList;
import com.auto.test.common.bean.ACaseReady;
import com.auto.test.core.api.ready.impl.ReadyDataImpl;

public class ReadyData {
	private static List<ACaseReady> list = null;
	static{
		list = new ArrayList<ACaseReady>();
		list.add(new ACaseReady(1, "A", "用户中心-更新我的金叶子为一亿"));
		list.add(new ACaseReady(2, "B", "用户中心-删除个人关联信息(保存本站/修改昵称)"));
		list.add(new ACaseReady(3, "C", "商城-更新背包保存中奖地址状态"));
		list.add(new ACaseReady(4, "D", "商城-更新背包领取福利状态"));
	}
	
	public ReadyData(){
		super();
	}
	
	public static void exe(int index, int uid, String body){
		switch (index) {
			case 1:
				new ReadyDataImpl().A(uid, body);
				break;
			case 2:
				new ReadyDataImpl().B(uid, body);
				break;
			case 3:
				new ReadyDataImpl().C(uid, body);
				break;
			case 4:
				new ReadyDataImpl().D(uid, body);
				break;
			default:
				break;
		}
	}
	
	public static int getUserID(String loginname) {
		return new ReadyDataImpl().getUserID(loginname);
	}
	
	public static List<ACaseReady> getList() {
		return list;
	}
	public static void setList(List<ACaseReady> list) {
		ReadyData.list = list;
	}
}

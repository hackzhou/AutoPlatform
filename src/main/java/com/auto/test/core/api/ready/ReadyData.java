package com.auto.test.core.api.ready;

import java.util.List;
import java.util.ArrayList;
import com.auto.test.common.bean.ACaseReady;
import com.auto.test.core.api.ready.impl.ReadyDataImpl;

public class ReadyData {
	private static List<ACaseReady> list = null;
	static{
		list = new ArrayList<ACaseReady>();
		list.add(new ACaseReady(101, "AA", "用户中心-更新我的金叶子为一亿"));
		list.add(new ACaseReady(102, "AB", "用户中心-清除验证码发送频繁"));
		list.add(new ACaseReady(103, "AC", "用户中心-删除个人关联信息(保存本站/修改昵称)"));
		list.add(new ACaseReady(201, "BA", "商城-更新背包保存中奖地址状态"));
		list.add(new ACaseReady(202, "BB", "商城-更新背包领取福利状态"));
	}
	
	public ReadyData(){
		super();
	}
	
	public static void exe(int index, int uid, String body){
		switch (index) {
			case 101:
				new ReadyDataImpl().AA(uid, body);
				break;
			case 102:
				new ReadyDataImpl().AB(uid, body);
				break;
			case 103:
				new ReadyDataImpl().AC(uid, body);
				break;
			case 201:
				new ReadyDataImpl().BA(uid, body);
				break;
			case 202:
				new ReadyDataImpl().BB(uid, body);
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

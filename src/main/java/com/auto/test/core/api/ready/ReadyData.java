package com.auto.test.core.api.ready;

import java.util.List;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.auto.test.common.bean.ACaseReady;
import com.auto.test.common.constant.Const;
import com.auto.test.core.api.ready.impl.ReadyDataImpl;

public class ReadyData {
	private static List<ACaseReady> list	= null;
	static{
		list = new ArrayList<ACaseReady>();
		list.add(new ACaseReady(101, "AA", "用户中心-更新我的金叶子为一亿"));
		list.add(new ACaseReady(102, "AB", "用户中心-清除验证码发送频繁"));
		list.add(new ACaseReady(103, "AC", "用户中心-删除个人关联信息(保存本站/修改昵称)"));
		list.add(new ACaseReady(201, "BA", "商城-更新背包保存中奖地址状态"));
		list.add(new ACaseReady(202, "BB", "商城-更新背包领取福利状态"));
		list.add(new ACaseReady(301, "CA", "App-删除分享圈点赞"));
		list.add(new ACaseReady(302, "CB", "App-删除分享圈评论点赞"));
		list.add(new ACaseReady(303, "CC", "App-更新咨询收藏状态"));
		list.add(new ACaseReady(304, "CD", "App-更新咨询点赞状态"));
		list.add(new ACaseReady(305, "CE", "App-删除每日签到"));
		list.add(new ACaseReady(306, "CF", "App-消息中心-更新消息状态"));
		list.add(new ACaseReady(307, "CG", "App-消息中心-更新领取奖励状态"));
		list.add(new ACaseReady(308, "CH", "App-删除新人礼包"));
		list.add(new ACaseReady(309, "CI", "App-删除俱乐部"));
		list.add(new ACaseReady(310, "CJ", "App-清除橡皮擦"));
		list.add(new ACaseReady(401, "DA", "Wap-"));
		list.add(new ACaseReady(501, "EA", "运营活动-清除付费转盘次数"));
	}
	
	public ReadyData(){
		super();
	}
	
	public static void exe(int index, int uid, String loginname, String body){
		if(getTestPlatform()){
			switch (index) {
			case 101:
				new ReadyDataImpl().AA(uid);
				break;
			case 102:
				new ReadyDataImpl().AB(loginname);
				break;
			case 103:
				new ReadyDataImpl().AC(uid);
				break;
			case 201:
				new ReadyDataImpl().BA(body);
				break;
			case 202:
				new ReadyDataImpl().BB(body);
				break;
			case 301:
				new ReadyDataImpl().CA(uid);
				break;
			case 302:
				new ReadyDataImpl().CB(uid);
				break;
			case 303:
				new ReadyDataImpl().CC(body);
				break;
			case 304:
				new ReadyDataImpl().CD(body);
				break;
			case 305:
				new ReadyDataImpl().CE(uid);
				break;
			case 306:
				new ReadyDataImpl().CF(body);
				break;
			case 307:
				new ReadyDataImpl().CG(body);
				break;
			case 308:
				new ReadyDataImpl().CH(uid);
				break;
			case 309:
				new ReadyDataImpl().CI(uid);
				break;
			case 310:
				new ReadyDataImpl().CJ();
				break;
			case 501:
				new ReadyDataImpl().EA(uid);
				break;
			default:
				break;
			}
		}
	}
	
	public static int getUserID(String loginname) {
		return new ReadyDataImpl().getUserID(loginname);
	}
	
	private static boolean getTestPlatform(){
		try {
			InetAddress ip = InetAddress.getByName("uic-api.beeplay123.com");
			if(Const.IP_SERVER_TEST.equals(ip.getHostAddress())){
				return true;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<ACaseReady> getList() {
		return list;
	}
	public static void setList(List<ACaseReady> list) {
		ReadyData.list = list;
	}
}

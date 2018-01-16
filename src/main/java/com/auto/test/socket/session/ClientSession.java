package com.auto.test.socket.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.auto.test.socket.data.IMessageDef;
import com.auto.test.socket.data.ProtocolData;
import com.neovisionaries.ws.client.WebSocket;

public class ClientSession implements Runnable{
	private int id;
	private String token;
	private String channelId;
	private WebSocket ws;
	private boolean enter;
	private boolean isEnter;
	
	public ClientSession(int id, String channelId, String token, WebSocket ws){
		this.id = id;
		this.channelId = channelId;
		this.token = token;
		this.ws = ws;
	}
	
	@Override
	public void run() {
		try {
			if (!enter && !isEnter) {
				isEnter = true;
				Map<String, Object> params = new HashMap<>();
				params.put("token", token);
				params.put("channelId", channelId);
				String content = new ProtocolData(2, params).toJson();
				ws.sendText(content); //登录
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dealMessage(ProtocolData pd){
		try {
			switch (pd.getProtocolId()) {
			case IMessageDef.LOGIN:
				Object obj = pd.getData().get("result");
				if(obj != null){
					double result = Double.parseDouble(obj.toString());
					if (1 == result) {
						this.enter = true;
					}
				}
				sendEnterRoomReq();
				break;
			case IMessageDef.ENTER_ROOM:
				break;
			case IMessageDef.ENTER_ROOM_PUSH:
				break;
			case IMessageDef.GAME_BEGIN_PUSH:
				sendQiangZhuangReq();
				break;
			case IMessageDef.QIANG_ZHUANG:
				break;
			case IMessageDef.QIANG_ZHUANG_PUSH:
				break;
			case IMessageDef.QIANG_ZHUANG_OVER_PUSH:
				sendXianJiaJiaBeiReq();
				break;
			case IMessageDef.XIAN_JIA_JIA_BEI:
				break;
			case IMessageDef.XIAN_JIA_JIA_BEI_PUSH:
				break;
			case IMessageDef.XIAN_JIA_JIA_BEI_OVER_PUSH:
				sendZuPaiReq();
				break;
			case IMessageDef.ZU_PAI:
				break;
			case IMessageDef.ZU_PAI_PUSH:
				break;
			case IMessageDef.GAME_OVER_PUSH:
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendEnterRoomReq() {
		Map<String, Object> params = new HashMap<>();
		params.put("roomType", new Random().nextInt(3) + 1);
		params.put("token", token);
		params.put("channelId", channelId);
		String content = new ProtocolData(IMessageDef.ENTER_ROOM, params).toJson();
		ws.sendText(content);
	}
	
	private void sendQiangZhuangReq() {
		Map<String, Object> params = new HashMap<>();
		params.put("multiples", 0);
		String content = new ProtocolData(IMessageDef.QIANG_ZHUANG, params).toJson();
		ws.sendText(content);
	}
	
	private void sendXianJiaJiaBeiReq() {
		Map<String, Object> params = new HashMap<>();
		params.put("multiples", 5);
		String content = new ProtocolData(IMessageDef.XIAN_JIA_JIA_BEI, params).toJson();
		ws.sendText(content);
	}
	
	private void sendZuPaiReq() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public WebSocket getWs() {
		return ws;
	}
	public void setWs(WebSocket ws) {
		this.ws = ws;
	}

}

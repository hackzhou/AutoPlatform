package com.auto.test.socket.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.auto.test.socket.data.ProtocolData;
import com.neovisionaries.ws.client.WebSocket;

public class ClientSession implements Runnable{
	private int channelId;
	private String token;
	private WebSocket ws;
	private boolean enter;
	private List<List<String>> fishs;
	private boolean isEnter;
	
	public ClientSession(int channelId, String token, WebSocket ws){
		this.channelId = channelId;
		this.token = token;
		this.ws = ws;
	}
	
	@Override
	public void run() {
		try {
			Map<String, Object> params = new HashMap<>();

			if (!enter && !isEnter) {
				isEnter = true;
				params.put("roomConfigId", 1);
				params.put("token", token);
				String content = new ProtocolData(2,params).toJson();
				ws.sendText(content);
			}else if(enter){
				params.put("score", 1);
				String content = new ProtocolData(15,params).toJson();
				ws.sendText(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void dealMessage(ProtocolData pd){
		try {
			if(pd.getProtocolId() == 2){
				long roomId = ((Double) pd.getData().get("roomId")).longValue();
				
				if(roomId!=0){
					this.enter = true;
					this.fishs = (List<List<String>>) pd.getData().get("fish");
				}
			}else if (pd.getProtocolId() == 3) {
				this.fishs = (List<List<String>>) pd.getData().get("fish");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String randomFishId(){
		if (this.fishs!=null) {
			List<String> fish = this.fishs.get(new Random().nextInt(this.fishs.size()));
			return fish.get(3);
		}
		return "0";
	}

	public boolean isEnter() {
		return enter;
	}
	public void setEnter(boolean enter) {
		this.enter = enter;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public WebSocket getWs() {
		return ws;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

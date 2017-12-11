package com.auto.test.socket.session;

import java.util.HashMap;
import java.util.Map;

public class SessionRegistry {
	private static Map<Integer, ClientSession> channelSessionMap = new HashMap<Integer, ClientSession>();
	
	public static void addSession(ClientSession session){
		channelSessionMap.put(session.getChannelId(), session);
	}
	
	public static ClientSession getSessionById(int channelId){
		return channelSessionMap.get(channelId);
	}
	
	public static int getOnlineCount(){
		return  channelSessionMap.size();
	}
	
	public static void remove(int channelId){
		channelSessionMap.remove(channelId);
	}
}

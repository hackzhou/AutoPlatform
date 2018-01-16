package com.auto.test.socket.session;

import java.util.HashMap;
import java.util.Map;

public class SessionRegistry {
	private static Map<Integer, ClientSession> sessionMap = new HashMap<Integer, ClientSession>();
	
	public static void addSession(ClientSession session){
		sessionMap.put(session.getId(), session);
	}
	
	public static ClientSession getSessionById(int id){
		return sessionMap.get(id);
	}
	
	public static int getOnlineCount(){
		return sessionMap.size();
	}
	
	public static void remove(int id){
		sessionMap.remove(id);
	}
}

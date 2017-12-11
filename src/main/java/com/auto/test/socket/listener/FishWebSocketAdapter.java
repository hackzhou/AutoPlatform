package com.auto.test.socket.listener;

import java.util.List;
import java.util.Map;
import com.auto.test.socket.data.ProtocolData;
import com.auto.test.socket.pool.ClientThreadPool;
import com.auto.test.socket.session.ClientSession;
import com.auto.test.socket.session.SessionRegistry;
import com.auto.test.utils.JSONUtil;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;

public class FishWebSocketAdapter extends WebSocketAdapter{
	private String token = "";
	
	public FishWebSocketAdapter(String token){
		super();
		this.token = token;
	}
	
	@Override
	public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
		int id = websocket.getSocket().hashCode();
		System.out.println("当前连接的token:" + token);
		ClientSession cs = new ClientSession(id, token, websocket);
		SessionRegistry.addSession(cs);
		ClientThreadPool.exec(cs);
	}
	
	@Override
	public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) {
		int id = websocket.getSocket().hashCode();
		SessionRegistry.remove(id);
	}
	
	@Override
    public void onError(WebSocket websocket, WebSocketException cause) {
		int id = websocket.getSocket().hashCode();
		SessionRegistry.remove(id);
    }
	
	@Override
	public void onTextMessage(WebSocket websocket, String text) {
		int id = websocket.getSocket().hashCode();
		ProtocolData pd = JSONUtil.json2Bean(ProtocolData.class, text);
		SessionRegistry.getSessionById(id).dealMessage(pd);
	}
}

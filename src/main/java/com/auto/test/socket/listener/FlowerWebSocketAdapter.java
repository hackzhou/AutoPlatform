package com.auto.test.socket.listener;

import java.util.List;
import java.util.Map;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;

public class FlowerWebSocketAdapter extends WebSocketAdapter{
	private String token = "";
	
	public FlowerWebSocketAdapter(String token){
		super();
		this.token = token;
	}
	
	@Override
	public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
		System.out.println(token);
	}
	
	@Override
	public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) {
		
	}
	
	@Override
    public void onError(WebSocket websocket, WebSocketException cause) {
		
    }
	
	@Override
	public void onTextMessage(WebSocket websocket, String text) {
		
	}
}

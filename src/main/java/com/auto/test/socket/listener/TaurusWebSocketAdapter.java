package com.auto.test.socket.listener;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.auto.test.socket.data.ProtocolData;
import com.auto.test.socket.pool.ClientThreadPool;
import com.auto.test.socket.session.ClientSession;
import com.auto.test.socket.session.SessionRegistry;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;

public class TaurusWebSocketAdapter extends WebSocketAdapter{
	private static Logger logger = LoggerFactory.getLogger(TaurusWebSocketAdapter.class);
	private String token = "";
	private String channelId = "";
	
	public TaurusWebSocketAdapter(String channelId, String token){
		super();
		this.token = token;
		this.channelId = channelId;
	}
	
	@Override
	public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
		int id = websocket.getSocket().hashCode();
		logger.info("连接建立[Token=" + token + ",SessionID=" + id + "]");
		ClientSession cs = new ClientSession(id, channelId, token, websocket);
		SessionRegistry.addSession(cs);
		ClientThreadPool.exec(cs);
	}
	
	@Override
	public void onTextMessage(WebSocket websocket, String text) {
		logger.info("文本消息[Text=" + text + "]");
		int id = websocket.getSocket().hashCode();
		ProtocolData pd = JSON.parseObject(text, ProtocolData.class);
		SessionRegistry.getSessionById(id).dealMessage(pd);
	}
	
	@Override
	public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) {
		logger.info("消息发送失败");
		int id = websocket.getSocket().hashCode();
		SessionRegistry.remove(id);
	}
	
	@Override
	public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {
		logger.info("处理回调失败");
		int id = websocket.getSocket().hashCode();
		SessionRegistry.remove(id);
	}
	
	@Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception{
		logger.info("连接关闭");
		int id = websocket.getSocket().hashCode();
		SessionRegistry.remove(id);
    }
	
}

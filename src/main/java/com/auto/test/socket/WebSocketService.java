package com.auto.test.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.exception.BusinessException;
import com.auto.test.socket.listener.FishWebSocketAdapter;
import com.auto.test.socket.listener.FlowerWebSocketAdapter;
import com.auto.test.socket.listener.TaurusWebSocketAdapter;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

public class WebSocketService {
	private static Logger logger = LoggerFactory.getLogger(WebSocketService.class);
	private static final String FISH_GAME	= "fish";
	private static final String FISH_WS		= "ws://10.33.85.174:7200/api_fish/webSocketServer";
	private static final String FLOWER_GAME	= "flower";
	private static final String FLOWER_WS	= "ws://10.33.85.174:7200/api_flower/webSocketServer";
	private static final String TAURUS_GAME	= "taurus";
	private static final String TAURUS_WS	= "ws://game-ws-api.beeplay123.com/taurus/webSocketServer";
	private static List<WebSocket> wsList = null;
	
	public static void main(String[] args) {
		try {
			WebSocket ws = new WebSocketFactory().createSocket(TAURUS_WS);
			ws.addListener(new TaurusWebSocketAdapter("100006", "ac30ec9f99a545b2b8ec3374b1d9384e"));
			ws.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getInstance(String type, String channel, List<String> tokens){
		disconnectAll();
		wsList = new ArrayList<WebSocket>();
		try {
			if(FISH_GAME.equals(type)){
				for (String token : tokens) {
					WebSocket ws = new WebSocketFactory().createSocket(FISH_WS);
					ws.addListener(new FishWebSocketAdapter(token));
					ws.connect();
					wsList.add(ws);
				}
			}else if(FLOWER_GAME.equals(type)){
				for (String token : tokens) {
					WebSocket ws = new WebSocketFactory().createSocket(FLOWER_WS);
					ws.addListener(new FlowerWebSocketAdapter(token));
					ws.connect();
					wsList.add(ws);
				}
			}else if(TAURUS_GAME.equals(type)){
				for (String token : tokens) {
					WebSocket ws = new WebSocketFactory().createSocket(TAURUS_WS);
					ws.addListener(new TaurusWebSocketAdapter(channel, token));
					ws.connect();
					wsList.add(ws);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void disconnectAll(){
		if(wsList != null && !wsList.isEmpty()){
			for (WebSocket ws : wsList) {
				ws.disconnect();
			}
			wsList = null;
		}
	}
	
	public static String[] getPressureProjects(){
		return new String[]{TAURUS_GAME};
	}
	
	public void checkProjectUrl(String type){
		if(FISH_GAME.equals(type)){
			checkHostPort(FISH_WS, getIp(FISH_WS), getPort(FISH_WS));
			logger.info("游戏[炸金花]连接成功！");
		}else if(FLOWER_GAME.equals(type)){
			checkHostPort(FLOWER_WS, getIp(FLOWER_WS), getPort(FLOWER_WS));
			logger.info("游戏[捕鱼]连接成功！");
		}else if(TAURUS_GAME.equals(type)){
			checkHostPort(TAURUS_WS, getIp(TAURUS_WS), getPort(TAURUS_WS));
			logger.info("游戏[牛牛]连接成功！");
		}else{
			throw new BusinessException("项目不存在[type=" + type + "]");
		}
	}
	
	private String getIp(String url){
		String url2 = url.replace("ws://", "");
		if(url2.contains(":")){
			return url2.split(":")[0];
		}else{
			return url2.split("/")[0];
		}
	}
	
	private int getPort(String url){
		String url2 = url.replace("ws://", "");
		if(url2.contains(":")){
			return Integer.parseInt(url2.split(":")[1].split("/")[0]);
		}else{
			return 80;
		}
	}
	
	private void checkHostPort(String ws, String host, int port){
		Socket socket = new Socket();
        try {
        	socket.connect(new InetSocketAddress(host, port), 5000);
		} catch (Exception e) {
			throw new BusinessException("服务未开启[ip=" + host + "][port=" + port + "][" + ws + "]");
        } finally {
        	try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

	public static List<WebSocket> getWsList() {
		return wsList;
	}
}

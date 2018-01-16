package com.auto.test.socket.data;

public interface IMessageDef {
	/** 错误信息 */
	public static final int ERROR_INFO = 0;
	/** 心跳 */
	public static final int HEART_BEAT = 1;
	/**
	 * 登录
	 */
	public static final int LOGIN = 2;
	/**
	 * 踢人下线
	 */
	public static final int KICK = 3;

	/*********** 10000-11000 ******************/
	/************ 牌局玩法 ************************/
	/**
	 * 进入房间
	 */
	public static final int ENTER_ROOM = 10000;
	/**
	 * 进入房间推送
	 */
	public static final int ENTER_ROOM_PUSH = 10001;
	/**
	 * 游戏开始推送
	 */
	public static final int GAME_BEGIN_PUSH = 10002;
	/**
	 * 抢庄
	 */
	public static final int QIANG_ZHUANG = 10003;
	/**
	 * 抢庄推送
	 */
	public static final int QIANG_ZHUANG_PUSH = 10004;
	/**
	 * 抢庄结束推送
	 */
	public static final int QIANG_ZHUANG_OVER_PUSH = 10005;
	/**
	 * 闲家加倍
	 */
	public static final int XIAN_JIA_JIA_BEI = 10006;
	/**
	 * 闲家加倍推送
	 */
	public static final int XIAN_JIA_JIA_BEI_PUSH = 10007;
	/**
	 * 闲家加倍结束推送
	 */
	public static final int XIAN_JIA_JIA_BEI_OVER_PUSH = 10008;
	/**
	 * 组牌
	 */
	public static final int ZU_PAI = 10009;
	/**
	 * 组牌推送
	 */
	public static final int ZU_PAI_PUSH = 10010;
	/**
	 * 游戏结束推送
	 */
	public static final int GAME_OVER_PUSH = 10011;
}

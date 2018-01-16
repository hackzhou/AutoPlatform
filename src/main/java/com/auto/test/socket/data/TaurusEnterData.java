package com.auto.test.socket.data;

public class TaurusEnterData {
	private String amount;
	private String headImg;
	private String pos;
	private String status;
	private String userId;
	private String userName;
	
	public TaurusEnterData() {
		super();
	}

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "TaurusEnterData [amount=" + amount + ", headImg=" + headImg + ", pos=" + pos + ", status=" + status
				+ ", userId=" + userId + ", userName=" + userName + "]";
	}

}

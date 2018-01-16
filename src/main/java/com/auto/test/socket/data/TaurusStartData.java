package com.auto.test.socket.data;

public class TaurusStartData {
	private String cards;
	private String robBankOverTime;
	
	public TaurusStartData() {
		super();
	}

	public String getCards() {
		return cards;
	}
	public void setCards(String cards) {
		this.cards = cards;
	}
	public String getRobBankOverTime() {
		return robBankOverTime;
	}
	public void setRobBankOverTime(String robBankOverTime) {
		this.robBankOverTime = robBankOverTime;
	}

	@Override
	public String toString() {
		return "TaurusStartData [cards=" + cards + ", robBankOverTime=" + robBankOverTime + "]";
	}

}

package com.auto.test.core.ui.driver;

public interface IDriverExe {
	
	public boolean isElementPresent(String type, String locator);

	public void keyboardClick(String keyName);
	
	public void click(String type, String locator);
	
	public void foundClick(String type, String locator);
	
	public void tap(String type, String locator);
	
	public void tap(String type, String locator, Integer time);
	
	public void tap(Integer x, Integer y);

	public void tap(Integer x, Integer y, Integer time);
	
	public void swap(Integer startX, Integer startY, Integer endX, Integer endY, Integer duration);
	
	public void clearText(String type, String locator);
	
	public void sendKey(String type, String locator, String text);
	
	public void sleep(Integer seconds);
	
	public boolean existResult(String result);

	public boolean isValue(String type, String locator, String value);
	
	public void capturePic(String picPath);
	
	public void resetApp();
	
	public void destory();
	
}

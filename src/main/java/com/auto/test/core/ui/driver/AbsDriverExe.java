package com.auto.test.core.ui.driver;

public abstract class AbsDriverExe implements IDriverExe {

	@Override
	public boolean isElementPresent(String type, String locator) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void keyboardClick(String keyName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void click(String type, String locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void foundClick(String type, String locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tap(String type, String locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tap(String type, String locator, Integer time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tap(Integer x, Integer y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tap(Integer x, Integer y, Integer time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void swap(Integer startX, Integer startY, Integer endX, Integer endY, Integer duration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearText(String type, String locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendKey(String type, String locator, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sleep(Integer seconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existResult(String result) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValue(String type, String locator, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void capturePic(String picPath) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetApp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub

	}

}

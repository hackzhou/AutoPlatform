package com.auto.test.core.ui.driver;

public abstract class AbsDriverExe implements IDriverExe {
	
	@Override
	public boolean isElementPresent(String type, String locator) {
		System.out.println("[AbsDriverExe]-isElementPresent");
		return false;
	}
	
	@Override
	public void init() {
		System.out.println("[AbsDriverExe]-init");
	}

	@Override
	public void keyboardClick(String keyName) {
		System.out.println("[AbsDriverExe]-keyboardClick");
	}

	@Override
	public void click(String type, String locator) {
		System.out.println("[AbsDriverExe]-click");
	}

	@Override
	public void foundClick(String type, String locator) {
		System.out.println("[AbsDriverExe]-foundClick");
	}

	@Override
	public void tap(String type, String locator) {
		System.out.println("[AbsDriverExe]-tap");
	}

	@Override
	public void tap(String type, String locator, Integer time) {
		System.out.println("[AbsDriverExe]-tap");
	}

	@Override
	public void tap(Integer x, Integer y) {
		System.out.println("[AbsDriverExe]-tap");
	}

	@Override
	public void tap(Integer x, Integer y, Integer time) {
		System.out.println("[AbsDriverExe]-tap");
	}

	@Override
	public void swap(Integer startX, Integer startY, Integer endX, Integer endY, Integer duration) {
		System.out.println("[AbsDriverExe]-swap");
	}

	@Override
	public void clearText(String type, String locator) {
		System.out.println("[AbsDriverExe]-clearText");
	}

	@Override
	public void sendKey(String type, String locator, String text) {
		System.out.println("[AbsDriverExe]-sendKey");
	}

	@Override
	public void sleep(Integer seconds) {
		System.out.println("[AbsDriverExe]-sleep");
	}

	@Override
	public boolean existResult(String result) {
		System.out.println("[AbsDriverExe]-existResult");
		return false;
	}

	@Override
	public boolean isValue(String type, String locator, String value) {
		System.out.println("[AbsDriverExe]-isValue");
		return false;
	}

	@Override
	public void capturePic(String picPath) {
		System.out.println("[AbsDriverExe]-capturePic");
	}

	@Override
	public void resetApp() {
		System.out.println("[AbsDriverExe]-resetApp");
	}

	@Override
	public void destory() {
		System.out.println("[AbsDriverExe]-destory");
	}

}

package com.auto.test.core.ui.driver.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.constant.Const;
import com.auto.test.core.ui.driver.IDriverExe;
import com.auto.test.entity.UDevice;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidDriverExe implements IDriverExe {
	private static final Logger logger = LoggerFactory.getLogger(AndroidDriverExe.class);
	private DesiredCapabilities capabilities = null;
	private UDevice device = null;
	private AndroidDriver<?> driver = null;

	public AndroidDriverExe(UDevice device){
		super();
		this.device = device;
		init();
	}
	
	@SuppressWarnings("rawtypes")
	public void init() {
		capabilities = new DesiredCapabilities();
		try {
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, device.getPlatformName());
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
			capabilities.setCapability(MobileCapabilityType.UDID, device.getUdid());
			capabilities.setCapability(MobileCapabilityType.APP, device.getApp());
			capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability("autoWebviewTimeout", 5 * 1000);
			capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60 * 2);
			this.driver = new AndroidDriver(new URL(device.getServerUrl()), capabilities);
			this.driver.setLogLevel(Level.INFO);
			this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isElementPresent(String type, String locator){
		return (new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
		        	List<?> list = null;
		        	if("id".equalsIgnoreCase(type)){
		        		list = driver.findElements(By.id(locator));
		    		} else if("name".equalsIgnoreCase(type)){
		    			list = driver.findElements(By.name(locator));
		    		} else if("xpath".equalsIgnoreCase(type)){
		    			list = driver.findElements(By.xpath(locator));
		    		}
		        	if(list != null && !list.isEmpty()){
		        		return true;
		        	}
		        	return false;
		        } catch(NoSuchElementException e) {
		        	return false;
		        }
			}
			@Override
			public String toString() {
				return "控件未找到! [" + type + "]Locator:" + locator;
			}
		}));
	}
	
	@Override
	public void keyboardClick(String keyName){
		if(Const.KEYBOARD_RETURN.equals(keyName)){
			driver.pressKeyCode(4);
		}else if(Const.KEYBOARD_ENTER.equals(keyName)){
			driver.pressKeyCode(66);
		}
	}

	@Override
	public void click(String type, String locator) {
		if(isElementPresent(type, locator)){
			if("id".equalsIgnoreCase(type)){
				this.driver.findElement(By.id(locator)).click();
			} else if("xpath".equalsIgnoreCase(type)){
				this.driver.findElement(By.xpath(locator)).click();
			}
		}
	}
	
	@Override
	public void foundClick(String type, String locator){
		if(isElementExist(type, locator)){
			if("id".equalsIgnoreCase(type)){
				this.driver.findElement(By.id(locator)).click();
			} else if("xpath".equalsIgnoreCase(type)){
				this.driver.findElement(By.xpath(locator)).click();
			}
		}
	}
	
	@Override
	public void tap(String type, String locator) {
		this.tap(type, locator, 100);
	}

	@Override
	public void tap(String type, String locator, Integer time) {
		if(isElementPresent(type, locator)){
			if("id".equalsIgnoreCase(type)){
				this.driver.tap(1, this.driver.findElement(By.id(locator)), time);
			} else if("xpath".equalsIgnoreCase(type)){
				this.driver.tap(1, this.driver.findElement(By.xpath(locator)), time);
			}
		}
	}
	
	@Override
	public void tap(Integer x, Integer y) {
		this.tap(x, y, 100);
	}
	
	@Override
	public void tap(Integer x, Integer y, Integer time) {
		this.driver.tap(1, x, y, time);
	}
	
	@Override
	public void swap(Integer startX, Integer startY, Integer endX, Integer endY, Integer duration) {
		this.driver.swipe(startX, startY, endX, endY, duration);
	}

	@Override
	public void clearText(String type, String locator) {
		if(isElementPresent(type, locator)){
			this.driver.pressKeyCode(123);
			for (int i = 0; i < 15; i++) {
				this.driver.pressKeyCode(67);
			}
		}
	}
	
	@Override
	public void sendKey(String type, String locator, String text) {
		if(isElementPresent(type, locator)){
			WebElement e = null;
			if ("id".equalsIgnoreCase(type)) {
				e = this.driver.findElement(By.id(locator));
			} else if ("xpath".equalsIgnoreCase(type)) {
				e = this.driver.findElement(By.xpath(locator));
			}
			if(e != null){
				e.click();
				String value = e.getText();
				if(value != null && !value.isEmpty()){
					driver.pressKeyCode(123);
					int len = value.length();
					if (len >= 15) {
						len = 15;
					}
					for (int i = 0; i < len; i++) {
						driver.pressKeyCode(67);
					}
				}
				e.sendKeys(text);
			}
		}
	}
	
	@Override
	public void sleep(Integer seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean existResult(String result) {
		return this.driver.getPageSource().indexOf(result) != -1;
	}
	
	@Override
	public boolean isValue(String type, String locator, String value) {
		if(isElementPresent(type, locator)){
			WebElement e = null;
			if("id".equalsIgnoreCase(type)){
				e = this.driver.findElement(By.id(locator));
			} else if("xpath".equalsIgnoreCase(type)){
				e = this.driver.findElement(By.xpath(locator));
			}
			if (e != null && (value.equals(e.getText()) || value.equals(e.getAttribute("name")))) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void capturePic(String picPath){
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(picPath));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void resetApp() {
		this.driver.closeApp();
		this.driver.launchApp();
	}

	@Override
	public void destory() {
		if (this.driver != null) {
			this.driver.quit();
		}
	}

	private boolean isElementExist(String type, String locator){
		int index = 0;
		while (index < 1) {
			List<?> list = null;
			if("id".equalsIgnoreCase(type)){
        		list = driver.findElements(By.id(locator));
    		} else if("name".equalsIgnoreCase(type)){
    			list = driver.findElements(By.name(locator));
    		} else if("xpath".equalsIgnoreCase(type)){
    			list = driver.findElements(By.xpath(locator));
    		}
        	if(list != null && !list.isEmpty()){
        		return true;
        	}
			index++;
		}
		return false;
	}

}

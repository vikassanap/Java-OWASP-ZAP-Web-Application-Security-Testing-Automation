package com.zap.driver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManager {

	public WebDriver getDriver(String proxyLocation, String driverType) {
		System.out.println("inside getDriver function");
		WebDriver driver;
		Proxy proxy = new Proxy();
		proxy.setAutodetect(false);
		proxy.setProxyType(ProxyType.MANUAL);
		proxy.setHttpProxy(proxyLocation);
		System.out.println("Proxy running at " + proxyLocation);
		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability(CapabilityType.PROXY, proxy);
		if (driverType == "Firefox") {
			driver = new FirefoxDriver(ds);

		} else {
			driver = new FirefoxDriver(ds);
		}
		return driver;
	}

	public void destoryDriver(WebDriver driver) {
		System.out.println("inside deleteDriver function");
		driver.close();
	}

}

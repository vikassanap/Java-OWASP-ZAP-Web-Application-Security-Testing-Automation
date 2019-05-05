package com.zap.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zap.driver.DriverManager;

public class Test {
	public void TestCase()
	{
		System.out.println("inside testCase function");
		DriverManager driverManager=new DriverManager();
		WebDriver driver=driverManager.getDriver("localhost:8080", "Firefox");
		driver.get("http://localhost:9090");
		driver.findElement(By.id("username")).sendKeys("alice");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.name("_action_login")).click();
		driverManager.destoryDriver(driver);
	}

}

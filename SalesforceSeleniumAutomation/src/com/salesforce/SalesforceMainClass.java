package com.salesforce;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SalesforceMainClass
{
	public static void main(String[] args) throws InterruptedException, IOException {
		

	System.setProperty("webdriver.chrome.driver",
			"C:\\Users\\acer2\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	WebDriver driver = new ChromeDriver(options);	
	driver.manage().window().maximize();
	AutomateSalesforce Sales = new AutomateSalesforce();
	Sales.salesforceLogin(driver);
	//Sales.doEnrollment(driver);
	Sales.createCase(driver);
	
	
}
}

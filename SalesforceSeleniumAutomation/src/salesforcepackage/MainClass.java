package salesforcepackage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainClass {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\acer2\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("start-maximized");
		WebDriver driver = new ChromeDriver(options);	
		driver.manage().window().maximize();
	    //Thread.sleep(30);
	    //driver.wait(5);
	    //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	SalesforceSeleniumAutomation salesforce = new SalesforceSeleniumAutomation();
	salesforce.salesforceLogin(driver);
	//salesforce.appLauncher(driver);
	salesforce.createLead(driver);
	salesforce.createOpportunity(driver);
	//salesforce.printErrorMessage(driver);
	}
	
}

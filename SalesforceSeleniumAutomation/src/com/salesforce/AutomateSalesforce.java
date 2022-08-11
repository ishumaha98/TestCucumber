package com.salesforce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AutomateSalesforce {
	public void salesforceLogin(WebDriver driver) throws InterruptedException, IOException {
		File file = new File("C:\\Users\\acer2\\Documents\\register.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("AuraSF");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		driver.get("https://aura004--auratest.lightning.force.com/lightning/page/home");
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement login = driver.findElement(By.id("Login"));
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow current_Row = sheet.getRow(i);
			username.sendKeys(current_Row.getCell(0).getStringCellValue());
			password.sendKeys(current_Row.getCell(1).getStringCellValue());
		}
		login.click();
	}

	public void doEnrollment(WebDriver driver) throws InterruptedException, IOException {
		File file = new File("C:\\Users\\acer2\\Documents\\Autoenrollmentdata.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("customer info");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		Thread.sleep(25000);
		driver.findElement(By.xpath("//span[text()='Enroll New Customer']")).click();
		// offer selection
		// Thread.sleep(25000);
		driver.manage().timeouts().implicitlyWait(25000, TimeUnit.SECONDS);
		WebElement radio = driver
				.findElement(By.xpath("(//tr[@class=\"slds-hint-parent\"]//td[@role=\"gridcell\"])[7]"));
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(radio).click().build().perform();
		// customer info
		driver.findElement(By.xpath("(//button[@value=\"Customer Info\"])[1]")).click();

		driver.manage().timeouts().implicitlyWait(25000, TimeUnit.SECONDS);
		WebElement firstName = driver.findElement(By.xpath("//input[@name=\"First_Name\"]"));
		WebElement lastName = driver.findElement(By.xpath("//input[@name=\"Last_Name\"]"));
		WebElement Birthdate = driver.findElement(By.xpath("//input[@name=\"Birthdate\"]"));
		WebElement Street = driver.findElement(By.xpath("//input[@name=\"Street\"]"));
		WebElement City = driver.findElement(By.xpath("//input[@name=\"City\"]"));
		WebElement Postalcode = driver.findElement(By.xpath("//input[@name=\"Postal_Code\"]"));
		WebElement email = driver.findElement(By.xpath("//input[@name=\"Email1\"]"));
		WebElement phone = driver.findElement(By.xpath("//input[@name=\"Phone\"]"));

		for (int i = 1; i <= rowCount; i++) {
			firstName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			lastName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			Thread.sleep(3000);
			Birthdate.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
			Street.sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
			Thread.sleep(3000);
			City.sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
			Postalcode.sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());
			Thread.sleep(3000);
			email.sendKeys(sheet.getRow(i).getCell(6).getStringCellValue());
			phone.sendKeys(sheet.getRow(i).getCell(7).getStringCellValue());
		}
		driver.findElement(By.xpath("//button[text()=\"Next\"]")).click();
		Thread.sleep(5000);
		
		WebElement next = driver.findElement(By.xpath("//button[text()=\"Next\"]"));
		Actions actions3 = new Actions(driver);
		actions3.moveToElement(next).click().build().perform();
		driver.findElement(By.xpath("//input[@name='number']")).sendKeys("4111 1111 1111 1111");
        driver.findElement(By.xpath("//input[@name='expiry']")).sendKeys("07/23");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys("222");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='ssn']")).sendKeys("222-22-2222");
        Thread.sleep(15000);
        driver.findElement(By.xpath("//input[@name='confirmssn']")).sendKeys("222-22-2222");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Next']")).click();

        Thread.sleep(6000);
        WebElement element4 = driver.findElement(By.xpath("//button[text()='Next']"));
        Actions actions4 = new Actions(driver);
        actions4.moveToElement(element4).click().build().perform();
	
        WebElement Element = driver.findElement(By.xpath("((//flowruntime-lwc-field[@class=\"field-element\"])[12]//div)[3]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
		Thread.sleep(5000);
		Element.click();
        
		driver.findElement(By.xpath("//button[text()=\"Enroll\"]")).click();
	}
public void createCase(WebDriver driver) throws InterruptedException {
	Thread.sleep(6000);
	driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
	driver.findElement(By.xpath("(//ul[@role=\"group\"]//li)[2]//a")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@title='New']")).click();
	
	Thread.sleep(4000);
	driver.findElement(By.xpath("(//input[@class=' input'])[1]")).sendKeys("Testing");

    WebElement element1 = driver.findElement(By.xpath("//a[text()='None']"));
    Actions actions1 = new Actions(driver);
    actions1.moveToElement(element1).click().build().perform();
    driver.findElement(By.xpath("//a[text()='Test']")).click();

    Thread.sleep(2000);

    WebElement element2 = driver.findElement(By.xpath("(//a[text()='--None--'])[1]"));
    Actions actions2 = new Actions(driver);
    actions2.moveToElement(element2).click().build().perform();
    Thread.sleep(2000);
    driver.findElement(By.xpath("(//a[text()='Test'])[3]")).click();

    driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//div[text()='ipand']")).click();
    driver.findElement(By.xpath("//button[@title='Save']")).click();
}
}

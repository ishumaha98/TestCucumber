package salesforcepackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class SalesforceSeleniumAutomation {
	public void salesforceLogin(WebDriver driver) throws InterruptedException, IOException {
		File file = new File("C:\\Users\\acer2\\Documents\\register.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet5");
		driver.get("https://login.salesforce.com/");
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		/*
		 * for (int i = 1; i <= rowCount; i++) { XSSFRow current_Row = sheet.getRow(i);
		 * username.sendKeys(current_Row.getCell(0).getStringCellValue());
		 * password.sendKeys(current_Row.getCell(1).getStringCellValue());
		 * Thread.sleep(5000); driver.findElement(By.id("Login")).click();
		 * System.out.println("abc"); }
		 */
		username.sendKeys("priyamaha@practice.com");
		password.sendKeys("Priya@1234");
		driver.findElement(By.id("Login")).click();
		appLauncher(driver);
	}

	public void appLauncher(WebDriver driver) throws InterruptedException, IOException {
		Thread.sleep(10000);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click(); // app Launcher
		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[text()='Sales']")).click(); // Sales Page

	}

	public static void accountCreation(WebDriver driver) throws InterruptedException, IOException {
		// Account Creation
		File file = new File("C:\\Users\\acer2\\Documents\\register.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet5");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//one-app-nav-bar-item-root[@data-id=\"Account\"]")).click();
		driver.findElement(By.xpath("//div[text()='New']")).click();
		WebElement name = driver.findElement(By.xpath("//input[@name='Name']"));
		WebElement phone = driver.findElement(By.xpath("//input[@name='Phone']"));
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow current_Row = sheet.getRow(i);
			name.sendKeys(current_Row.getCell(0).getStringCellValue());
			phone.sendKeys(current_Row.getCell(1).getStringCellValue());
		}
		driver.findElement(By.xpath("//button[text()='Save']")).click();
	}

	public void createLead(WebDriver driver) throws IOException, InterruptedException {
		// create lead
		File file = new File("C:\\Users\\acer2\\Documents\\register.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Leads");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//one-app-nav-bar-item-root[@data-id='Lead']")).click();
		driver.findElement(By.xpath("//div[@title='New']")).click();
		driver.findElement(By.xpath("(//span[text()='--None--'])[1]")).click();
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Ms.']")).click();
		WebElement firstName = driver.findElement(By.xpath("//input[@name='firstName']"));
		WebElement LastName = driver.findElement(By.xpath("//input[@name='lastName']"));
		WebElement phone = driver.findElement(By.xpath("//input[@name='Phone']"));
		WebElement mobile = driver.findElement(By.xpath("//input[@name='MobilePhone']"));
		WebElement ComapanyName = driver.findElement(By.xpath("//input[@name='Company']"));
		WebElement title = driver.findElement(By.xpath("//input[@name='Title']"));
		WebElement email = driver.findElement(By.xpath("//input[@name='Email']"));

		for (int i = 1; i <= rowCount; i++) {

			firstName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			LastName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			phone.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
			mobile.sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
			ComapanyName.sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
			title.sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());
			email.sendKeys(sheet.getRow(i).getCell(6).getStringCellValue());

			WebElement Element = driver.findElement(By.xpath("(//span[text()='Open - Not Contacted'])[2]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Element);
			Element.click();

			driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Working - Contacted']")).click();
			driver.findElement(By.xpath("//button[text()='Save']")).click();

			WebElement converted = driver.findElement(By.xpath("//span[text()='Converted']"));
			Actions actions = new Actions(driver);
			actions.moveToElement(converted).click().build().perform();

			WebElement convert1 = driver.findElement(By.xpath("//span[text()=\"Select Converted Status\"]"));
			Actions actions1 = new Actions(driver);
			actions1.moveToElement(convert1).click().build().perform();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//button[text()='Convert']")).click();

			XSSFSheet sheet1 = wb.getSheet("Output");
			// int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

			WebElement account = driver.findElement(By.xpath("//div[text()='Account']"));
			WebElement contact = driver.findElement(By.xpath("//div[text()='Contact']"));

			XSSFCell cell = sheet1.getRow(i).createCell(1);
			XSSFCell cell1 = sheet1.getRow(i).createCell(2);

			if (account.isDisplayed() && contact.isDisplayed()) {
				cell.setCellValue("Created");
				cell1.setCellValue("Created");
			} else {
				cell.setCellValue("FAIL");
			}
			FileOutputStream outputStream = new FileOutputStream("C:\\Users\\acer2\\Documents\\register.xlsx");
			wb.write(outputStream);
			System.out.println("Account and Contact has been created");

			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			driver.findElement(By.xpath("((//div[@class=\"containerBodyCard vertical-align\"])[1]//a)[1]")).click();

			// contact creation
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@title='New'])[2]")).click();
			XSSFSheet sheet2 = wb.getSheet("Contact");

			WebElement firstName1 = driver.findElement(By.xpath("//input[@name='firstName']"));
			WebElement lastName1 = driver.findElement(By.xpath("//input[@name='lastName']"));
			WebElement phone1 = driver.findElement(By.xpath("//input[@name='Phone']"));
			WebElement homePhone1 = driver.findElement(By.xpath("//input[@name='HomePhone']"));
			WebElement title1 = driver.findElement(By.xpath("//input[@name='Title']"));
			WebElement birthday1 = driver.findElement(By.xpath("//input[@name='Birthdate']"));
			WebElement email1 = driver.findElement(By.xpath("//input[@name='Email']"));
			WebElement street = driver.findElement(By.xpath("(//textarea[@name='street'])[1]"));
			WebElement city = driver.findElement(By.xpath("(//input[@name='city'])[1]"));
			WebElement state = driver.findElement(By.xpath("(//input[@name='province'])[1]"));
			WebElement postalCode = driver.findElement(By.xpath("(//input[@name='postalCode'])[1]"));
			WebElement country = driver.findElement(By.xpath("(//input[@name='country'])[1]"));
			WebElement SaveBtn = driver.findElement(By.xpath("//button[text()='Save']"));

			firstName1.sendKeys(sheet2.getRow(i).getCell(0).getStringCellValue());
			lastName1.sendKeys(sheet2.getRow(i).getCell(1).getStringCellValue());
			phone1.sendKeys(sheet2.getRow(i).getCell(2).getStringCellValue());
			homePhone1.sendKeys(sheet2.getRow(i).getCell(3).getStringCellValue());
			title1.sendKeys(sheet2.getRow(i).getCell(4).getStringCellValue());
			birthday1.sendKeys(sheet2.getRow(i).getCell(5).getStringCellValue());
			email1.sendKeys(sheet2.getRow(i).getCell(6).getStringCellValue());
			street.sendKeys(sheet2.getRow(i).getCell(7).getStringCellValue());
			city.sendKeys(sheet2.getRow(i).getCell(8).getStringCellValue());
			state.sendKeys(sheet2.getRow(i).getCell(9).getStringCellValue());
			postalCode.sendKeys(sheet2.getRow(i).getCell(10).getStringCellValue());
			country.sendKeys(sheet2.getRow(i).getCell(11).getStringCellValue());
			SaveBtn.click();

		}
	}

	public void createOpportunity(WebDriver driver) throws IOException, InterruptedException {

		// Opportunity Creation
		File file = new File("C:\\Users\\acer2\\Documents\\register.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Opportunity");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		Thread.sleep(10000);
		WebElement element = driver.findElement(By.xpath("((//ul[@class='slds-button-group-list'])[2]//li)[4]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();

		driver.findElement(
				By.xpath("//div[@role='menu']//runtime_platform_actions-action-renderer[@title='New Opportunity']"))
				.click();

		Thread.sleep(5000);

		WebElement name = driver.findElement(By.xpath("(//input[@class=' input'])[1]"));
		WebElement closedate = driver.findElement(By.xpath("(//input[@class=' input'])[2]"));
		closedate.clear();
		driver.findElement(By.xpath("//a[text()=\"--None--\"]")).click();
		driver.findElement(By.xpath("//a[text()='Prospecting']")).click();

		for (int i = 1; i <= rowCount; i++) {

			name.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			closedate.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
		}

		driver.findElement(By.xpath("//footer[@class='slds-modal__footer']//span[text()='Save']")).click();
		Thread.sleep(5000);

		// opp process
		String javascript = "window.scrollBy(0,300)";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(javascript);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@title='Opportunities']")).click();
		driver.findElement(By.xpath("(//tr//th[@scope='row'])[2]//a")).click();
		Thread.sleep(3000);
		WebElement completeButton = driver.findElement(By.xpath("//button//span[text()='Mark Stage as Complete']"));
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(completeButton).click().build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("((//ul[@class=\"slds-tabs_default__nav\"])[5]//li)[2]//a")).click(); // details
																											// button
		String javascript1 = "window.scrollBy(0,300)";
		JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
		jsExecutor1.executeScript(javascript1);

		String probValue = driver.findElement(By.xpath("//slot[@name=\"outputField\"]//lightning-formatted-number"))
				.getText().toString();
		System.out.println(probValue);
		if (probValue.equals("10%")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-350)");
			Thread.sleep(3000);
			WebElement completeButton1 = driver
					.findElement(By.xpath("//button//span[text()='Mark Stage as Complete']"));
			Actions actions2 = new Actions(driver);
			actions2.moveToElement(completeButton1).click().build().perform();
		} else

			System.out.println("Value is less than 10%");

		String javascript2 = "window.scrollBy(0,300)";
		JavascriptExecutor jsExecutor2 = (JavascriptExecutor) driver;
		jsExecutor2.executeScript(javascript2);
		Thread.sleep(5000);
		String probValue2 = driver.findElement(By.xpath("//slot[@name=\"outputField\"]//lightning-formatted-number"))
				.getText().toString();
		System.out.println(probValue2);
		if (probValue2.equals("20%")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-350)");
			Thread.sleep(3000);
			WebElement completeButton1 = driver
					.findElement(By.xpath("//button//span[text()='Mark Stage as Complete']"));
			Actions actions2 = new Actions(driver);
			actions2.moveToElement(completeButton1).click().build().perform();
		} else

			System.out.println("Value is less than 20%");

		// ---

		String javascript3 = "window.scrollBy(0,300)";
		JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
		jsExecutor3.executeScript(javascript3);
		Thread.sleep(5000);
		String probValue3 = driver.findElement(By.xpath("//slot[@name=\"outputField\"]//lightning-formatted-number"))
				.getText().toString();
		System.out.println(probValue3);
		if (probValue3.equals("50%")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-350)");
			Thread.sleep(3000);
			WebElement completeButton1 = driver
					.findElement(By.xpath("//button//span[text()='Mark Stage as Complete']"));
			Actions actions2 = new Actions(driver);
			actions2.moveToElement(completeButton1).click().build().perform();
		} else

			System.out.println("Value is less than 50%");

		// ----

		Thread.sleep(2000);

		String javascript4 = "window.scrollBy(0,300)";
		JavascriptExecutor jsExecutor4 = (JavascriptExecutor) driver;
		jsExecutor4.executeScript(javascript4);
		Thread.sleep(3000);
		String probValue4 = driver.findElement(By.xpath("//slot[@name=\"outputField\"]//lightning-formatted-number"))
				.getText().toString();
		System.out.println(probValue4);
		if (probValue4.equals("60%")) {
			// String javascript2 = "window.scrollBy(0,-350)";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-350)");

			Thread.sleep(4000);
			WebElement completeButton1 = driver
					.findElement(By.xpath("//button//span[text()='Mark Stage as Complete']"));
			Actions actions2 = new Actions(driver);
			actions2.moveToElement(completeButton1).click().build().perform();
		} else

			System.out.println("Value is less");

		Thread.sleep(2000);

		String javascript5 = "window.scrollBy(0,300)";
		JavascriptExecutor jsExecutor5 = (JavascriptExecutor) driver;
		jsExecutor5.executeScript(javascript5);
		Thread.sleep(3000);
		String probValue5 = driver.findElement(By.xpath("//slot[@name=\"outputField\"]//lightning-formatted-number"))
				.getText().toString();
		System.out.println(probValue5);
		if (probValue5.equals("70%")) {
			// String javascript2 = "window.scrollBy(0,-350)";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-350)");

			Thread.sleep(4000);
			WebElement completeButton1 = driver
					.findElement(By.xpath("//button//span[text()='Mark Stage as Complete']"));
			Actions actions2 = new Actions(driver);
			actions2.moveToElement(completeButton1).click().build().perform();
		} else

			System.out.println("Value is less");

		Thread.sleep(2000);

		String javascript6 = "window.scrollBy(0,300)";
		JavascriptExecutor jsExecutor6 = (JavascriptExecutor) driver;
		jsExecutor6.executeScript(javascript6);
		Thread.sleep(3000);
		String probValue6 = driver.findElement(By.xpath("//slot[@name=\"outputField\"]//lightning-formatted-number"))
				.getText().toString();
		System.out.println(probValue6);
		if (probValue6.equals("75%")) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-350)");

			Thread.sleep(4000);
			WebElement completeButton1 = driver
					.findElement(By.xpath("//button//span[text()='Mark Stage as Complete']"));
			Actions actions2 = new Actions(driver);
			actions2.moveToElement(completeButton1).click().build().perform();
		} else

			System.out.println("Value is less");

		Thread.sleep(2000);

		String javascript7 = "window.scrollBy(0,300)";
		JavascriptExecutor jsExecutor7 = (JavascriptExecutor) driver;
		jsExecutor7.executeScript(javascript7);
		Thread.sleep(2000);
		String probValue7 = driver.findElement(By.xpath("//slot[@name=\"outputField\"]//lightning-formatted-number"))
				.getText().toString();
		System.out.println(probValue7);
		if (probValue7.equals("90%")) {
			// String javascript2 = "window.scrollBy(0,-350)";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-250)");

			Thread.sleep(4000);
			WebElement completeButton1 = driver
					.findElement(By.xpath("//button//span[text()='Mark Stage as Complete']"));
			Actions actions2 = new Actions(driver);
			actions2.moveToElement(completeButton1).click().build().perform();
		} else

			System.out.println("Value is less");
		driver.findElement(By.xpath("//select[@aria-required='true']")).click();
		
		driver.findElement(By.xpath("//option[text()='Closed Won']")).click();
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		

		// driver.findElement(By.xpath("(//div[@class=\"slds-grid\"]//a)[11]")).click();
		// Take Screenshot
		/*
		 * TakesScreenshot scrShot = ((TakesScreenshot) driver); File firstSrc =
		 * scrShot.getScreenshotAs(OutputType.FILE); File desc = new
		 * File("./opportunitycreatedsnap/img.png"); FileHandler.copy(firstSrc, desc);
		 */
	}

	public void printErrorMessage(WebDriver driver) throws InterruptedException {

		Thread.sleep(5000);
		driver.findElement(By.xpath("//one-app-nav-bar-item-root[@data-id='Campaign']")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@title='New']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		String errormsg = driver.findElement(By.xpath("//ul[@class=\"errorsList\"]//li")).getText();
		System.out.println(errormsg);
	}
}

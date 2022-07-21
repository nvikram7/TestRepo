package com.emicalculation.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.emicalculation.utils.DateUtils;
import com.emicalculation.utils.ExtentReportManager;
import com.emicalculation.utils.XLUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class BaseUI {

	public WebDriver driver;
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public SoftAssert softAssert = new SoftAssert();

	public String ssDirectoryPath;

	/* ============================= Driver Setup ============================= */

	public void invokebrowser(String browserName) {

		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--disable-notifications");
				driver = new ChromeDriver(option);
			} else if (browserName.equalsIgnoreCase("Mozila")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions option = new FirefoxOptions();
				option.addArguments("--disable-notifications");
				driver = new FirefoxDriver(option);
			} else if (browserName.equalsIgnoreCase("Opera")) {
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new OperaDriver();
			} else {
				driver = new SafariDriver();
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Initialize the Properties File
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\main\\resources\\ObjectRepository\\projectconfig.properties");
				prop.load(file);
			} catch (Exception e) {
				reportFail(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	/* ==================== Open URL ==================== */

	public void openURL(String URLKey) {
		try {
			driver.get(prop.getProperty(URLKey));
			reportPass(URLKey + " Identified Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
//	public void openURL(String URLKey) {
//
//		driver.get(prop.getProperty(URLKey));
//	}

	/* ==================== Close Browser ==================== */

	public void closeBrowser() {
		try {
			driver.close();
			reportPass("Application closed successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/* ==================== Quit Browser ==================== */

	public void quitBrowser() {
		driver.quit();
	}

	/* ==================== Identify Elements ==================== */

	public WebElement getElement(String locatorKey) {

		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);

			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);

			} else if (locatorKey.endsWith("_ClassName")) {
				element = driver.findElement(By.className(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);

			} else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);

			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);

			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);

			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);

			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();
		}

		return element;
	}

	/* ==================== Enter Text ==================== */

	public void enterText(String locator, String data) {
		try {
			getElement(locator).sendKeys(data);
			reportPass("'" + data + "' : Entered successfully in locator element");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/* ==================== Clear Text ==================== */

	public void clearTextAndEnter(String locator, String value) {
		try {
			WebElement element = getElement(locator);
			element.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
			logger.log(Status.INFO, "Cleared text successfully.");
			element.sendKeys(value);
			logger.log(Status.INFO, "Entered value successfully.");
			element.sendKeys(Keys.ENTER);
			reportPass("Entered the data in the text box - Successfull");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/* ==================== Get Element Text ==================== */

	public String getElementText(String locator) {
		return getElement(locator).getText();
	}

	/* ==================== Click Element ==================== */

	public void clickElement(String locator) {
		try {
			getElement(locator).click();
			reportPass("'" + locator + "' : Clicked successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/* ==================== Select from Drop Down Menu ==================== */

	public void selectElementFromDropDown(String locator, String visibleText) {

		try {
			Select menu = new Select(getElement(locator));
			menu.selectByVisibleText(visibleText);
			reportPass("'" + visibleText + "' - is selected successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void selectFromSuggestion(String locatorXpath, int index) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions
					.visibilityOfAllElements(driver.findElements(By.xpath(prop.getProperty(locatorXpath)))));

			List<WebElement> autoSuggets = driver.findElements(By.xpath(prop.getProperty(locatorXpath)));
			autoSuggets.get(index).click();
			reportPass("Correct element at index" + index + "from suggestion list, is selected successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/* ==================== Verify ==================== */

	public void verifyPageTitle(String expectedTitle) {

		String actualTitle = driver.getTitle();
		logger.log(Status.INFO, "Actual Title of Current Page is :" + actualTitle);
		logger.log(Status.INFO, "Expected Title is :" + expectedTitle);
		try {
			Assert.assertEquals(actualTitle, expectedTitle);
			reportPass("Page Title is verified.");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}

	}

	public void verifyElementText(String locator, String expectedText) {
		String actualText = getElementText(locator);
		logger.log(Status.INFO, "Actual Element Text is :" + actualText);
		logger.log(Status.INFO, "Expected Element Text is :" + expectedText);
		try {
			Assert.assertEquals(actualText, expectedText);
			reportPass("Element text is verified.");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}

	}

	public boolean isElementPresent(String locatorKey) {
		try {
			if (getElement(locatorKey).isDisplayed()) {
				reportPass(locatorKey + " : Element is Displayed");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	public boolean isElementSelected(String locatorKey) {
		try {
			if (getElement(locatorKey).isSelected()) {
				reportPass(locatorKey + " : Element is Selected");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	public boolean isElementEnabled(String locatorKey) {
		try {
			if (getElement(locatorKey).isEnabled()) {
				reportPass(locatorKey + " : Element is Enabled");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	/****************** Handle Frames **********************/
	public void switchToFrame(String frameLocator) {
		try {
			logger.log(Status.INFO, "Switching Frame : " + frameLocator);
			driver.switchTo().frame(frameLocator);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void switchToFrameByIndex(int frameNumner) {
		try {
			logger.log(Status.INFO, "Switching Frame : " + frameNumner);
			driver.switchTo().frame(frameNumner);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void switchToDefaultFrame() {
		try {
			logger.log(Status.INFO, "Switching to Main Window");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/* ==================== Wait Functions ==================== */
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
			i++;
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			boolean jsState = (boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState)
				break;
			else {
				waitLoad(1);
			}
			i++;
		}
	}

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ==================== Reporting ==================== */

	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
//		screenShot("#FAIL");
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	@AfterTest
	public void endReport() {
		report.flush();

	}

	/* ==================== Capture Screen Shot ==================== */
	@Parameters({"scenario"})
	@BeforeClass
	public void ssDirectoryForEachTest(String scenarioName) {

		String path = System.getProperty("user.dir") + "\\ScreenShots\\";
		String folderName = scenarioName+"_"+DateUtils.getTimeStamp();
		ssDirectoryPath = path + folderName;

		File ssDirectory = new File(path + folderName);
		if (!ssDirectory.exists()) {
			if (ssDirectory.mkdir()) {
				System.out.println(ssDirectory.getName() + " created successfully.");
			} else {
				System.out.println("Couldn't create " + ssDirectory.getName());
			}
		} else {
			System.out.println(ssDirectory.getName() + " already exists.");
		}

	}

	public void screenShot(String name) {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(ssDirectoryPath + "\\" + name + "_" + DateUtils.getTimeStamp() + ".png");

		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(ssDirectoryPath + "\\" + name + "_" + DateUtils.getTimeStamp() + ".png");
			logger.log(Status.INFO, "Screenshot is taken and added successfully.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void fullPageScreenShot(String name) {
		int wh = 500;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		long sh = (long) js.executeScript("return document.body.scrollHeight");

		int scroll = wh;
		int i = 1;

		while (scroll <= sh) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			screenShot(name + i);
			js.executeScript("window.scrollBy(0," + wh + ")");
			i++;
			scroll = scroll + wh;
		}
	}

	/* ============= Methods for Particular Automation ================ */

	public void scrollToElement(String locatorKey) {
		WebElement element = getElement(locatorKey);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void dismissAds() {

		try {
			switchToFrame("aswift_8");
			switchToFrame("ad_iframe");
			if (isElementPresent("adCancleButton_Id")) {
				clickElement("adCancleButton_Id");
			} else {
				switchToDefaultFrame();
				switchToFrame("aswift_8");
				clickElement("adCrossIcon_Xpath");
			}
			switchToDefaultFrame();
			reportPass("Dismiss the Advertisement Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void dismissBottomAdBar() {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getElement("bottomAdBariFrame_Xpath")));
//		Actions action = new Actions(driver);
//		WebElement From= getElement("bottomAdBar_Xpath");
//		action.dragAndDropBy(From,0, 40).build().perform();
		clickElement("bottomAdBar_Xpath");
		waitLoad(4);
	}
	
	public void printSliderScale(String scaleKey) {
		
		try {
			WebElement scale = getElement(scaleKey);
			List<WebElement> scaleValue = scale.findElements(By.xpath("span/span"));
			String scaleRange = "";
			for (WebElement value : scaleValue) {
				String text = value.getText();
				System.out.println(text);
				scaleRange =scaleRange + text + "   ";
			}
			System.out.println("[ " + scaleRange + "]");
			reportPass("Print the Scale Successfully");
			logger.log(Status.INFO, "Scale is : [ " + scaleRange + "]");
		} catch (Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void dragSlider(String Key) {
		try {
			WebElement slider = getElement(Key);
			Actions move = new Actions(driver);
			move.dragAndDropBy(slider, 30, 0).perform();
			reportPass("Slider is Working Correctly..");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void selectDateInCalendar(String date) {

		logger.log(Status.INFO, "Given Date or MonthYear is : " + date);
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		try {
			Date expectedDate = dateFormat.parse(date);

//			String day = new SimpleDateFormat("dd").format(expectedDate);
			String month = new SimpleDateFormat("MMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);

			String expectedMonthYear = month + " " + year;
			logger.log(Status.INFO, "Selecting the given monthYear....");
			while (true) {
				String displayYear = driver
						.findElement(By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']"))
						.getText();

				if (year.equals(displayYear)) {

					driver.findElement(
							By.xpath("/html[1]/body[1]/div[3]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/span[text()='"
									+ month + "']"))
							.click();

					break;
				} else if (expectedDate.compareTo(currentDate) > 0) {
					driver.findElement(By.xpath("//div[@class='datepicker-months']//th[@class='next']")).click();
				} else {
					driver.findElement(By.xpath("//div[@class='datepicker-months']//th[@class='prev']")).click();
				}
			}
			reportPass(expectedMonthYear + " - is Selected Successfully. ");
		} catch (ParseException e) {
			reportFail(e.getMessage());
			e.printStackTrace();
		}

	}

	public void exportTableData(String tableKey, String path, String sheetName) {

		try {
			logger.log(Status.INFO, "Initialize the Excel File path");
			XLUtility xlutil = new XLUtility(path);

			WebElement table = getElement(tableKey);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", table);
			screenShot(sheetName + "DataTable");

			// Write the Table Header in the Excel
			logger.log(Status.INFO, "Write the Table Header in the Excel");
			
			List<WebElement> headerColumns = table.findElements(By.xpath("tr[1]/th"));
			int headerColCount = headerColumns.size();
			int h =0;
			for (int head = 1; head <= headerColCount; head++) {
				
				String headerText = table.findElement(By.xpath("tr[1]/th[" + head + "]")).getText();
				if(headerText.equals("")) {
					head++;
					headerText = table.findElement(By.xpath("tr[1]/th[" + head + "]")).getText();
				}
				xlutil.setCellData(sheetName, 0, h, headerText);
				System.out.print(headerText + "\t");
				h++;
			}

			// List of Year in Table
			List<WebElement> yearRows = table.findElements(By.xpath("tr"));
			int yearCount = yearRows.size();
			int rowStart = 1;
			logger.log(Status.INFO, "Writing the Table Data in the Excel....");
			for (int y = 2; y < yearCount; y = y + 2) {

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearRows.get(y));
				List<WebElement> yearTableColumns = table.findElements(By.xpath("tr[" + y + "]/td"));
				int yearColCount = yearTableColumns.size();

				// Get the Year Data and write in Excel
				for (int cy = 0; cy < yearColCount; cy++) {
					int colYear = cy + 1;
					String yearData = table.findElement(By.xpath("tr[" + y + "]/td[" + colYear + "]")).getText();
					xlutil.setCellData(sheetName, rowStart, cy, yearData);
					System.out.print(yearData + "\t");
				}
				rowStart++;
				System.out.println();

				// Access Monthly EMI Details
				table.findElement(By.xpath("tr[" + y + "]/td[1]")).click();
				int m = y + 1;

				// List of the Months under each Year
				List<WebElement> monthRow = table.findElements(By.xpath("tr[" + m + "]/td[1]/div[1]/table/tbody/tr"));
				int monthCount = monthRow.size();

				// Get the Monthly Table Data of each year
				for (int r = 1; r <= monthCount; r++) {
					List<WebElement> monthTableColumns = table
							.findElements(By.xpath("tr[" + m + "]/td[1]/div[1]/table/tbody/tr[" + r + "]/td"));
					int monthColCount = monthTableColumns.size();

					// Write the Monthly Table Data of each year in Excel
					for (int cm = 0; cm < monthColCount; cm++) {
						int colMonth = cm + 1;
						String monthData = table
								.findElement(By.xpath(
										"tr[" + m + "]/td[1]/div[1]/table/tbody/tr[" + r + "]/td[" + colMonth + "]"))
								.getText();
						xlutil.setCellData(sheetName, rowStart, cm, monthData);
						System.out.print(monthData + "\t");
					}
					rowStart++;
					System.out.println();
				}
				rowStart++;
				System.out.println();
			}
			System.out.println("Web scrapping is done succesfully.....");
			logger.log(Status.INFO, "Path of the Excel File : " + path + " and Sheet Name : " + sheetName);
			reportPass("Web scrapping is done succesfully.....");
		} catch (IOException e) {
			reportFail(e.getMessage());
			e.printStackTrace();
		}
	}

}

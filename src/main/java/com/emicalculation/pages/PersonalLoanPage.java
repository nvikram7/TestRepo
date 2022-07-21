package com.emicalculation.pages;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.emicalculation.base.BaseUI;


public class PersonalLoanPage extends BaseUI {
	
	
	String testScenario = "Personal Loan Page : ";
	String amount = "950000";
	String rate = "10";
	String time = "4";
	
	@Test
	public void openApplication() {
		logger = report.createTest("Open Application : https://emicalculator.net");
		logger.log(Status.INFO, "Opening Browser..");
		invokebrowser("Chrome");
		reportPass("Browser opened Successfully.");
		openURL("URLKey");
//		dismissBottomAdBar();
		screenShot("Emicalculator");
	}
	
	@Test(priority = 1)
	public void navigatePersonalLoanTab() {
		
		logger = report.createTest(testScenario+"Navigate to Personal Loan Tab");
		clickElement("personalLoanTab_Id");
		verifyPageTitle("EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
		screenShot("PersonalLoanEmiCalculator");
		reportPass("Navigation to the Personal Loan Tab Successfull.");
	}
	
	@Test(priority = 2)
	public void enterPersonalLoanAmount() {
		logger = report.createTest(testScenario+"Enter Personal Loan Amount");
		clearTextAndEnter("pesrsonalLoanAmountTextBox_Xpath", amount);
		logger.log(Status.PASS,"Personal Loan Amount : "+amount+", Entered Successfully");
	}
	
	@Test(priority = 3)
	public void enterInterestRate() {
		logger = report.createTest(testScenario+"Enter Interest Rate");
		clearTextAndEnter("plInterestRateTextBox_Xpath", rate);
		logger.log(Status.PASS,"Interest Rate : "+rate+", for Personal Loan Entered Successfully");
	}
	
	@Test(priority = 4)
	public void enterLoanTenure() {
		logger = report.createTest(testScenario+"Enter Loan Tenure");
		clearTextAndEnter("plLoanTenureTextBox_Xpath",time);
		logger.log(Status.PASS,"Tenure : "+time+" yrs, for Personal Loan Entered Successfully");
	}
	
	@Test(priority = 5)
	public void captureCarLoanEmiDetails() {
		logger = report.createTest(testScenario+"Capture the Personal Loan Emi Details and Payment Schedule Details");
		scrollToElement("personalLoanEmiPaymentSummery_Xpath");
		waitLoad(2);
		screenShot("CarLoanDetails");
		scrollToElement("personalLoanCalenderDiv_Xpath");
		waitLoad(2);
		screenShot("CarLoanPaymentScheduleChart");
		reportPass("Capture the Car Loan Emi Details and Payment Schedule Chart Successfull.");
	}

	
	@Test(priority = 6)
	public void geTabletData() {
		
		logger = report.createTest(testScenario+"Export the Table Data in Excel");

		String path = System.getProperty("user.dir") + "\\DataFiles\\emiCalculation.xlsx";
		String sheetName = "PersonalLoan";
		exportTableData("personalLoanPaymentTable_Xpath", path, sheetName);
		
	}
	
	@Test(priority=7)
	public void closeApplication() {
		logger = report.createTest(testScenario+"Closing the Application...");
		closeBrowser();
	}
	
	
}

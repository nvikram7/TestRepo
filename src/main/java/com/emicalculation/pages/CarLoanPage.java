package com.emicalculation.pages;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.emicalculation.base.BaseUI;

public class CarLoanPage extends BaseUI {
	
	String testScenario = "Car Loan Page : ";
	String amount = "1500000";
	String rate = "9.5";
	String time = "1";

	@Test
	public void openApplication() {
		logger = report.createTest(testScenario+"Open Application : https://emicalculator.net");
		logger.log(Status.INFO, "Opening Browser..");
		invokebrowser("Chrome");
		reportPass("Browser opened Successfully.");
		openURL("URLKey");
//		dismissBottomAdBar();
		screenShot("Emicalculator");

	}

	@Test(priority = 1)
	public void navigateToCarLoanTab() {
		logger = report.createTest(testScenario+"Navigation to the Car Loan Calculator");
		clickElement("carloanmenu_Id");
		verifyPageTitle("EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
		screenShot("CarLoanEmiCalculator");
		reportPass("Navigation to the Car Loan Tab Successfull.");
	}

	@Test(priority = 2)
	public void setCarLoanAmount() {
		logger = report.createTest(testScenario+"Enter Car Loan Amount .");
		clearTextAndEnter("pesrsonalLoanAmountTextBox_Xpath", amount);
		logger.log(Status.PASS, "Car Loan Amount : " + amount + ", Entered Successfully");
	}
	
	@Test(priority = 3)
	public void setInterestRate() {
		logger = report.createTest(testScenario+"Enter Car Loan Interest Rate.");
		clearTextAndEnter("plInterestRateTextBox_Xpath", rate);
		logger.log(Status.PASS, "Interest Rate : " + rate + " %, for Car Loan Entered Successfully");
	}
	
	@Test(priority = 4)
	public void setLoanTenure() {
		logger = report.createTest(testScenario+"Enter Car Loan Tenure.");
		clearTextAndEnter("plLoanTenureTextBox_Xpath", time);
		logger.log(Status.PASS, "Tenure : " + time + " yrs, for Car Loan Entered Successfully");
		screenShot("CarLoan-AfterSetRelevantValues");
	}

	@Test(priority = 5)
	public void captureCarLoanEmiDetails() {
		logger = report.createTest(testScenario+"Capture the Car Loan Emi Details and Payment Schedule Details");
		scrollToElement("carLoanEmiPaymentSummery_Xpath");
		waitLoad(2);
		screenShot("CarLoanDetails");
		scrollToElement("carLoanCalenderDiv_Xpath");
		waitLoad(2);
		screenShot("CarLoanPaymentScheduleChart");
		reportPass("Capture the Car Loan Emi Details and Payment Schedule Chart Successfull.");
	}

	@Test(priority = 6)
	public void getOneMonthData() {

		logger = report.createTest(testScenario+"EMI Calculation for a Month");
		scrollToElement("carLoanTable_Xpath");
		clickElement("carLoanTableYear_Xpath");
		String PA = getElementText("carLoanTablePrincipleAmt_Xpath").substring(1);
		String IA = getElementText("carLoanTableInterestAmt_Xpath").substring(1);
		clickElement("carLoanTableYear_Xpath");
		System.out.println("The Principal Amount for One Month is : Rs." + PA);
		logger.log(Status.INFO, "The Principal Amount for One Month is : Rs." + PA);
		System.out.println("The Interest Amount for One Month is : Rs." + IA);
		logger.log(Status.INFO, "The Interest Amount for One Month is : Rs." + IA);
		reportPass("Get one month Principle amount and Interest Amount SuccessFull");

	}

	@Test(priority = 7)
	public void getTableData() {

		logger = report.createTest(testScenario+"Export the Table Data in Excel");
		String path = System.getProperty("user.dir") + "\\DataFiles\\emiCalculation.xlsx";
		String sheetName = "CarLoan";
		exportTableData("carLoanTable_Xpath", path, sheetName);
	}

	@Test(priority = 8)
	public void closeApplication() {
		logger = report.createTest(testScenario+"Closing the Application..");
		closeBrowser();
	}

}

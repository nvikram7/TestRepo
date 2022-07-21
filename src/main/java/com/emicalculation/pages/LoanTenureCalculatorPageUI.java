package com.emicalculation.pages;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.emicalculation.base.BaseUI;

public class LoanTenureCalculatorPageUI extends BaseUI {
	
	String testScenario = "Loan Tenure Calculator Page UI : ";
	String amount = "820000";
	String emi = "15000";
	String rate = "10.5";
	String fees = "8000";

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
	public void navigateToLoanTenureCal() {
		logger = report.createTest(testScenario+"Navigation to the Loan Tenure Calculator");
		clickElement("calculatorMenuLink_Id");
		clickElement("loanCalculator_Xpath");
		dismissAds();
//		waitLoad(2);
//		dismissBottomAdBar();
		clickElement("loanTenureTab_Id");
		verifyPageTitle("Loan Calculator — Calculate EMI, Affordability, Tenure & Interest Rate");
		screenShot("LoanTenureCalculator");
		reportPass("Navigation to the Loan Tenure Tab Successful");
	}
	
	
//	********************************** Loan Amount UI Check **********************************
	@Test(priority = 2)
	public void loanAmountTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Loan Amount Text Box Availibility");
		screenShot("LoanAmountTextBox");
		isElementPresent("loanTenureLoanAmountTextBox_Xpath");
	}
	
	@Test(priority = 3)
	public void loanAmountsliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for Loan Amount Slider Availibility");
		screenShot("LoanAmountSlider");
		isElementPresent("loanTenureLoanAmountSlider_Xpath");
	}
	
	@Test(priority = 4)
	public void loanAmountsliderCheck() {
		logger = report.createTest(testScenario+"Checking Loan Amount Slider Function");
		dragSlider("loanTenureLoanAmountSlider_Xpath");

	}
	
	@Test(priority = 5)
	public void enterLoanAmountCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Loan Amount Entered in Text Box");
		clearTextAndEnter("loanTenureLoanAmountTextBox_Xpath", amount);
	}
	
	@Test(priority=6)
	public void checkLoanAmountScale() {
		logger = report.createTest(testScenario+"Checking Loan Amount Scale");
		isElementPresent("loanTenureLoanAmountScale_Xpath");
		reportPass("Loan Amount Scale is Present.");
		printSliderScale("loanTenureLoanAmountScale_Xpath");
		reportPass("Loan Amount Scale Printed Successfully.");
	}

	
//	************************************* EMI UI Check *************************************
	@Test(priority = 7)
	public void emiTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for EMI Text Box Availibility");
		screenShot("EMITextBox");
		isElementPresent("loanTenureEMI_Xpath");
	}

	@Test(priority = 8)
	public void emiSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for EMI Slider Availibility");
		screenShot("EMISlider");
		isElementPresent("loanTenureEMISlider_Xpath");
	}

	@Test(priority = 9)
	public void emiSliderCheck() {
		logger = report.createTest(testScenario+"Checking EMI Slider Function");
		dragSlider("loanTenureEMISlider_Xpath");

	}
	
	@Test(priority = 10)
	public void enterEmiAmountCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly EMI Amount Entered in Text Box");
		clearTextAndEnter("loanTenureEMI_Xpath", emi);
	}

	@Test(priority=11)
	public void checkEmiAmountScale() {
		logger = report.createTest(testScenario+"Checking Emi Amount Scale");
		isElementPresent("loanTenureEMIScale_Xpath");
		reportPass("Emi Amount Scale is Present.");
		printSliderScale("loanTenureEMIScale_Xpath");
		reportPass("Emi Amount Scale Printed Successfully.");
	}
	
	
//	********************************** Interest Rate UI Check **********************************

	@Test(priority = 12)
	public void interestRateTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Interest Rate Text Box Availibility");
		scrollToElement("loanTenureEMISlider_Xpath");
		screenShot("InterestRateTextBox");
		isElementPresent("loanTenureInterestRate_Xpath");
	}

	@Test(priority = 13)
	public void interestRateSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for Interest Rate Slider Availibility");
		screenShot("InterestRateSlider");
		isElementPresent("loanTenureInterestRateSlider_Xpath");
	}


	@Test(priority = 14)
	public void interestRateSliderCheck() {
		logger = report.createTest(testScenario+"Checking Interest Rate Slider Function");
		dragSlider("loanTenureInterestRateSlider_Xpath");

	}
	
	@Test(priority = 15)
	public void enterInterestRateCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Interest Rate Entered in Text Box");
		clearTextAndEnter("loanTenureInterestRate_Xpath", rate);
	}
	

	@Test(priority=16)
	public void checkInterestRateScale() {
		logger = report.createTest(testScenario+"Checking Interest Rate Scale");
		isElementPresent("loanTenureInterestRateScale_Xpath");
		reportPass("Interest Rate Scale is Present.");
		printSliderScale("loanTenureInterestRateScale_Xpath");
		reportPass("Interest Rate Scale Printed Successfully.");
	}
	
	
//	********************************** Fees and Charges UI Check **********************************
	
	@Test(priority = 17)
	public void feesChargesTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Fees and Charges Text Box Availibility");
		screenShot("Fees&ChargesTextBox");
		isElementPresent("loanTenureFee_Xpath");
	}

	@Test(priority = 18)
	public void feesChargesSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for Fees and Charges Slider Availibility");
		screenShot("Fees&ChargesSlider");
		isElementPresent("loanTenureFeeSlider_Xpath");
	}

	@Test(priority = 19)
	public void feesChargesSliderCheck() {
		logger = report.createTest(testScenario+"Checking Fees and Charges Slider Function");
		dragSlider("loanTenureFeeSlider_Xpath");

	}
	
	@Test(priority = 20)
	public void enterFeesChargesCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Fees and Charges Amount Entered in Text Box");
		clearTextAndEnter("loanTenureFee_Xpath", fees);
	}

	@Test(priority=21)
	public void checkFeesChargesScale() {
		logger = report.createTest(testScenario+"Fees and Charges Rate Scale");
		isElementPresent("loanTenureFeeScale_Xpath");
		reportPass("Fees and Charges Scale is Present.");
		printSliderScale("loanTenureFeeScale_Xpath");
		reportPass("Fees and Charges Scale Printed Successfully.");
	}


//	********************************** Close Application **************************************
	
	@Test(priority = 22)
	public void closeApplication() {
		logger = report.createTest(testScenario+"Closing the Application...");
		closeBrowser();
	}
	
	
}

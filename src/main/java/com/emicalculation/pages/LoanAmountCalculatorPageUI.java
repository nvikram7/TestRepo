package com.emicalculation.pages;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.emicalculation.base.BaseUI;

public class LoanAmountCalculatorPageUI extends BaseUI {
	
	String testScenario = "Loan Amount Calculator Page UI : ";
	String amount = "15000";
	String rate = "10.5";
	String tenure = "1";
	String fees = "8000";

	@Test
	public void openBrowser() {
		logger = report.createTest(testScenario+"Open Application : https://emicalculator.net");
		logger.log(Status.INFO, "Opening Browser..");
		invokebrowser("Chrome");
		reportPass("Browser opened Successfully.");
		openURL("URLKey");
//		dismissBottomAdBar();
		screenShot("Emicalculator");

	}

	@Test(priority = 1)
	public void navigateToLoanAmountCal() {
		logger = report.createTest(testScenario+"Navigation to the Loan Amount Calculator");
		clickElement("calculatorMenuLink_Id");
		clickElement("loanCalculator_Xpath");
		dismissAds();
//		waitLoad(2);
//		dismissBottomAdBar();
		clickElement("loanAmountTab_Xpath");
		verifyPageTitle("Loan Calculator — Calculate EMI, Affordability, Tenure & Interest Rate");
		screenShot("LoanAmountCalculator");
		reportPass("Navigation to the Loan Amount Tab Successful");
	}
	
	
//	************ EMI UI Check ************
	@Test(priority = 2)
	public void emiTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for EMI Text Box Availibility");
		screenShot("EMITextBox");
		isElementPresent("loanAmountEMITextBox_Xpath");
	}
	
	@Test(priority = 3)
	public void emiSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for EMI Slider Availibility");
		screenShot("EMISlider");
		isElementPresent("loanAmountEMISlider_Xpath");
	}

	@Test(priority = 4)
	public void emiSliderCheck() {
		logger = report.createTest(testScenario+"Checking EMI Slider Function");
		dragSlider("loanAmountEMISlider_Xpath");
	}
	
	@Test(priority = 5)
	public void enterEmiAmountCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly EMI Amount Entered in Text Box");
		clearTextAndEnter("loanAmountEMITextBox_Xpath", amount);
	}

	@Test(priority=6)
	public void checkEmiAmountScale() {
		logger = report.createTest(testScenario+"Checking Emi Amount Scale");
		isElementPresent("loanAmountEMIScale_Xpath");
		reportPass("Emi Amount Scale is Present.");
		printSliderScale("loanAmountEMIScale_Xpath");
		reportPass("Emi Amount Scale Printed Successfully.");
	}

	
//	************* Interest Rate UI Check *************
	@Test(priority = 7)
	public void interestRateTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Interest Rate Text Box Availibility");
		scrollToElement("loanAmountEMISlider_Xpath");
		screenShot("InterestRateTextBox");
		isElementPresent("loanAmountInterestRateTextBox_Xpath");
	}


	@Test(priority = 8)
	public void interestRateSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for Interest Rate Slider Availibility");
		screenShot("InterestRateSlider");
		isElementPresent("loanAmountInterestRateSlider_Xpath");
	}

	@Test(priority = 9)
	public void interestRateSliderCheck() {
		logger = report.createTest(testScenario+"Checking Interest Rate Slider Function");
		dragSlider("loanAmountInterestRateSlider_Xpath");
	}
	
	@Test(priority = 10)
	public void enterInterestRateCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Interest Rate Entered in Text Box");
		clearTextAndEnter("loanAmountInterestRateTextBox_Xpath", rate);
	}

	@Test(priority=11)
	public void checkInterestRateScale() {
		logger = report.createTest(testScenario+"Checking Interest Rate Scale");
		isElementPresent("loanAmountInterestRateScale_Xpath");
		reportPass("Interest Rate Scale is Present.");
		printSliderScale("loanAmountInterestRateScale_Xpath");
		reportPass("Interest Rate Scale Printed Successfully.");
	}
	
	
//	************ Loan Tenure UI Check ************


	@Test(priority = 12)
	public void loanTenureTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Loan Tenure Text Box Availibility");
		scrollToElement("loanAmountInterestRateSlider_Xpath");
		screenShot("LoanTenureTextBox");
		isElementPresent("loanAmountTenureTextbox_Xpath");
	}

	@Test(priority = 13)
	public void loanTenureSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for loan tenure Slider Availibility");
		screenShot("LoanTenureSlider");
		isElementPresent("loanAmountTenureSlider_Xpath");
	}

	@Test(priority = 14)
	public void loanTenureSliderCheck() {
		logger = report.createTest(testScenario+"Checking Interest Rate Slider Function");
		dragSlider("loanAmountTenureSlider_Xpath");
	}
	
	@Test(priority = 15)
	public void enterLoanTenureCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Loan Tenure Entered in Text Box");
		clearTextAndEnter("loanAmountTenureTextbox_Xpath", tenure);
	}
	
	@Test(priority=16)
	public void checkLoanTenureScale() {
		logger = report.createTest(testScenario+"Checking Loan Tenure Scale");
		isElementPresent("loanAmountTenureScale_Xpath");
		reportPass("Loan Tenure Scale is Present.");
		clickElement("loanAmountTenureYrBtn_Xpath");
		logger.log(Status.INFO, "Printing the Scale range when Year is Selected");
		printSliderScale("loanAmountTenureScale_Xpath");
		clickElement("loanAmountTenureMonBtn_Xpath");
		logger.log(Status.INFO, "Printing the Scale range when Year is Selected");
		printSliderScale("loanAmountTenureScale_Xpath");
		reportPass("Interest Rate Scale Printed Successfully.");
	}
	
	
//	************ Fees and Charges UI Check ************
	
	@Test(priority = 17)
	public void feesChargesTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Fees and Charges Text Box Availibility");
		screenShot("Fees&ChargesTextBox");
		isElementPresent("loanAmountFeeTextBox_Xpath");
	}

	@Test(priority = 18)
	public void feesChargesSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for Fees and Charges Slider Availibility");
		screenShot("Fees&ChargesSlider");
		isElementPresent("loanAmountFeeSlider_Xpath");
	}

	@Test(priority = 19)
	public void feesChargesSliderCheck() {
		logger = report.createTest(testScenario+"Checking Fees and Charges Slider Function");
		dragSlider("loanAmountFeeSlider_Xpath");

	}

	@Test(priority = 20)
	public void enterFeesChargesCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Fees and Charges Amount Entered in Text Box");
		clearTextAndEnter("loanAmountFeeTextBox_Xpath", fees);
	}
	

	@Test(priority=21)
	public void checkFeesChargesScale() {
		logger = report.createTest(testScenario+"Fees and Charges Rate Scale");
		isElementPresent("loanAmountFeeScale_Xpath");
		reportPass("Fees and Charges Scale is Present.");
		printSliderScale("loanAmountFeeScale_Xpath");
		reportPass("Fees and Charges Scale Printed Successfully.");
	}

//	************ Close Application **************
	
	@Test(priority = 22)
	public void closeApplication() {
		logger = report.createTest(testScenario+"Closing the Application...");
		closeBrowser();
	}
	
	
}
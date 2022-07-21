package com.emicalculation.pages;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.emicalculation.base.BaseUI;

public class EmiCalculatorPageUI extends BaseUI{
	
	String testScenario = "Emi Calculator Page UI : ";
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
		logger = report.createTest(testScenario+"Navigation to the Loan Emi Calculator");
		clickElement("calculatorMenuLink_Id");
		clickElement("loanCalculator_Xpath");
		dismissAds();
//		waitLoad(2);
//		dismissBottomAdBar();
		clickElement("emiCalculatorTab_Xpath");
		verifyPageTitle("Loan Calculator — Calculate EMI, Affordability, Tenure & Interest Rate");
		screenShot("LoanEmiCalculator");
		reportPass("Navigation to the Loan Emi Tab Successful");
	}
	
	
//	************ Loan Amount UI Check ************
	@Test(priority = 2)
	public void loanAmountTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Loan Amount Text Box Availibility");
		screenShot("LoanAmountTextBox");
		isElementPresent("emiCalculatorLoanAmountTextBox_Xpath");
	}
	
	@Test(priority = 3)
	public void loanAmountSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for Loan Amount Slider Availibility");
		screenShot("LoanAmountSlider");
		isElementPresent("emiCalculatorLoanAmountSlider_Xpath");
	}
	
	@Test(priority = 4)
	public void loanAmountSliderCheck() {
		logger = report.createTest(testScenario+"Checking Loan Amount Slider Function");
		dragSlider("emiCalculatorLoanAmountSlider_Xpath");
	}

	@Test(priority = 5)
	public void enterLoanAmountCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Loan Amount Entered in Text Box");
		clearTextAndEnter("emiCalculatorLoanAmountTextBox_Xpath", amount);
	}
	
	@Test(priority=6)
	public void checkLoanAmountAmountScale() {
		logger = report.createTest(testScenario+"Checking Loan Amount Scale");
		isElementPresent("emiCalculatorLoanAmountScale_Xpath");
		reportPass("Loan Amount Scale is Present.");
		printSliderScale("emiCalculatorLoanAmountScale_Xpath");
		reportPass("Loan Amount Scale Printed Successfully.");
	}

	
//	************* Interest Rate UI Check *************
	@Test(priority = 7)
	public void interestRateTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Interest Rate Text Box Availibility");
		screenShot("InterestRateTextBox");
		isElementPresent("emiCalculatorInterestRateTextBox_Xpath");
	}

	@Test(priority = 8)
	public void interestRateSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for Interest Rate Slider Availibility");
		screenShot("InterestRateSlider");
		isElementPresent("emiCalculatorInterestRateSlider_Xpath");
	}
	
	@Test(priority = 9)
	public void interestRateSliderCheck() {
		logger = report.createTest(testScenario+"Checking Interest Rate Slider Function");
		dragSlider("emiCalculatorInterestRateSlider_Xpath");
	}

	@Test(priority = 10)
	public void enterInterestRateCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Interest Rate Entered in Text Box");
		clearTextAndEnter("emiCalculatorInterestRateTextBox_Xpath", rate);
	}
	
	@Test(priority=11)
	public void checkInterestRateScale() {
		logger = report.createTest(testScenario+"Checking Interest Rate Scale");
		isElementPresent("emiCalculatorInterestRateScale_Xpath");
		reportPass("Interest Rate Scale is Present.");
		printSliderScale("emiCalculatorInterestRateScale_Xpath");
		reportPass("Interest Rate Scale Printed Successfully.");
	}
	
	
//	************ Loan Tenure UI Check ************


	@Test(priority = 12)
	public void loanTenureTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Loan Tenure Text Box Availibility");
		scrollToElement("emiCalculatorInterestRateScale_Xpath");
		screenShot("LoanTenureTextBox");
		isElementPresent("emiCalculatorTenureTextbox_Xpath");
	}
	
	@Test(priority = 13)
	public void loanTenureSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for loan tenure Slider Availibility");
		screenShot("LoanTenureSlider");
		isElementPresent("emiCalculatorTenureSlider_Xpath");
	}

	@Test(priority = 14)
	public void loanTenureSliderCheck() {
		logger = report.createTest(testScenario+"Checking Interest Rate Slider Function");
		dragSlider("emiCalculatorTenureSlider_Xpath");
	}

	@Test(priority = 15)
	public void enterLoanTenureCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Loan Tenure Entered in Text Box");
		clearTextAndEnter("emiCalculatorTenureTextbox_Xpath", tenure);
	}
	
	@Test(priority=16)
	public void checkLoanTenureScale() {
		logger = report.createTest(testScenario+"Checking Loan Tenure Scale");
		isElementPresent("emiCalculatorTenureScale_Xpath");
		reportPass("Loan Tenure Scale is Present.");
		clickElement("emiCalculatorTenureYrBtn_Xpath");
		logger.log(Status.INFO, "Printing the Scale range when Year is Selected");
		printSliderScale("emiCalculatorTenureScale_Xpath");
		clickElement("emiCalculatorTenureMonBtn_Xpath");
		logger.log(Status.INFO, "Printing the Scale range when Year is Selected");
		printSliderScale("emiCalculatorTenureScale_Xpath");
		reportPass("Interest Rate Scale Printed Successfully.");
	}
	
	
//	************ Fees and Charges UI Check ************
	
	@Test(priority = 17)
	public void feesChargesTextBoxAvailibility() {
		logger = report.createTest(testScenario+"Checking for Fees and Charges Text Box Availibility");
		screenShot("Fees&ChargesTextBox");
		isElementPresent("emiCalculatorFeeTextBox_Xpath");
	}
	
	@Test(priority = 18)
	public void feesChargesSliderAvailibility() {
		logger = report.createTest(testScenario+"Checking for Fees and Charges Slider Availibility");
		screenShot("Fees&ChargesSlider");
		isElementPresent("emiCalculatorFeeSlider_Xpath");
	}

	@Test(priority = 19)
	public void feesChargesSliderCheck() {
		logger = report.createTest(testScenario+"Checking Fees and Charges Slider Function");
		dragSlider("emiCalculatorFeeSlider_Xpath");

	}
	
	@Test(priority = 20)
	public void enterFeesChargesCheck() {
		logger = report.createTest(testScenario+"Checking for Correctly Fees and Charges Amount Entered in Text Box");
		clearTextAndEnter("emiCalculatorFeeTextBox_Xpath", fees);
	}


	@Test(priority=21)
	public void checkFeesChargesScale() {
		logger = report.createTest(testScenario+"Fees and Charges Rate Scale");
		isElementPresent("emiCalculatorFeeScale_Xpath");
		reportPass("Fees and Charges Scale is Present.");
		printSliderScale("emiCalculatorFeeScale_Xpath");
		reportPass("Fees and Charges Scale Printed Successfully.");
	}

//	************ Close Application **************
	
	@Test(priority = 22)
	public void closeApplication() {
		logger = report.createTest(testScenario+"Closing the Application....");
		closeBrowser();
	}

}

package com.emicalculation.pages;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.emicalculation.base.BaseUI;

public class HomeLoanCalculationPage extends BaseUI {
	
	String testScenario = "Home Loan Calculator Page : ";
	String homeValue = "4000000";
	String dp = "18";
	String loanIns = "0";
	String loanAmt = "3000000";
	String iR = "8";
	String tenure = "25";
	String fees = "0.3";
	String monthYear = "11/2032";
	String oneTimeExpenses = "12";
	String taxes = "0.2";
	String homeIns = "0.07";
	String maintenance = "3000";

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
		clickElement("homeLoanEmiCalMenu_Xpath");
		dismissAds();
		waitLoad(2);
//		dismissBottomAdBar();
		verifyPageTitle("Home Loan EMI Calculator with Prepayments, Taxes & Insurance");
		screenShot("HomeLoanEmiCalculator");
		reportPass("Navigation to the Loan Tenure Tab Successful");
	}

	@Test(priority = 2)
	public void setHomeLoanDetails() {

		logger = report.createTest(testScenario+"Set the Home Loan Details in Respective Fields");
		
		clearTextAndEnter("homeValueTextBox_Xpath", homeValue);
		logger.log(Status.PASS, "Home Value : " + homeValue + ", Entered Successfully");

		clearTextAndEnter("homeLoanmarginOrDownPaymentTextBox_Xpath", dp);
		logger.log(Status.PASS, "Margine or Down Payment : " + dp + ", Entered Successfully");

		clearTextAndEnter("homeLoanInsuranceTextBox_Xpath", loanIns);
		logger.log(Status.PASS, "Loan Insurance : " + loanIns + ", Entered Successfully");

		clearTextAndEnter("homeLoanAmountTextBox_Xpath", loanAmt);
		logger.log(Status.PASS, "Loan Amount : " + loanAmt + ", Entered Successfully");

		clearTextAndEnter("homeLoanIRTextBox_Xpath", iR);
		logger.log(Status.PASS, "Home Loan Interest Rate : " + iR + ", Entered Successfully");

		clearTextAndEnter("homeLoanTenureTextBox_Xpath", tenure);
		logger.log(Status.PASS, "Loan Tenure : " + tenure + ", Entered Successfully");

		clearTextAndEnter("homeLoanFees&ChargesTextBox_Xpath", fees);
		logger.log(Status.PASS, "Loan Fees and Charges : " + fees + ", Entered Successfully");

		clickElement("homeLoanStartMon&YrTextBox_Xpath");
		selectDateInCalendar(monthYear);

		screenShot("HomeLoanEmiCalculator-AfterFillRelivantDetails");

	}
	
	@Test(priority = 3)
	public void setHomeOwnerExpenses() {
		
		logger = report.createTest(testScenario+"Enter the Relevant Details of Homeowner Expenses");
		
		clearTextAndEnter("homeLoanOneTimeExpensesTextBox_Xpath", oneTimeExpenses);
		logger.log(Status.PASS, "One-Time Expenses(%) : " + oneTimeExpenses + ", Entered Successfully");
		
		clearTextAndEnter("homeLoanPropertyTaxes_Xpath", taxes);
		logger.log(Status.PASS, "Property Taxes / year (%) : " + taxes + ", Entered Successfully");
		
		clearTextAndEnter("homeLoanHomeInsurance_Xpath", homeIns);
		logger.log(Status.PASS, "Home Insurance / year (%) : " + homeIns + ", Entered Successfully");
		
		clearTextAndEnter("homeLoanMaintenanceExpenses_Xpath", maintenance);
		logger.log(Status.PASS, "Maintenance Expenses / month : " + maintenance + ", Entered Successfully");
		
		screenShot("HomeLoanEmiCalculator-AfterFillHomeOwnerExpensesDetails");
	}
	
	
	@Test(priority = 4)
	public void capturePaymentDetails() {
		logger = report.createTest(testScenario+"Capture the Home Loan Details and Payment Schedule Details");
		scrollToElement("homeLoanDetails_Xpath");
		waitLoad(2);
		screenShot("HomeLoanDetails");
		scrollToElement("homeLoanPaymentChart_Xpath");
		waitLoad(2);
		screenShot("HomeLoanPaymentScheduleChart");
	}
	
	
	

	@Test(priority = 5)
	public void getTableData() {

		logger = report.createTest(testScenario+"Export the Table Data in Excel");
		String path = System.getProperty("user.dir") + "\\DataFiles\\emiCalculation.xlsx";
		String sheetName = "HomeLoanEMI";
		exportTableData("homeLoanPaymentScheduleTable_Xpath", path, sheetName);
	}
	
	@Test(priority = 6)
	public void closeApplication() {
		logger = report.createTest(testScenario+"Closing the Application...");
		closeBrowser();
	}

}

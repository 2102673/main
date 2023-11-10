package com.mycompany.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Integration UI test for PHP App.
 */
public class AppTest
{
	WebDriver driver; 
	WebDriverWait wait; 
	// String url = "http://192.168.50.207";
	String url = "http://172.20.10.2";

    String validSearchTerm = "ValidSearch";
    String xssAttackTerm = "<script>alert('XSS Attack');</script>";
    String sqlInjectionTerm = "'; DROP TABLE users; --";

    @Before
    public void setUp() { 
		driver = new HtmlUnitDriver(); 
		wait = new WebDriverWait(driver, 10); 
	} 

	@After
    public void tearDown() { 
		driver.quit(); 
	}	 
	
    @Test
    public void testValidSearchTerm() 
		throws InterruptedException { 

		//get web page
		driver.get(url);
		//wait until page is loaded or timeout error
		wait.until(ExpectedConditions.titleContains("Home Page")); 

		//enter input
		driver.findElement(By.name("search")).sendKeys(validSearchTerm);
		//click submit
		driver.findElement(By.name("submit")).submit();
	
		//check result 
		String expectedResult = "Search Results"; 
		boolean isResultCorrect = wait.until(ExpectedConditions.titleContains(expectedResult)); 
		assertTrue(isResultCorrect == true); 
	}
		
	@Test
    public void testXSSAttackTerm() 
		throws InterruptedException { 

		//get web page
		driver.get(url);
		//wait until page is loaded or timeout error
		wait.until(ExpectedConditions.titleContains("Home Page")); 

		//enter input
		driver.findElement(By.name("search")).sendKeys(xssAttackTerm);
		//click submit
		driver.findElement(By.name("submit")).submit();
	
		//check result
		String expectedResult = "Home Page"; 
		boolean isResultCorrect = wait.until(ExpectedConditions.titleContains(expectedResult)); 
		assertTrue(isResultCorrect == true); 
	}

	@Test
    public void testSQLInjectionTerm() 
		throws InterruptedException { 

		//get web page
		driver.get(url);
		//wait until page is loaded or timeout error
		wait.until(ExpectedConditions.titleContains("Home Page")); 

		//enter input
		driver.findElement(By.name("search")).sendKeys(sqlInjectionTerm);
		//click submit
		driver.findElement(By.name("submit")).submit();
	
		//check result
		String expectedResult = "Home Page"; 
		boolean isResultCorrect = wait.until(ExpectedConditions.titleContains(expectedResult)); 
		assertTrue(isResultCorrect == true); 
	}

}

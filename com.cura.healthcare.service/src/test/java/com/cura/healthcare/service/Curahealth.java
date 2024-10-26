package com.cura.healthcare.service;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import genericUtility.BaseTest;

public class Curahealth extends BaseTest
{
	@Test
	public void Login() throws Throwable
	{	
		driver.findElement(By.id("btn-make-appointment")).click();
		Thread.sleep(3000);
		
		//Scroll down 
		WebElement scroll1 = driver.findElement(By.tagName("h2"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].scrollIntoView(true)",scroll1);
		
		//Entering the userName and Password
		driver.findElement(By.xpath("//input[@id='txt-username'][1]")).sendKeys("John Doe");
		driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
				
		//Scroll down 
		WebElement scroll2 = driver.findElement(By.tagName("h2"));
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("arguments[0].scrollIntoView(true)",scroll2);
		
		//Handling the dropdown
		WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='combo_facility']"));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue("Tokyo CURA Healthcare Center");
		Thread.sleep(1500);
		dropdown.selectByValue("Hongkong CURA Healthcare Center");
		Thread.sleep(1500);
		dropdown.selectByValue("Seoul CURA Healthcare Center");
		Thread.sleep(1500);
		
		//Clicking on checkbox 
		WebElement checkbox = driver.findElement(By.className("checkbox-inline"));
		checkbox.click();
		Thread.sleep(1500);
		
		//Clicking on multiple radiobuttons 
		 WebElement radioButton = driver.findElement(By.xpath("//input[@id='radio_program_medicare']"));
		 radioButton.click();
		 Thread.sleep(1500);
		 WebElement radioButton2 = driver.findElement(By.id("radio_program_medicaid"));
		 radioButton2.click();
		 WebElement radioButton3 = driver.findElement(By.xpath("//input[@id='radio_program_none']"));
		 radioButton3.click();
		 
		//Handling calender popup  
		 driver.findElement(By.id("txt_visit_date")).click();		 
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']")));
		 String actualMonth= driver.findElement(By.xpath("//th[normalize-space()='October 2024']")).getText();
	     while (!(actualMonth.equals("October 2024"))) 
	     	{
	    	    driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][normalize-space()='»']")).click(); 
	            actualMonth= driver.findElement(By.xpath("//th[normalize-space()='October 2024']")).getText();
	     	}
	     	Thread.sleep(1500);
	        WebElement dateToSelect = driver.findElement(By.xpath("//td[normalize-space()='16']")); 
	        dateToSelect.click();
	     
	      //Adding the text in the comment box
	     driver.findElement(By.id("txt_comment")).sendKeys("This is Automation testing");
	     Thread.sleep(1500);
	     driver.findElement(By.id("btn-book-appointment")).click();
	     
	     WebElement successMessage = driver.findElement(By.xpath("//h2[normalize-space()='Appointment Confirmation']"));
	     Thread.sleep(1500);
	     Assert.assertTrue(successMessage.isDisplayed(), "Appointment Confirmation");
	     
	}
	
}
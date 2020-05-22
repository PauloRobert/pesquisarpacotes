package pageobjects;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadWriteExcel {
	WebDriver driver;
	WebDriverWait wait;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFCell cell;

	@Before
	public void TestSetup() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		// Enter url.
		driver.get("https://www.linkedin.com/login");
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	 public void ReadData() throws IOException
	 {
	     // Import excel sheet.
	     File src=new File("tools/TestData.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	    workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheetAt(0);
	      
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	         // Import data for Email.
	         cell = sheet.getRow(i).getCell(1);
	         driver.findElement(By.id("username")).sendKeys(cell.getStringCellValue());
	          
	         // Import data for password.
	         cell = sheet.getRow(i).getCell(2);
	         driver.findElement(By.id("password")).sendKeys(cell.getStringCellValue());
	          driver.quit();

	      // Write data in the excel.
	         FileOutputStream foutput=new FileOutputStream(src);
	           
	          // Specify the message needs to be written.
	          String message = "Data Imported Successfully.";
	           
	          // Create cell where data needs to be written.
	          sheet.getRow(i).createCell(3).setCellValue(message);
	            
	          // Specify the file in which data needs to be written.
	          FileOutputStream fileOutput = new FileOutputStream(src);
	           
	          // finally write content
	          workbook.write(fileOutput);
	           
	           // close the file
	          fileOutput.close();
	          
	                 
	        }
	  } 
 }

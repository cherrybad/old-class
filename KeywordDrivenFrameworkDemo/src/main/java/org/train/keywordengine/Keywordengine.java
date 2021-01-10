package org.train.keywordengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.train.keywordBase.Base;

public class Keywordengine {
	
	public WebDriver driver;
	public WebElement element;
	
	public Base base;
	
	public static Workbook book;
	public static Sheet sheet;
	
	public void executetest(String sheetName) throws Exception {
		
		String locatorName=null;
		String locatorValue=null;
		File credentials=new File("G:\\Tools\\SeleniumCertificationTraining\\KeywordDrivenFrameworkDemo\\src\\main\\java\\org\\train\\keywordscenario\\Keywordsaction.xlsx");
		
		FileInputStream fis=new FileInputStream(credentials);
		
		book=WorkbookFactory.create(fis);
		
		sheet=book.getSheet(sheetName);
		
		int k=0;
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			try {
			String LocatorColValue=sheet.getRow(i+1).getCell(k+1).toString().trim();
			
			if(!LocatorColValue.equalsIgnoreCase("NA")) {
				locatorName=LocatorColValue.split("=")[0].trim();  //name
				locatorValue=LocatorColValue.split("=")[1].trim();  //locator value
			}
			
			String action=sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value=sheet.getRow(i+1).getCell(k+3).toString().trim();
			
			switch (action) {
			case "openbrowser":
				base=new Base();
				driver=base.init_driver(value);
						
				break;
				
			case "enterurl":
				driver.get(value);
				break;
				
			case "quit"	:
				driver.quit();
				break;

			default:
				break;
			}
			
			switch (locatorName) {
			case "id":
				
				 element=driver.findElement(By.id(locatorValue));
				 if(action.equalsIgnoreCase("sendkeys")) {
					 element.sendKeys(value);
				 }else if (action.equalsIgnoreCase("click")) {
					 element.click();
				 }
				 locatorName=null;
				
				break;
				
			case "name":
				element=driver.findElement(By.name(locatorValue));
				element.sendKeys(value);
				locatorName=null;

			default:
				break;
			}
			
			}
			catch(Exception e) {
			
			
		}
		
		
	}
	}
}


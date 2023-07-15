package com.automation.download.pdf.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.*;


public class LoginAutomation {
    @Test
    public void login() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get("http://164.100.181.132/Certificate/Login.aspx");
        driver.manage().window().maximize();
        WebElement username=driver.findElement(By.id("txtUserName"));
        WebElement password=driver.findElement(By.id("txtPassword"));
        WebElement login=driver.findElement(By.name("btnLogin"));
        username.sendKeys("9454400253");
        password.sendKeys("cctns@22");
        login.click();

        //List<Map<String, String>> collection = readExcelToCollection();
        Map<Integer,String> collection=new HashMap<Integer,String>();
        collection.put(1,"316342323786");
        collection.put(2,"316342324155");
        collection.put(3,"316342323887");
        collection.put(4,"316342323943");
        collection.put(5,"316342323977");
        collection.put(6,"316342324010");
//        316342324021
//        316342323501
//        316342324078
//        316342324106
//        316342324316
//        316342324536
//        316342324423
//        316342324595

        // Access the data in the collection

        collection.forEach((key, value) -> {
            WebElement id=driver.findElement(By.id("txtCHARNUM"));
            WebElement login1=driver.findElement(By.name("btn"));
            id.sendKeys(value);
            login1.click();
        });
       /* String actualUrl="https://live.browserstack.com/dashboard";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);*/
    }

    public static List<Map<String, String>> readExcelToCollection() {
        List<Map<String, String>> collection = new ArrayList<>();
        String filePath = "C:\\Users\\Administrator\\Desktop";
        String sheetName = "Book.xlsx";

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                Row headerRow = sheet.getRow(0);
                int rowCount = sheet.getPhysicalNumberOfRows();
                int colCount = headerRow.getPhysicalNumberOfCells();

                for (int i = 1; i < rowCount; i++) {
                    Row row = sheet.getRow(i);
                    Map<String, String> data = new HashMap<>();

                    for (int j = 0; j < colCount; j++) {
                        Cell cell = row.getCell(j);
                        String header = headerRow.getCell(j).getStringCellValue();
                        String value = "";

                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    value = String.valueOf(cell.getNumericCellValue());
                                    break;
                                case BOOLEAN:
                                    value = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                // Handle other cell types if needed
                            }
                        }

                        data.put(header, value);
                    }

                    collection.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return collection;
    }
}
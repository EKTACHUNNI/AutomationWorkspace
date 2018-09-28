package com.ekta.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleAutoSuggestions {
    public static void main(String ar[]) throws InterruptedException{
//        //System.setProperty("webdriver.gecko.driver","Documents/AutomationDemo/geckodriver.exe");

        WebDriver driver=new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","/Users/manasektasharma/Documents/AutomationDemo/chromedriver.exe");
        //driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
        Thread.sleep(120);
        driver.findElement(By.id("lst-ib")).sendKeys("Jobs");
        String xp="//div[starts-with(text(),'jobs')]";
        List<WebElement> allAST =driver.findElements(By.xpath(xp));
        int count=allAST.size();
        System.out.println(count);
        for(WebElement ast:allAST){
            String t=ast.getText();
            System.out.println(t);
        }
        driver.close();


    }
}

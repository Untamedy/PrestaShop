/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop.search;


import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author YBolshakova
 */
public class SearchByParameters {

    private EventFiringWebDriver driver;
    

    public SearchByParameters(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void searchBy(String parameter) { 
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(parameter);        
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
    }
    
    

}

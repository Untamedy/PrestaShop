/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop.sort;



import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;



/**
 *
 * @author YBolshakova
 */
public class SortByParameter {

    private EventFiringWebDriver eventDriver;
   
   

    public SortByParameter(EventFiringWebDriver eventDriver) {
        this.eventDriver = eventDriver;
    }

    public void sortBy(String paramXpath) throws InterruptedException {        
        eventDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        eventDriver.findElement(By.xpath("//a[@class='select-title']")).click();  
        eventDriver.findElement(By.xpath(paramXpath)).click();
        eventDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); 
    }

}



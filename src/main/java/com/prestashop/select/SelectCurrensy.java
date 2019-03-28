/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop.select;

import static com.prestashop.PrestaShopTest.logToConsole;
import com.prestashop.checkers.CheckCurrensyForGoods;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author YBolshakova
 */
public class SelectCurrensy {

    private EventFiringWebDriver driver;
    private CheckCurrensyForGoods checkCurrensy;
    

    public SelectCurrensy(EventFiringWebDriver driver, CheckCurrensyForGoods checkCurrensy) {
        this.driver = driver;
        this.checkCurrensy = checkCurrensy;
    }

   
    public void setCurrensy(String currensyParam) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement currensy = driver.findElement(By.xpath("//span[2]"));
        currensy.click();
        WebElement usd = driver.findElement(By.xpath(currensyParam));
        usd.click(); 
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
        String currensyCurent = checkCurrensy.getCurentCurrensy();
        if(currensyParam.contains(currensyCurent)){
            logToConsole.info("Test \"Set curensy\" is PASSED!");           
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.WebDriverEventListener;
import static com.prestashop.PrestaShopTest.logToFile;

/**
 *
 * @author YBolshakova
 */
public class LogToFile implements WebDriverEventListener{

       @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        logToFile.info("WebDriver will navigate to" + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logToFile.info("WebDriver navigate to " + url + " successfully");
    }
   

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logToFile.info("WebDriver will find element: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logToFile.info("Element is found");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logToFile.info("WebDriver wiil click to element " + element.getTagName());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logToFile.info("Click to element successfully");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        logToFile.info("WebDriver change value of element " + element.getTagName() + ". Send key: " + keysToSend[0].toString());
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        logToFile.info("Value change successfully");
    }
    
    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        logToFile.info("WebDriver wiil get text of element: " + element.getAttribute("class"));
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
      logToFile.info("Text of element is " + text);  
    }
    
    
    @Override
    public void beforeNavigateBack(WebDriver driver) {      
    }
    @Override
    public void afterNavigateBack(WebDriver driver) {        
    }    @Override
    public void beforeNavigateForward(WebDriver driver) {        
    }
    @Override
    public void afterNavigateForward(WebDriver driver) {        
    }
    @Override
    public void beforeNavigateRefresh(WebDriver driver) {        
    }
    @Override
    public void afterNavigateRefresh(WebDriver driver) {       
    }
    @Override
    public void beforeAlertAccept(WebDriver driver) {    
    }
    @Override
    public void afterAlertAccept(WebDriver driver) {       
    }
    @Override
    public void afterAlertDismiss(WebDriver driver) {        
    }
    @Override
    public void beforeAlertDismiss(WebDriver driver) {       
    }
     @Override
    public void beforeScript(String script, WebDriver driver) {        
    }
    @Override
    public void afterScript(String script, WebDriver driver) {        
    }
    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {        
    }
    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {        
    }
    @Override
    public void onException(Throwable throwable, WebDriver driver) {       
    }
    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {       
    }
    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {     
    }
}

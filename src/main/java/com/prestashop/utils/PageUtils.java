/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author YBolshakova
 */
public class PageUtils {
    private WebDriver driver;
    
    public PageUtils(WebDriver driver){
        this.driver=driver;
    }
    
    public List<WebElement> listOfelements(String xpath) {
        List<WebElement> elementsList = driver.findElements(By.xpath(xpath));
        return elementsList;
    }
    
    public double parserToInt(String stringToparse) {
        String[] s = stringToparse.split(" ");
        double i = Double.parseDouble(s[0].replaceAll(",", ".").replaceAll("-|%|$|€", ""));       
        return i;
    }
}

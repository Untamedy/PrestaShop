/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop;

import com.prestashop.select.SelectCurrensy;
import com.prestashop.utils.PageUtils;
import com.prestashop.sort.SortByParameter;
import com.prestashop.search.SearchByParameters;
import com.prestashop.checkers.CheckSortByPrice;
import com.prestashop.checkers.CheckGoodsCurrensy;
import com.prestashop.checkers.CheckGoodsAmount;
import com.prestashop.checkers.CheckCurrensyForGoods;
import com.prestashop.checkers.CheckSaleGoodsPrice;
import com.prestashop.utils.LogToFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;


/**
 *
 * @author YBolshakova
 */
public class PrestaShopTest {    
    public static final Logger logToFile = org.apache.log4j.Logger.getLogger("rootLogger");    
    public static Logger logToConsole = org.apache.log4j.Logger.getLogger("PrestaShopLoggerToConsole");

    public static void main(String[] args) throws InterruptedException {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-headless");
        options.addArguments("window-size=1200x600");
        
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        eventDriver.register(new LogToFile()); 
        
        
        eventDriver.get("http://prestashop-automation.qatestlab.com.ua");       

        //MainPage mainPage = new MainPage(driver);     
        PageUtils pageUtils = new PageUtils(driver);
        CheckCurrensyForGoods checkCurrensyForGoods = new CheckCurrensyForGoods(eventDriver, pageUtils);
        SelectCurrensy s = new SelectCurrensy(eventDriver, checkCurrensyForGoods);
        SearchByParameters search = new SearchByParameters(eventDriver);
        CheckGoodsAmount checkGoodsAmount = new CheckGoodsAmount(eventDriver, pageUtils);
        CheckGoodsCurrensy checkGoodsCurrensy = new CheckGoodsCurrensy(eventDriver, pageUtils);
        SortByParameter sortByParameter = new SortByParameter(eventDriver);
        CheckSortByPrice sortByPrice = new CheckSortByPrice(eventDriver, pageUtils);
        CheckSaleGoodsPrice saleGoodsPrice = new CheckSaleGoodsPrice(eventDriver, pageUtils);
       
        checkCurrensyForGoods.getGoodsCurensy();
        s.setCurrensy("//a[contains(.,'USD $')]");
        search.searchBy("dress");
        checkGoodsAmount.amountCompare();
        checkGoodsCurrensy.checkCurrensy();
        sortByParameter.sortBy("//a[5]");
        Thread.sleep(3000);
        sortByPrice.checkSort("//div[@class='product-price-and-shipping']//span[1]");
        saleGoodsPrice.checkTypeOfPrice();
        saleGoodsPrice.checkSaleSum();
        saleGoodsPrice.checkIsInPercent();
        eventDriver.quit();

    }

}

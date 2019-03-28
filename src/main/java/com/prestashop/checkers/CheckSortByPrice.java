/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop.checkers;

import static com.prestashop.PrestaShopTest.logToConsole;
import com.prestashop.utils.PageUtils;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author YBolshakova
 */
public class CheckSortByPrice {

    private EventFiringWebDriver driver;
    private PageUtils pageElements;
    
    double priceElement = 0;
    boolean result = false;

    public CheckSortByPrice(EventFiringWebDriver driver, PageUtils pageElements) {
        this.driver = driver;
        this.pageElements = pageElements;
    }

    public boolean checkSort(String xpath) {
        List<WebElement> elements = new ArrayList<>(pageElements.listOfelements(xpath));
        for (WebElement e : elements) {
            String price = e.getText();
            double i = pageElements.parserToInt(price);
            if (priceElement == 0) {
                priceElement = i;
            } else {
                if (i <= priceElement) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        if (result) {
            logToConsole.info("Test \"Sort goods from high to low\" is PASSED!");
        }
        return false;
    }

}

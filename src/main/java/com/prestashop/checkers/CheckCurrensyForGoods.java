/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop.checkers;

import static com.prestashop.PrestaShopTest.logToConsole;
import com.prestashop.utils.PageUtils;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author YBolshakova
 */
public class CheckCurrensyForGoods {

    private EventFiringWebDriver driver;
    private PageUtils pageElements;
    private String curentCurrensy = "";
    private static final String UAN = "UAH ₴";
    private static final String EUR = "EUR €";
    private static final String USD = "USD $";
    private final String eur_s = ("\u20AC");
    private final String uan_s = ("\u20B4");
    private final String usd_s = ("\u0024");

    public CheckCurrensyForGoods(EventFiringWebDriver driver, PageUtils pageElements) {
        this.driver = driver;
        this.pageElements = pageElements;
    }

    public boolean getGoodsCurensy() {
        List<WebElement> goods = pageElements.listOfelements("//div[@class='currency-selector dropdown js-dropdown open']//span[@class='price']");
        boolean result = true;
        for (WebElement e : goods) {
            if (!e.getText().endsWith(getCurentCurrensy())) {
                result = false;
            }
        }
        if (result) {
            logToConsole.info("Test \"Correspondence of the price of goods to the established monetary unit\" is PASSED!");
        }
        return false;
    }
    
    public String getCurentCurrensy() {
        curentCurrensy = driver.findElement(By.xpath("//div[@id='_desktop_currency_selector']/div/span[2]")).getText();
        if (curentCurrensy.equalsIgnoreCase(UAN)) {
            return uan_s;
        }
        if (curentCurrensy.equalsIgnoreCase(EUR)) {
            return eur_s;
        }
        if (curentCurrensy.equalsIgnoreCase(USD)) {
            return usd_s;
        }
        return curentCurrensy;
    }

}

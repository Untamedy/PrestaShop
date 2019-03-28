/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prestashop.checkers;

import static com.prestashop.PrestaShopTest.logToConsole;
import com.prestashop.utils.PageUtils;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author YBolshakova
 */
public class CheckGoodsCurrensy {

    private EventFiringWebDriver driver;
    private PageUtils pageElements;
    private final String usd_s = ("\u0024");

    public CheckGoodsCurrensy(EventFiringWebDriver driver, PageUtils pageElements) {
        this.driver = driver;
        this.pageElements = pageElements;
    }

    public boolean checkCurrensy() {
        boolean result = false;
        List<WebElement> list = pageElements.listOfelements("//span[@class='price']");
        for (WebElement e : list) {
            if (e.getText().endsWith(usd_s)) {
                result = true;
            }else{
                logToConsole.info("Goods currensy " + e.getText() + " does't matched curent currensy"); 
            } 
        }
        if(result){
            logToConsole.info("Test \"Set currensy\" is PASSED!");
            logToConsole.info("Test \"Check goods currensy after change\" is PASSED!");
        }else{
            logToConsole.info("Test \"Check goods currensy after change\" is FAILED!");
        }
        return result;

    }
}

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
public class CheckGoodsAmount {

   private EventFiringWebDriver driver;
   PageUtils pageElements;

    public CheckGoodsAmount(EventFiringWebDriver driver, PageUtils pageElements) {
        this.pageElements = pageElements;
        this.driver = driver;
    }
    

    public String getGoodsAmount() {
        WebElement totalGoodsAmount = driver.findElement(By.xpath("//div[@class='col-md-6 hidden-sm-down total-products']/p"));
        if(totalGoodsAmount.isDisplayed()){            
            String goodsAmount = totalGoodsAmount.getText();
            String amount = goodsAmount.replaceAll("\\D+", "").trim();
            logToConsole.info("Test\"Total goods amount display\" is PASSED!");
            return amount;
        }
        return "Test\"Total goods amount display\" is FAILED!";
        
    }

    public int countGoods() {
        List<WebElement> goods = pageElements.listOfelements("//span[@class='price']");
        int amount = goods.size();
        return amount;
    }

    public boolean amountCompare() {
        String total = getGoodsAmount();
        String countResoult = String.valueOf(countGoods());
        if (total.equals(countResoult)) {
            logToConsole.info("Test \"Comparison of the total quantity of goods with the actual amount of goods\" is PASSED");
            return true;
        } else {
            logToConsole.info("Test \"Comparison of the total quantity of goods with the actual amount of goods\" is FAILED");
            return false;
        }
    }

}

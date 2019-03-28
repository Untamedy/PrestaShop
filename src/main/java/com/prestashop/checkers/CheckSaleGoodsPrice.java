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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author YBolshakova
 */
public class CheckSaleGoodsPrice {

    private EventFiringWebDriver driver;
    private PageUtils pageElements;
    double regular;
    double prise;
    int discount;

    public CheckSaleGoodsPrice(EventFiringWebDriver driver, PageUtils pageElements) {
        this.driver = driver;
        this.pageElements = pageElements;
    }

    public boolean checkTypeOfPrice() {
        List<WebElement> discountGoods = checkIsDiscount();
        boolean result = false;
        if (!discountGoods.isEmpty()) {
            for (WebElement e : discountGoods) {
                int i = 0;
                List<WebElement> span = e.findElements(By.xpath(".//span"));
                for (WebElement s : span) {
                    if (isDisplayElement("regular-price", "class", s) && s.isDisplayed()) {
                        i++;
                    } else {
                        if (isDisplayElement("discount-percentage", "class", s) && s.isDisplayed()) {
                            i++;
                        } else {
                            if (isDisplayElement("price", "itemprop", s) && s.isDisplayed()) {
                                i++;
                            }
                        }
                    }
                }
                if (i == 3) {
                    result = true;
                } else {
                    logToConsole.info("There are not any goods with discount or some of price type is absent");
                }
            }
            if (result) {
                logToConsole.info("Test \"Availability of all types of prices\" is PASSED!");
                return result;
            }
        }
        logToConsole.info("Test \"Availability of all types of prices\" is FAILED!");
        return result;
    }

    public boolean checkIsInPercent() {
        List<WebElement> percent = driver.findElements(By.xpath("//span[@class='discount-percentage']"));
        boolean result = false;
        for (WebElement p : percent) {
            if (p.getText().endsWith("%")) {
                result = true;
            }
        }
        if (result) {
            logToConsole.info("Test \"Discount display in percent\" is PASSED!");
            return result;
        }
        logToConsole.info("Test \"Discount display in percent\" is FAILED!");
        return result;

    }

    public void checkSaleSum() {
        List<WebElement> elementsWithSale = checkIsDiscount();

        if (!elementsWithSale.isEmpty()) {
            for (WebElement e : elementsWithSale) {
                List<WebElement> span = e.findElements(By.xpath(".//span"));
                for (WebElement s : span) {
                    if (s.getAttribute("class").equals("regular-price")) {
                        regular = pageElements.parserToInt(s.getText());
                    } else {
                        if (s.getAttribute("class").equals("discount-percentage")) {
                            discount = (int) pageElements.parserToInt(s.getText());
                        } else {
                            if (s.getAttribute("itemprop").equals("price")) {
                                prise = pageElements.parserToInt(s.getText());
                            }
                        }
                    }
                }
                if (countSale(regular, prise, discount)) {
                    logToConsole.info("Test \"cost calculation with discount\" for discount :" + discount + "% is PASSED!");
                } else {
                    logToConsole.info("Test \"cost calculation with discount\" for discount :" + discount + "% is FAILED!");
                }
            }
        }
    }

    public List<WebElement> checkIsDiscount() {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class ='product-price-and-shipping']"));
        List<WebElement> discountElements = new ArrayList<>();
        for (WebElement e : elements) {
            List<WebElement> spanElement = e.findElements(By.xpath(".//span"));
            if (spanElement.size() > 1) {
                discountElements.add(e);
            }
        }
        return discountElements;
    }

    public boolean countSale(double regular, double price, int discount) {
        boolean equals = false;
        int result = (int) Math.rint(100 - (price * 100 / regular));
        if (discount == result) {
            equals = true;
            return equals;
        } else {
        }
        return equals;
    }

    public boolean isDisplayElement(String value, String attribute, WebElement element) {
        if (value.equals(element.getAttribute(attribute))) {
            return true;
        }
        return false;
    }

}

/*
 * MIT License
 *
 * Copyright (c) 2018 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ranjansingh.page;

import com.ranjansingh.driver.DriverManager;
import com.ranjansingh.utils.AlertUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.ranjansingh.config.ConfigurationManager.configuration;
import static com.ranjansingh.utils.WaitUtils.waitForClickable;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPageObject {

    protected AbstractPageObject() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), configuration().timeout()), this);
    }

    protected void navigateTo(String path) {
        DriverManager.getDriver().get(configuration().url() + path);
    }

    protected void clearAndType(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    protected void click(WebElement element) {
        try {
            waitForClickable(element);
            element.click();
        } catch (Exception e) {
            // Fallback to JS click
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
        }
    }

    protected void acceptAlert() {
        AlertUtils.acceptAlert();
    }

    protected void dismissAlert() {
        AlertUtils.dismissAlert();
    }

    protected String getAlertText() {
        return AlertUtils.getAlertText();
    }

    protected void sendKeysToAlert(String text) {
        AlertUtils.sendTextToAlert(text);
    }

}

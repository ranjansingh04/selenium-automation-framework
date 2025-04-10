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

package com.ranjansingh.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import com.ranjansingh.driver.DriverManager;
import static com.ranjansingh.utils.WaitUtils.waitForAlert;

public class AlertUtils {

    private static Alert getAlert() {
        try {
            waitForAlert();
            return DriverManager.getDriver().switchTo().alert();
        } catch (Exception e) {
            throw new RuntimeException("No alert present within timeout.", e);
        }
    }

    public static void acceptAlert() {
        try {
            getAlert().accept();
        } catch (NoAlertPresentException e) {
            throw new RuntimeException("No alert present to accept.", e);
        }
    }

    public static void dismissAlert() {
        try {
            getAlert().dismiss();
        } catch (NoAlertPresentException e) {
            throw new RuntimeException("No alert present to dismiss.", e);
        }
    }

    public static String getAlertText() {
        try {
            return getAlert().getText();
        } catch (NoAlertPresentException e) {
            throw new RuntimeException("No alert present to get text from.", e);
        }
    }

    public static void sendTextToAlert(String text) {
        try {
            getAlert().sendKeys(text);
        } catch (NoAlertPresentException e) {
            throw new RuntimeException("No prompt alert present to send keys to.", e);
        }
    }
}

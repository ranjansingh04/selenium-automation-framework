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

import com.ranjansingh.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public final class WaitUtils {

    private static final Duration TIMEOUT = Duration.ofSeconds(5);

    private WaitUtils() {
        // Utility class — no instantiation
    }

    /**
     * Waits until the given element is visible on the page.
     */
    public static void waitForVisibility(WebElement element) {
        new WebDriverWait(DriverManager.getDriver(), TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the given element is clickable.
     */
    public static void waitForClickable(WebElement element) {
        new WebDriverWait(DriverManager.getDriver(), TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until an alert is present.
     */
    public static void waitForAlert() {
        new WebDriverWait(DriverManager.getDriver(), TIMEOUT)
                .until(ExpectedConditions.alertIsPresent());
    }
}

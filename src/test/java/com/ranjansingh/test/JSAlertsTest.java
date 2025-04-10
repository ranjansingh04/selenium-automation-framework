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
package com.ranjansingh.test;

import com.ranjansingh.BaseWeb;
import com.ranjansingh.page.herokuapp.JavaScriptAlertsPage;
import com.ranjansingh.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JSAlertsTest extends BaseWeb {

    @Test(description = "Handle JS Alert", retryAnalyzer = RetryAnalyzer.class)
    public void testHandleJSAlert() {

        var jsAlertPage = new JavaScriptAlertsPage();
        jsAlertPage.open();
        jsAlertPage.clickJSAlert();
        jsAlertPage.acceptJSAlert();

        Assert.assertEquals(jsAlertPage.getResultMessage(), "You successfully clicked an alert");
    }

    @Test(description = "Handle JS Confirm - Cancel", retryAnalyzer = RetryAnalyzer.class)
    public void testHandleJSConfirmCancel() {

        var jsAlertPage = new JavaScriptAlertsPage();
        jsAlertPage.open();
        jsAlertPage.clickJSConfirm();
        jsAlertPage.dismissJSAlert();

        Assert.assertEquals(jsAlertPage.getResultMessage(), "You clicked: Cancel");
    }

    @Test(description = "Handle JS Confirm - OK", retryAnalyzer = RetryAnalyzer.class)
    public void testHandleJSConfirmOK() {

        var jsAlertPage = new JavaScriptAlertsPage();
        jsAlertPage.open();
        jsAlertPage.clickJSConfirm();
        jsAlertPage.acceptJSAlert();

        Assert.assertEquals(jsAlertPage.getResultMessage(), "You clicked: Ok");
    }

    @Test(description = "Handle JS Prompt", retryAnalyzer = RetryAnalyzer.class)
    public void testHandleJSPrompt() {

        var jsAlertPage = new JavaScriptAlertsPage();
        jsAlertPage.open();
        jsAlertPage.clickJSPrompt();
        jsAlertPage.sendTextToJSAlert("Ranjan");
        jsAlertPage.acceptJSAlert();

        Assert.assertEquals(jsAlertPage.getResultMessage(), "You entered: Ranjan");
    }
}

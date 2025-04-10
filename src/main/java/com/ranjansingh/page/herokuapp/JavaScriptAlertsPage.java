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

package com.ranjansingh.page.herokuapp;

import com.ranjansingh.page.AbstractPageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JavaScriptAlertsPage extends AbstractPageObject {

    private static final String PATH = "/javascript_alerts";

    @FindBy(id = "result")
    private WebElement result;

    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    private WebElement jsAlertButton;

    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    private WebElement jsConfirmButton;

    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    private WebElement jsPromptButton;

    @Step
    public void open() {
        super.navigateTo(PATH);
    }

    @Step
    public void clickJSAlert() {
        click(jsAlertButton);
    }

    @Step
    public void acceptJSAlert() {
        acceptAlert();
    }

    @Step
    public void clickJSConfirm() {
        click(jsConfirmButton);
    }

    @Step
    public void dismissJSAlert() {
        dismissAlert();
    }

    @Step
    public void clickJSPrompt() {
        click(jsPromptButton);
    }

    @Step
    public void sendTextToJSAlert(String text) {
        sendKeysToAlert(text);
    }

    @Step
    public String getResultMessage() {
        return result.getText();
    }

}

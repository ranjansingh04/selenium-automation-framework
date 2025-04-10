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
import com.ranjansingh.data.LoginTestData;
import com.ranjansingh.page.herokuapp.LoginPage;
import com.ranjansingh.utils.RetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ranjansingh.config.ConfigurationManager.configuration;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseWeb {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {
                        new LoginTestData(configuration().username(), configuration().password(),
                                "You logged into a secure area!", true, "TC001", "positive")
                },
                {
                        new LoginTestData("invalid", "wrong",
                                "Your username is invalid!", false, "TC002", "negative")
                }
        };
    }

    @Test(dataProvider = "loginData", retryAnalyzer = RetryAnalyzer.class)
    @Description("Tests login functionality with valid/invalid credentials and verifies expected message")
    public void testLogin(LoginTestData data) {

        var loginPage = new LoginPage();
        loginPage.open();
        loginPage.fillUsername(data.getUsername());
        loginPage.fillPassword(data.getPassword());
        loginPage.clickLogin();

        String actualMessage = loginPage.getMessage();

        assertThat(actualMessage).contains(data.getExpectedMessage());
    }
}

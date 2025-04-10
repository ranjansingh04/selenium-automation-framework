/*
 * MIT License
 *
 * Copyright (c) 2021 Elias Nogueira
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

package com.ranjansingh.driver;

import com.ranjansingh.exceptions.HeadlessNotSupportedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

import static com.ranjansingh.config.ConfigurationManager.configuration;
import static com.ranjansingh.data.changeless.BrowserData.CHROME_HEADLESS;
import static com.ranjansingh.data.changeless.BrowserData.DISABLE_INFOBARS;
import static com.ranjansingh.data.changeless.BrowserData.DISABLE_NOTIFICATIONS;
import static com.ranjansingh.data.changeless.BrowserData.GENERIC_HEADLESS;
import static com.ranjansingh.data.changeless.BrowserData.REMOTE_ALLOW_ORIGINS;
import static com.ranjansingh.data.changeless.BrowserData.START_MAXIMIZED;
import static java.lang.Boolean.TRUE;

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createLocalDriver() {
            return new ChromeDriver(getOptions());
        }

        @Override
        public WebDriver createTestContainerDriver() {
            BrowserWebDriverContainer<?> driverContainer = new BrowserWebDriverContainer<>().withCapabilities(new ChromeOptions());
            driverContainer.start();

            return new RemoteWebDriver(driverContainer.getSeleniumAddress(), new ChromeOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            var chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(START_MAXIMIZED);
            chromeOptions.addArguments(DISABLE_INFOBARS);
            chromeOptions.addArguments(DISABLE_NOTIFICATIONS);
            chromeOptions.addArguments(REMOTE_ALLOW_ORIGINS);

            if (configuration().headless()) chromeOptions.addArguments(CHROME_HEADLESS);

            return chromeOptions;
        }
    }, FIREFOX {
        @Override
        public WebDriver createLocalDriver() {
            return new FirefoxDriver(getOptions());
        }

        @Override
        public WebDriver createTestContainerDriver() {
            BrowserWebDriverContainer<?> driverContainer = new BrowserWebDriverContainer<>().withCapabilities(new FirefoxOptions());
            driverContainer.start();

            return new RemoteWebDriver(driverContainer.getSeleniumAddress(), new FirefoxOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            var firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(START_MAXIMIZED);

            if (configuration().headless()) firefoxOptions.addArguments(GENERIC_HEADLESS);

            return firefoxOptions;
        }
    }, EDGE {
        @Override
        public WebDriver createLocalDriver() {
            return new EdgeDriver(getOptions());
        }

        public WebDriver createTestContainerDriver() {
            BrowserWebDriverContainer<?> driverContainer = new BrowserWebDriverContainer<>().withCapabilities(new EdgeOptions());
            driverContainer.start();

            return new RemoteWebDriver(driverContainer.getSeleniumAddress(), new EdgeOptions());
        }

        @Override
        public EdgeOptions getOptions() {
            var edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(START_MAXIMIZED);

            if (configuration().headless()) edgeOptions.addArguments(GENERIC_HEADLESS);

            return edgeOptions;
        }
    }, SAFARI {
        @Override
        public WebDriver createLocalDriver() {
            return new SafariDriver(getOptions());
        }

        public WebDriver createTestContainerDriver() {
            throw new IllegalArgumentException("Browser Safari not supported on TestContainers yet");
        }

        @Override
        public SafariOptions getOptions() {
            var safariOptions = new SafariOptions();
            safariOptions.setAutomaticInspection(false);

            if (TRUE.equals(configuration().headless()))
                throw new HeadlessNotSupportedException(safariOptions.getBrowserName());

            return safariOptions;
        }
    };

    /**
     * Used to run local tests where the WebDriverManager will take care of the driver
     *
     * @return a new WebDriver instance based on the browser set
     */
    public abstract WebDriver createLocalDriver();

    /**
     * @return a new AbstractDriverOptions instance based on the browser set
     */
    public abstract AbstractDriverOptions<?> getOptions();

    /**
     * Used to run the remote test execution using Testcontainers
     *
     * @return a new WebDriver instance based on the browser set
     */
    public abstract WebDriver createTestContainerDriver();
}

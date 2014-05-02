/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.test;

import com.thoughtworks.selenium.Wait;
import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class HighchartsOneRequestTest extends SeleniumTestCase {

    @Test
    public void testJSFile() throws Exception {
        open("/HighchartsOneRequest");

        //Thread.sleep(600000);

        new Wait() {

            @Override
            public boolean until() {
                return isElementPresent("//head/script[contains(@src,'highcharts.src.js')]");
            }
        }.wait("The Highcharts JavaScript file is missing.", 50000l);
    }
}

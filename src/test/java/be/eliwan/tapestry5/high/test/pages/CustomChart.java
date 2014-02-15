/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.test.pages;

import org.apache.tapestry5.json.JSONObject;

/**
 * Pages using a customized version of the the Highcharts component. Custom component adds the data to display.
 */
public class CustomChart {

    public JSONObject getOptions() {
        return new JSONObject("subtitle", new JSONObject(
                "text", "Subtitle added in page",
                "x", -20
        ));
    }

}

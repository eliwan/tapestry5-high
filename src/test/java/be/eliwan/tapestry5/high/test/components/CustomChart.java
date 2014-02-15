/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.test.components;

import be.eliwan.tapestry5.high.components.Highcharts;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

public class CustomChart extends Highcharts {

    @Override
    public JSONObject getComponentOptions() {
        JSONObject base = super.getComponentOptions();

        base.put("title", new JSONObject("text", "Stock evolution"));
        ((JSONObject) base.get("chart")).put("type", "bar");

        JSONObject xAxis = new JSONObject();
        xAxis.put("categories", new JSONArray("Apples", "Bananas", "Oranges"));
        base.put("xAxis", xAxis);

        JSONObject yAxis = new JSONObject();
        JSONObject yAxisTitle = new JSONObject();
        yAxisTitle.put("text", "Fruit Eaten");
        yAxis.put("title", yAxisTitle);
        base.put("yAxis", yAxis);

        JSONObject jane = new JSONObject(
                "name", "Adam",
                "data", new JSONArray(1, 0, 4)
        );
        JSONObject john = new JSONObject(
                "name", "Eve",
                "data", new JSONArray(5, 7, 3)
        );

        base.put("series", new JSONArray(jane, john));

        return base;
    }

}

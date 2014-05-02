/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.test.pages;

import be.eliwan.tapestry5.high.High;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

public class Highcharts {

    public JSONObject getOptions() {
        JSONObject high = new JSONObject(
                "title", new JSONObject("text", "Stock evolution"),
                "chart", new JSONObject("type", "bar")
        );

        JSONObject xAxis = new JSONObject();
        xAxis.put("categories", new JSONArray("Apples", "Bananas", "Oranges"));
        high.put("xAxis", xAxis);

        JSONObject yAxis = new JSONObject();
        JSONObject yAxisTitle = new JSONObject();
        yAxisTitle.put("text", "Fruit Eaten");
        yAxis.put("title", yAxisTitle);
        high.put("yAxis", yAxis);

        JSONObject jane = new JSONObject(
                "name", "Jane",
                "data", new JSONArray(1, 0, 4)
        );
        JSONObject john = new JSONObject(
                "name", "John",
                "data", new JSONArray(5, 7, 3)
        );

        high.put("series", new JSONArray(jane, john));

        return high;
    }
    
    @OnEvent(High.CHART_OPTIONS_EVENT)
    public JSONObject getChartOptionsThroughAjax() {

        JSONObject high = new JSONObject(
                "title", new JSONObject("text", "AJAX Stock evolution"),
                "chart", new JSONObject("type", "bar")
        );

        JSONObject xAxis = new JSONObject();
        xAxis.put("categories", new JSONArray("AJAX Apples", "AJAX Bananas", "AJAX Oranges"));
        high.put("xAxis", xAxis);

        JSONObject yAxis = new JSONObject();
        JSONObject yAxisTitle = new JSONObject();
        yAxisTitle.put("text", "AJAX Fruit Eaten");
        yAxis.put("title", yAxisTitle);
        high.put("yAxis", yAxis);

        JSONObject jane = new JSONObject(
                "name", "AJAX Jane",
                "data", new JSONArray(1, 0, 4)
        );
        JSONObject john = new JSONObject(
                "name", "AJAX John",
                "data", new JSONArray(5, 7, 3)
        );

        high.put("series", new JSONArray(jane, john));

        return high;
        
    }

}

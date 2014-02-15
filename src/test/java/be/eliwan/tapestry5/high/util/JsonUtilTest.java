/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.util;

import org.apache.tapestry5.json.JSONObject;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test for Highcharts. Verify merging behaviour.
 */
public class JsonUtilTest {

    @Test
    public void testMerge() throws Exception {

        assertThat(JsonUtil.merge(null, new JSONObject())).isNull();
        assertThat(JsonUtil.merge(new JSONObject("key", "value"), null)).isEqualTo(new JSONObject("key", "value"));

        assertThat(JsonUtil.merge(
                new JSONObject(
                        "some", "data",
                        "already", new JSONObject(
                        "Joske", "Vermeulen",
                        "Pietje", "Puk"),
                        "merged", new JSONObject(
                        "one", "one",
                        "three", "three")
                ),
                new JSONObject(
                        "other", "data",
                        "merged", new JSONObject("two", "two"),
                        "new", new JSONObject("new", "Kids on the block")
                )
        )).isEqualTo(new JSONObject(
                "some", "data",
                "other", "data",
                "already", new JSONObject(
                "Joske", "Vermeulen",
                "Pietje", "Puk"),
                "merged", new JSONObject(
                "one", "one",
                "two", "two",
                "three", "three"),
                "new", new JSONObject("new", "Kids on the block")
        ));

    }

}

/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.util;

import org.apache.tapestry5.json.JSONObject;

/**
 * Utility methods for handling JSON objects.
 */
public final class JsonUtil {

    private JsonUtil() {
        // hide constructor
    }

    /**
     * Merge mergeValues into base. When keys exist in both, the value from mergeValues is taken.
     *
     * @param base base object to merge values into
     * @param mergeValues values to merge
     * @return null if base is null. Else return base merged with mergeValues
     */
    public static JSONObject merge(JSONObject base, JSONObject mergeValues) {
        if (base == null) {
            return null;
        }

        if (mergeValues == null) {
            return base;
        }

        for (String key : mergeValues.keys()) {
            if (base.keys().contains(key) &&  base.get(key) instanceof JSONObject &&
                    mergeValues.get(key) instanceof JSONObject) {
                base.put(key, merge((JSONObject) base.get(key), (JSONObject) mergeValues.get(key)));
            } else {
                base.put(key, mergeValues.get(key));
            }
        }
        return base;
    }
}

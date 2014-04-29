/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high;

import be.eliwan.tapestry5.high.components.Highcharts;
import be.eliwan.tapestry5.high.components.Highstock;

/**
 * Constants for the module.
 */
public interface High {

    /*** Core path. */
    String CORE_PATH = "high.core.path";
    
    /**
     * Event triggered by {@link Highcharts} and {@link Highstock}
     */
    String CHART_OPTIONS_EVENT = "chartOptions";
    
}

/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.components;

import be.eliwan.tapestry5.high.services.HighstockStack;
import be.eliwan.tapestry5.high.util.JsonUtil;
import lombok.Getter;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.javascript.ModuleConfigurationCallback;

/**
 * Component which displays a Highstock object. Use options to set the chart options.
 */
@Import(stack = HighstockStack.STACK_ID)
public class Highstock implements ClientElement {

    @Getter
    private String clientId;

    @Parameter
    @SuppressWarnings("unused")
    private JSONObject options;

    @Inject
    private JavaScriptSupport javascript;

    @Inject
    private ComponentResources resources;
    
    @Inject
    private HighstockStack highstockStack;
    
    /**
     * Add div in the page for the component.
     *
     * @param writer markup writer
     */
    @SetupRender
    public void addDiv(MarkupWriter writer) {
        clientId = javascript.allocateClientId(resources);
        writer.element("div", "id", clientId);
    }

    /**
     * Inject the necessary JavaScript to render the chart in the page.
     *
     * @param writer markup writer
     */
    @AfterRender
    public void setJS(MarkupWriter writer) {
        resources.renderInformalParameters(writer);
        writer.end();

        JSONObject opt = new JSONObject();
        opt.put("id", clientId);

        JSONObject params = getComponentOptions();

        JsonUtil.merge(params, options);

        opt.put("opt", params);
        
        javascript.addModuleConfigurationCallback(new ModuleConfigurationCallback() {
            @Override
            public JSONObject configure(JSONObject configuration) {
                // see http://stackoverflow.com/questions/8186027/loading-highcharts-with-require-js
                final JSONArray highchartsShim = new JSONArray();
                highchartsShim.put(new JSONObject("exports", "Highcharts"));
                highchartsShim.put(new JSONObject("deps", new JSONArray().put("jquery")));
                configuration.in("shim").put("highcharts", highchartsShim);
                // this supposes the highstock stack only has one javascript library
                configuration.in("paths").put("highstock", highstockStack.getJavaScriptLibraries().get(0).toClientURL());
                return configuration;
            }
        });

        javascript.require("high/highstock").with(opt);
        
    }

    /**
     * Get component options.
     * These are the base component options. This can be overwritten/extended for more specific chart components.
     *
     * @return component options
     */
    public JSONObject getComponentOptions() {
        return new JSONObject("chart", new JSONObject("renderTo", getClientId()));
    }
    
}

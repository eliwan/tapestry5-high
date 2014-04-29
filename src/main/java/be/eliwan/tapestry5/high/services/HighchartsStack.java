/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.services;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Mapper;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * JavaScript stack for Highcharts.
 */
public class HighchartsStack implements JavaScriptStack {

    /**
     * Stack id.
     */
    public static final String STACK_ID = "highchartsStack";

    private final boolean productionMode;
    
    private final List<String> modules = Arrays.asList("high/highcharts");

    private final List<Asset> javaScriptStack;

    private final List<StylesheetLink> stylesheetStack;

    /**
     * Build the JavaScript stack.
     *
     * @param productionMode is it production mode?
     * @param assetSource asset source
     */
    public HighchartsStack(@Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode,
            final AssetSource assetSource) {
        super();
        this.productionMode = productionMode;

        final Mapper<String, Asset> pathToAsset = new Mapper<String, Asset>() {

            public Asset map(String path) {
                return assetSource.getExpandedAsset(path);
            }
        };


        stylesheetStack = CollectionFactory.newList();

        if (productionMode) {
            javaScriptStack = F
                    .flow("${high.core.path}/highcharts/highcharts.js")
                    .map(pathToAsset).toList();
        } else {
            javaScriptStack = F
                    .flow("${high.core.path}/highcharts/highcharts.src.js")
                    .map(pathToAsset).toList();
        }

    }

    @Override
    public String getInitialization() {
        return productionMode ? null : "Tapestry.DEBUG_ENABLED = true;";
    }

    @Override
    public List<Asset> getJavaScriptLibraries() {
        return javaScriptStack;
    }

    @Override
    public List<StylesheetLink> getStylesheets() {
        return stylesheetStack;
    }

    @Override
    public List<String> getStacks() {
        return Collections.emptyList();
    }

    @Override
    public List<String> getModules() {
        return modules;
    }
}

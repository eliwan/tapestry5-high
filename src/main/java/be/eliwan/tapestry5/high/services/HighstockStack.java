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
import org.apache.tapestry5.services.javascript.JavaScriptAggregationStrategy;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * JavaScript stack for Highstock.
 */
public class HighstockStack implements JavaScriptStack {

    /**
     * Stack id.
     */
    public static final String STACK_ID = "highstockStack";

    private final boolean productionMode;

    private final List<String> modules = Arrays.asList("high/highstock");

    private final List<Asset> javaScriptStack;

    private final List<StylesheetLink> stylesheetStack;

    /**
     * Build the JavaScript stack.
     *
     * @param productionMode is it production mode?
     * @param assetSource asset source
     */
    public HighstockStack(@Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode,
            final AssetSource assetSource) {
        super();
        this.productionMode = productionMode;

        final Mapper<String, Asset> pathToAsset = new Mapper<String, Asset>() {

            @Override
			public Asset map(String path) {
                return assetSource.getExpandedAsset(path);
            }
        };


        stylesheetStack = CollectionFactory.newList();

        if (productionMode) {
            javaScriptStack = F
                    .flow("${high.core.path}/highstock/highstock.js")
                    .map(pathToAsset).toList();
        } else {
            javaScriptStack = F
                    .flow("${high.core.path}/highstock/highstock.src.js")
                    .map(pathToAsset).toList();
        }

    }

    @Override
    public String getInitialization() {
        return null;
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

	@Override
	public JavaScriptAggregationStrategy getJavaScriptAggregationStrategy()
	{
		return JavaScriptAggregationStrategy.COMBINE_AND_MINIMIZE;
	}
}

/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package be.eliwan.tapestry5.high.services;

import be.eliwan.tapestry5.high.High;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.javascript.JavaScriptStack;

/**
 * Module definition.
 */
public final class HighModule {

    private HighModule() {
        // hide constructor
    }

    /**
     * Bind JavaScript stacks.
     *
     * @param binder service binder
     */
    public static void bind(ServiceBinder binder) {
        binder.bind(HighstockStack.class);
        binder.bind(HighchartsStack.class);
    }

    /**
     * Contribute JavaScript stacks.
     *
     * @param highchartsStack highcharts stack
     * @param highstockStack highstock stack
     * @param configuration configuration to extend
     */
    @SuppressWarnings("unused")
    public static void contributeJavaScriptStackSource(MappedConfiguration<String, JavaScriptStack> configuration,
            HighchartsStack highchartsStack, HighstockStack highstockStack) {
        configuration.add(HighchartsStack.STACK_ID, highchartsStack);
        configuration.add(HighstockStack.STACK_ID, highstockStack);
    }

    /**
     * Contribute component class resolver.
     *
     * @param configuration configuration to extend
     */
    @SuppressWarnings("unused")
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("high", "be.eliwan.tapestry5.high"));
    }

    /**
     * Contribute factory defaults.
     *
     * @param configuration configuration to extend
     */
    @SuppressWarnings("unused")
    public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration) {
                configuration.add(High.CORE_PATH, "classpath:be/eliwan/tapestry5/high/");

    }
}

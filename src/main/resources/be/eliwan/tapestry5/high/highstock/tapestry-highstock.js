/*
 * This file is part of tapestry5-high, a Tapestry module for Highcharts and Highstock integration.
 * Copyright (c) Eliwan bvba, Belgium, http://eliwan.be
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

(function( $ ) {
	
	T5.extendInitializers(function(){
		
		function highstock(spec){
			var params = {};
			var extra = $("#" + spec.id).data('highstock');
			if(extra) {
				params = extra;
			}
			
			$.extend(true, params, spec.opt);
			
			chart = new Highcharts.StockChart(params);
			
			 $("#" + spec.id).removeData('highstock');
		}
		
		return {
            highstock : highstock
		}
		
	});
}) ( jQuery );
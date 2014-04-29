define(["jquery", "t5/core/console"], function($, console) {
	return function(spec) {
		var params = {};
		var extra = $("#" + spec.id).data('highstock');
		if(extra) {
			params = extra;
		}
		$.extend(true, params, spec.opt);
		chart = new Highcharts.StockChart(params);
		$("#" + spec.id).removeData('highstock');
	};
});
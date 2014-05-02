define(["jquery", "t5/core/console"], function($, console) {
	return function(spec) {
        var params = {};
        var extra = $("#" + spec.id).data('highstock');
        if (extra) {
            params = extra;
        }
        if (spec.eventUrl) {
            $.ajax({ url: spec.eventUrl}).done(function (data) {
                setup($.extend(true,{}, spec.opt, data));
            });
        }
        else {
            setup(spec.opt);
        }

        function setup(data) {
            $.extend(true, params, data);
            chart = new Highcharts.StockChart(params);
            $("#" + spec.id).removeData('highstock');
        }
	};
});
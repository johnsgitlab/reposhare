$(function () {

    var divelem = document.getElementById("historyChart");
    var symbol  = divelem.getAttribute("data-symbol");
    var aUrl    = "/rest/etf/averages?symbol=" + symbol;

    $.ajax({
        type: "GET",
        url: aUrl, 
        dataType: "json",
        success: renderChart,
        error: function (xhr, textStatus, errorThrown) {
            console.log(errorThrown + ". Loading did not work");
        }
    });
    
    function renderChart(data) {

        $("#historyChart").igDataChart({
            dataSource: data,
            width: "960px",
            height: "600px",
            title: "Historical Prices",
            subtitle: "Data: EOD",
            horizontalZoomable: true,
            syncChannel: "channel1",
            rightMargin: 30,
            legend: { element: "historyLegend" },
            axes: [{
                type: "categoryX",
                name: "xAxis",
                label: "date",
                labelExtent: 60
            }, {
                type: "numericY",
                name: "yAxis",
                title: "Price",
                labelExtent: 60
            }],
            series: [{
                name: "equity",
                type: "line",
                xAxis: "xAxis",
                yAxis: "yAxis",
		valueMemberPath: "close",

                markerType: "automatic",
                showTooltip: true,
                isTransitionInEnabled: true,
                isHighlightingEnabled: true,
                title: "close",
               
                transitionInDuration: 250,
                title: "Price",
                resolution: 8
            }, 
            {
                name: "kaufman",
                type: "line",
                xAxis: "xAxis",
                yAxis: "yAxis",
		valueMemberPath: "kaufman",

                markerType: "automatic",
                showTooltip: true,
                isTransitionInEnabled: true,
                isHighlightingEnabled: true,
                title: "close",
               
                transitionInDuration: 250,
                title: "kaufman",
                resolution: 8
            }, 
            {
                name: "fractal",
                type: "line",
                xAxis: "xAxis",
                yAxis: "yAxis",
		valueMemberPath: "fractal",

                markerType: "automatic",
                showTooltip: true,
                isTransitionInEnabled: true,
                isHighlightingEnabled: true,
                title: "close",
               
                transitionInDuration: 250,
                title: "fractal",
                resolution: 8
            },
            {
                name: "simple10",
                type: "line",
                xAxis: "xAxis",
                yAxis: "yAxis",
		valueMemberPath: "simple10",

                markerType: "automatic",
                showTooltip: true,
                isTransitionInEnabled: true,
                isHighlightingEnabled: true,
                title: "close",
               
                transitionInDuration: 250,
                title: "simple10",
                resolution: 8
            },
            {
                name: "simple20",
                type: "line",
                xAxis: "xAxis",
                yAxis: "yAxis",
		valueMemberPath: "simple20",

                markerType: "automatic",
                showTooltip: true,
                isTransitionInEnabled: true,
                isHighlightingEnabled: true,
                title: "close",
               
                transitionInDuration: 250,
                title: "simple20",
                resolution: 8
            },
            {
                name: "simple50",
                type: "line",
                xAxis: "xAxis",
                yAxis: "yAxis",
		valueMemberPath: "simple50",

                markerType: "automatic",
                showTooltip: true,
                isTransitionInEnabled: true,
                isHighlightingEnabled: true,
                title: "close",
               
                transitionInDuration: 250,
                title: "simple50",
                resolution: 8
            },
            {
                name: "itemToolTips",
                type: "itemToolTipLayer",
                useInterpolation: true,
                transitionDuration: 250
            }
            ]
        });

        $("#historyVolumeChart").igDataChart({
            dataSource: data,
            width: "960px",
            height: "150px",
            horizontalZoomable: true,
            syncChannel: "channel1",
            rightMargin: 30,
            legend: { element: "historyLegend" },
            axes: [{
                type: "categoryX",
                name: "xAxis",
                label: "date",
                labelExtent: 55,
                labelVisibility: "collapsed"
            }, {
                type: "numericY",
                name: "yAxis",
                title: "volume",
                labelExtent: 60,
                formatLabel: function (v) {
                    if (v > 1000000) {
                        v /= 1000000;
                        return v + "M";
                    }
                    if (v > 1000) {
                        v /= 1000;
                        return v + "K";
                    }

                    return v.toString();
                }
            }],
            series: [
            {
                name: "equityVolume",
                type: "area",
                xAxis: "xAxis",
                yAxis: "yAxis",
                brush: "#7C932F",
                outline: "#556420",
                valueMemberPath: "volume",
                showTooltip: true,
                isTransitionInEnabled: true,
                isHighlightingEnabled: true,
                transitionInDuration: 250,
                title: "Volume",
            }, 

            {
                name: "itemToolTips",
                type: "itemToolTipLayer",
                useInterpolation: true,
                transitionDuration: 250
            }]
        });

        $("#historyZoom").igZoombar({
            width: "920px",
            target: "#historyChart",
            zoomWindowMinWidth: 1.2,
            defaultZoomWindow: {
                left: 60,
                width: 40
            }
        });
        $("#historyZoom").css("margin-left", "44px");
        $("#historyZoom").igZoombar("clone").igDataChart({
            subtitle: null,
            rightMargin: 0,
            axes: [{ name: "xAxis", interval: NaN }],
            series: [{
                name: "equity", remove: true
            }, {
                name: "close",
                type: "area",
                xAxis: "xAxis",
                yAxis: "yAxis",
                valueMemberPath: "close"
            }]
        });

    }
});

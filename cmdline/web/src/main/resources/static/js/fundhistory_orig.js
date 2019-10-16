$(function () {

    var divelem = document.getElementById("historyChart");
    var symbol  = divelem.getAttribute("data-symbol");
    var aUrl    = "/rest/mf/values?symbol=" + symbol;

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
               
                transitionInDuration: 1000,
                title: "Price",
                resolution: 8
            }, {
                name: "itemToolTips",
                type: "itemToolTipLayer",
                useInterpolation: false,
                transitionDuration: 300
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

$(function () {

    var divelem = document.getElementById("historyChart");
    var symbol  = divelem.getAttribute("data-symbol");
    var aUrl    = "/rest/etf/valuesandavgs?symbol=" + symbol;

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
       
        title: "Historical Prices",
        subtitle: "Data: EOD",
        legend: { element: "historyLegend" },

        // width: "960px",
        // height: "600px",

        // width: "100%",
        width: "80%",
        height: "350px",
 
        leftMargin: 30,
        rightMargin: 30,

        windowRectMinWidth: 0.05,

        // windowResponse: "deferred",
        horizontalZoomable: true,

        syncChannel: "channel1",
        synchronizeVertically: false,
        syncrhonizeHorizontally: true,

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
            type: "financial",
            //brush: "green",
            //negativeBrush: "red",
            displayType: "candlestick",
            closeMemberPath: "close",
            highMemberPath: "high",
            lowMemberPath: "low",
            openMemberPath: "open",
            showTooltip: true,
            //tooltipTemplate: "tooltipTemplate",
            outline: "#000",
            xAxis: "xAxis",
            yAxis: "yAxis",
            name: "stockSeries",
            title: "Price Data",
            trendLineType: "simpleAverage",
            trendLinePeriod: 50
        },{
                name: "itemToolTips",
                type: "itemToolTipLayer",
                useInterpolation: true,
                transitionDuration: 250
            }]
        });


            $("#indicatorChart").igDataChart({
                            width: "80%",
            height: "300px",
 
            leftMargin: 30,
            rightMargin: 30,

            windowRectMinWidth: 0.05,

            // windowResponse: "deferred",
            horizontalZoomable: true,

            syncChannel: "channel1",
            synchronizeVertically: false,
            syncrhonizeHorizontally: true,

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
                    title: "Value"
                }],
                series: [{
                    type: "moneyFlowIndexIndicator",
                    isTransitionInEnabled: true,
                    closeMemberPath: "close",
                    highMemberPath: "high",
                    lowMemberPath: "low",
                    openMemberPath: "open",
                    volumeMemberPath: "volume",
                    xAxis: "xAxis",
                    yAxis: "yAxis",
                    name: "indicatorSeries",
                    showTooltip: true,
                    isTransitionInEnabled: true,
                    isHighlightingEnabled: true,
                    transitionInDuration: 250,
                    title: "Financial Indicator Data"
                }, 
                {
                    name: "itemToolTips",
                    type: "itemToolTipLayer",
                    useInterpolation: true,
                    transitionDuration: 250
                }]
            });

        $("#historyVolumeChart").igDataChart({
            dataSource: data,

            //width: "960px",
            //height: "150px",
            width: "80%",
            height: "150px",
 
            leftMargin: 30,
            rightMargin: 30,

            windowRectMinWidth: 0.05,

            // windowResponse: "deferred",
            horizontalZoomable: true,

            syncChannel: "channel1",
            synchronizeVertically: false,
            syncrhonizeHorizontally: true,

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

            // width: "920px",
            width: "76%",

            leftMargin: 30,
            rightMargin: 30,

            windowRectMinWidth: 0.05,

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




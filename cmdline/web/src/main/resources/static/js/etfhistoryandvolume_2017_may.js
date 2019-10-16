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
                name: "simple200",
                type: "line",
                xAxis: "xAxis",
                yAxis: "yAxis",
		valueMemberPath: "simple200",

                markerType: "automatic",
                showTooltip: true,
                isTransitionInEnabled: true,
                isHighlightingEnabled: true,
                title: "close",
               
                transitionInDuration: 250,
                title: "simple200",
                resolution: 8
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

            title: "rateOfChangeAndMomentumIndicator",
            //subtitle: "Sub Title",
            //legend: { element: "indicatorLegend" },

            width: "80%",
            height: "350px",
 
            leftMargin: 30,
            rightMargin: 30,

            windowRectMinWidth: 0.05,
            
            horizontalZoomable: true,

            syncChannel: "channel1",
            synchronizeVertically: false,
            syncrhonizeHorizontally: true,

            axes: [{
                type: "categoryX",
                name: "xAxis",
                label: "date",
                labelExtent: 60,
                localVisibility: "collapsed"
            }, {
                type: "numericY",
                name: "yAxis",
                title: "Value",
                labelExtent: 60,
                localVisibility: "collapsed"
            }],
            series: [{
                    name: "indicatorSeries",
                    type: "rateOfChangeAndMomentumIndicator",

                    //type: "absoluteVolumeOscillatorIndicator",
                    //type: "averageTrueRangeIndicator",
                    //type: "accumulationDistributionIndicator",
                    //type: "averageDirectionalIndexIndicator",
                    //type: "bollingerBandsOverlay",
                    //type: "bollingerBandWidthIndicator",
                    //type: "chaikinOscillatorIndicator",
                    //type: "chaikinVolatilityIndicator",
                    //type: "commodityChannelIndexIndicator",
                    //type: "detrendedPriceOscillatorIndicator",
                    //type: "easeOfMovementIndicator",
                    //type: "fastStochasticOscillatorIndicator",
                    //type: "forceIndexIndicator",
                    //type: "fullStochasticOscillatorIndicator",
                    //type: "marketFacilitationIndexIndicator",
                    //type: "massIndexIndicator",
                    //type: "medianPriceIndicator",
                    //type: "moneyFlowIndexIndicator",
                    //type: "movingAverageConvergenceDivergenceIndicator",
                    //type: "negativeVolumeIndexIndicator",
                    //type: "onBalanceVolumeIndicator",
                    //type: "percentagePriceOscillatorIndicator",
                    //type: "percentageVolumeOscillatorIndicator",
                    //type: "positiveVolumeIndexIndicator",
                    //type: "priceChannelOverlay",
                    //type: "priceVolumeTrendIndicator",
                    //type: "rateOfChangeAndMomentumIndicator",
                    //type: "relativeStrengthIndexIndicator",
                    //type: "slowStochasticOscillatorIndicator",
                    //type: "standardDeviationIndicator",
                    //type: "stochRSIIndicator",
                    //type: "trixIndicator",
                    //type: "typicalPriceIndicator",
                    //type: "ultimateOscillatorIndicator",
                    //type: "weightedCloseIndicator",
                    //type: "williamsPercentRIndicator",

                    xAxis: "xAxis",
                    yAxis: "yAxis",

                    closeMemberPath: "close",
                    highMemberPath: "high",
                    lowMemberPath: "low",
                    openMemberPath: "open",
                    volumeMemberPath: "volume",
                    
                    //markerType: "automatic",
                    showTooltip: true,
                    isTransitionInEnabled: true,
                    isHighlightingEnabled: true,
                    
                    title: "Financial Indicator Data",
                    //transitionInDuration: 250,
                    //resolution: 8
                }, 
            {
                name: "itemToolTips",
                type: "itemToolTipLayer",
                useInterpolation: true,
                transitionInDuration: 250,
            }, 
            {
                        name: "crosshairLayer",
                        title: "crosshair",
                        type: "crosshairLayer",
                        useInterpolation: false,
                        transitionDuration: 500
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




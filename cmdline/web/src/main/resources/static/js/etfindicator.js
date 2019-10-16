$(function () {

    var divelem = document.getElementById("historyChart");
    var symbol  = divelem.getAttribute("data-symbol");
    var aUrl    = "/rest/etf/values?symbol=" + symbol;

    $.ajax({
        type: "GET",
        url: aUrl, 
        dataType: "json",
        success: renderIndicatorChart,
        error: function (xhr, textStatus, errorThrown) {
            console.log(errorThrown + ". Loading did not work");
        }
    });
    
    function renderIndicatorChart(data) {

        $("#indicatorChart").igDataChart({
            dataSource: data,

            title: "Title",
            subtitle: "Sub Title",
            legend: { element: "indicatorLegend" },

            width: "80%",
            height: "350px",
 
            leftMargin: 30,
            rightMargin: 30,

            windowRectMinWidth: 0.05,
            
            horizontalZoomable: true,
            
            axes: [{
                type: "categoryX",
                name: "xAxis",
                label: "date",
                labelExtent: 60
            }, {
                type: "numericY",
                name: "yAxis",
                title: "Value",
                labelExtent: 60
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

        $("#indicatorZoom").igZoombar({
            width: "76%",

            leftMargin: 30,
            rightMargin: 30,

            windowRectMinWidth: 0.05,

            target: "#indicatorChart",
            zoomWindowMinWidth: 1.2,

            defaultZoomWindow: {
                left: 60,
                width: 40
            }
        });
        $("#indicatorZoom").css("margin-left", "44px");
        $("#indicatorZoom").igZoombar("clone").igDataChart({
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

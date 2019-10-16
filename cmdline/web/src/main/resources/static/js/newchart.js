$(function () {

    var divelem = document.getElementById("indicatorChart");
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

var indicators = [
        { key: "absoluteVolumeOscillatorIndicator", text: "Absolute Volume Oscillator" },
        { key: "averageTrueRangeIndicator", text: "Average True Range" },
        { key: "accumulationDistributionIndicator", text: "Accumulation Distribution" },
        { key: "averageDirectionalIndexIndicator", text: "Average Directional Index" },
        { key: "bollingerBandsOverlay", text: "Bollinger Bands Overlay" },
        { key: "bollingerBandWidthIndicator", text: "Bollinger Bandwidth" },
        { key: "chaikinOscillatorIndicator", text: "Chaikin Oscillator" },
        { key: "chaikinVolatilityIndicator", text: "Chaikin Volatility" },
        { key: "commodityChannelIndexIndicator", text: "Commodity Channel Index" },
        { key: "detrendedPriceOscillatorIndicator", text: "Detrended Price Oscillator" },
        { key: "easeOfMovementIndicator", text: "Ease Of Movement" },
        { key: "fastStochasticOscillatorIndicator", text: "Fast Stochastic Oscillator" },
        { key: "forceIndexIndicator", text: "Force Index" },
        { key: "fullStochasticOscillatorIndicator", text: "Full Stochastic Oscillator" },
        { key: "marketFacilitationIndexIndicator", text: "Market Facilitation Index" },
        { key: "massIndexIndicator", text: "Mass Index" },
        { key: "medianPriceIndicator", text: "Median Price" },
        { key: "moneyFlowIndexIndicator", text: "Money Flow Index" },
        { key: "movingAverageConvergenceDivergenceIndicator", text: "Moving Average Convergence Divergence" },
        { key: "negativeVolumeIndexIndicator", text: "Negative Volume Index" },
        { key: "onBalanceVolumeIndicator", text: "On Balance Volume" },
        { key: "percentagePriceOscillatorIndicator", text: "Percentage Price Oscillator" },
        { key: "percentageVolumeOscillatorIndicator", text: "Percentage Volume Oscillator" },
        { key: "positiveVolumeIndexIndicator", text: "Positive Volume Index" },
        { key: "priceChannelOverlay", text: "Price Channel Overlay" },
        { key: "priceVolumeTrendIndicator", text: "Price Volume Trend" },
        { key: "rateOfChangeAndMomentumIndicator", text: "Rate Of Change And Momentum" },
        { key: "relativeStrengthIndexIndicator", text: "Relative Strength Index" },
        { key: "slowStochasticOscillatorIndicator", text: "Slow Stochastic Oscillator" },
        { key: "standardDeviationIndicator", text: "Standard Deviation" },
        { key: "stochRSIIndicator", text: "Stoch RSI" },
        { key: "trixIndicator", text: "Trix" },
        { key: "typicalPriceIndicator", text: "Typical Price" },
        { key: "ultimateOscillatorIndicator", text: "Ultimate Oscillator" },
        { key: "weightedCloseIndicator", text: "Weighted Close" },
        { key: "williamsPercentRIndicator", text: "Williams Percent R" }
    ];
        $("#indicatorChart").igDataChart({
        width: "960px",
        height: "600px",
        dataSource: data,
       
        title: "Financial Chart",
        subtitle: "Data: EOD",
      
        width: "80%",
        height: "350px",

        axes: [{
                type: "categoryX",
                    label: "Date",
                    name: "xAxis",
                    interval: 10,
                    title: "Date"
            }, {
                type: "numericY",
                    name: "yAxis",
                    title: "Price"
            }],
        series: [{
                    type: "moneyFlowIndexIndicator",
                    isTransitionInEnabled: true,
                    closeMemberPath: "Close",
                    highMemberPath: "High",
                    lowMemberPath: "Low",
                    openMemberPath: "Open",
                    volumeMemberPath: "Volume",
                    xAxis: "xAxis",
                    yAxis: "yAxis",
                    name: "indicatorSeries",
                    title: "Financial Indicator Data"
                }]
        });

        
        $.each(indicators, function (index, item) {
                $('#indicatorTypes').append($('<option></option>').val(item.key).html(item.text));
            });

            $("#indicatorTypes").change(function () {
                changeIndicator($(this).val());
            });

        });


        function changeIndicator(newIndicator) {
            $("#indicatorChart").igDataChart("option", "series", [{
                name: "indicatorSeries",
                remove: true
            }]);

            $("#indicatorChart").igDataChart("option", "series", [{
                type: newIndicator,
                name: "indicatorSeries",
                title: "Financial Indicator Data",
                xAxis: "xAxis",
                yAxis: "yAxis",
                closeMemberPath: "Close",
                highMemberPath: "High",
                lowMemberPath: "Low",
                openMemberPath: "Open",
                volumeMemberPath: "Volume"
            }]);
        };

    

    }
});



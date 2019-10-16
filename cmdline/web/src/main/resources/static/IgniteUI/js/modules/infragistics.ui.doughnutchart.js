﻿/*!@license
* Infragistics.Web.ClientUI Doughnut Chart localization resources 16.1.20161.1009
*
* Copyright (c) 2011-2016 Infragistics Inc.
*
* http://www.infragistics.com/
*
*/
(function($){$.ig=$.ig||{};if(!$.ig.igDoughnutChart){$.ig.igDoughnutChart={};$.extend($.ig.igDoughnutChart,{locale:{invalidBaseElement:" is not supported as base element. Use DIV instead."}})}})(jQuery);/*!@license
* Infragistics.Web.ClientUI DoughnutChart 16.1.20161.1009
*
* Copyright (c) 2011-2016 Infragistics Inc.
*
* http://www.infragistics.com/
*
* Depends on:
*	jquery-1.4.4.js
*	jquery.ui.core.js
*	jquery.ui.widget.js
*	infragistics.util.js
*	infragistics.datasource.js
*	infragistics.dv.core.js
*	infragistics.dvcommonwidget.js
*	infragistics.templating.js
*	infragistics.ui.chartlegend.js
*	infragistics.ui.basechart.js
*	infragistics.chart_piechart.js
*/
if(typeof jQuery!=="function"){throw new Error("jQuery is undefined")}(function($){var _aNull=function(v,nan){return v===null||v===undefined||nan&&typeof v==="number"&&isNaN(v)};$.widget("ui.igDoughnutChart",$.ui.igBaseChart,{css:{chart:"ui-doughnut ui-corner-all ui-widget-content",tooltip:"ui-doughnut-tooltip ui-widget-content ui-corner-all"},options:{width:null,height:null,series:[{type:"flat",showTooltip:false,tooltipTemplate:null,itemsSource:null,valueMemberPath:"null",labelMemberPath:"null",labelsPosition:"center",leaderLineVisibility:"visible",leaderLineStyle:null,leaderLineType:"straight",leaderLineMargin:6,othersCategoryThreshold:3,othersCategoryType:"percent",othersCategoryText:"Others",legend:null,formatLabel:null,labelExtent:10,startAngle:0,selectedStyle:null,brushes:null,outlines:null,isSurfaceInteractionDisabled:null,radiusFactor:.9}],allowSliceSelection:true,isSurfaceInteractionDisabled:false,allowSliceExplosion:true,innerExtent:40,selectedStyle:null},events:{tooltipShowing:"tooltipShowing",tooltipShown:"tooltipShown",tooltipHiding:"tooltipHiding",tooltipHidden:"tooltipHidden",browserNotSupported:"browserNotSupported",sliceClick:null,holeDimensionsChanged:null},_create:function(){var elementName=this.element[0].nodeName.toUpperCase();if(elementName!=="DIV"){throw new Error(elementName+$.ig.igDoughnutChart.locale.invalidBaseElement)}if(this.css.chart){this.element.addClass(this.css.chart)}$.ui.igBaseChart.prototype._create.apply(this);this._defStyle("selected");this._defStyle("unselected");var doughnutChart=this._chart;if(doughnutChart){doughnutChart.sliceClick=$.ig.Delegate.prototype.combine(doughnutChart.sliceClick,jQuery.proxy(this._fireDoughnutChart_sliceClick,this));doughnutChart.holeDimensionsChanged=$.ig.Delegate.prototype.combine(doughnutChart.holeDimensionsChanged,jQuery.proxy(this._fireDoughnutChart_holeDimensionsChanged,this))}},_creationOptions:null,_createWidget:function(options,element){this._creationOptions=options;this._tooltipTemplate={};this._seriesOpt={};this._seriesColl={};this._tooltip={};this._legends={};$.Widget.prototype._createWidget.apply(this,[options,element])},_beforeInitialOptions:function(chart,elem){chart.provideContainer(elem[0])},_provideContainer:function(chart,elem){},_setInitialOptions:function(chart){var o=this._creationOptions,self=this;for(key in o){if(o.hasOwnProperty(key)){v=o[key];if(v!==null){self._set_option(chart,key,v)}}}},_getDataSourceOwner:function(dataSourceOwnerName){return this._seriesColl[dataSourceOwnerName]},_fireDoughnutChart_holeDimensionsChanged:function(chart,evt){var e={};e.center={x:evt.center().__x,y:evt.center().__y};e.radius=evt.radius();e.doughnut=this.options;this._trigger("holeDimensionsChanged",null,e)},_fireDoughnutChart_sliceClick:function(chart,evt){var e={},slice=evt.slice();if(slice){e.slice={};e.slice.item=slice.dataContext();e.slice.isSelected=slice.isSelected();e.slice.isExploded=slice.isExploded();e.doughnut=this.options}this._trigger("sliceClick",null,e);if(e.slice.isSelected!=slice.isSelected()){slice.isSelected(e.slice.isSelected)}if(e.slice.isExploded!=slice.isExploded()){slice.isExploded(e.slice.isExploded)}},_getValueKeyName:function(){return"valueMemberPath"},_getNotifyResizeName:function(){return"notifyResized"},_createChart:function(){return new $.ig.XamDoughnutChart},_setLegend:function(item,value,chart){var legend;if(value){value.owner=this.widget;if(value.type===undefined){value.type="item"}var legend=value.element;if(legend){legend=$("#"+legend)}if(!legend||legend.length!==1){var widgetId=chart.getContainerID();legend=$('<div id="'+widgetId+"_"+item.name()+'_legend" />').insertAfter(this.element);legend[0]._remove=true}if(item.legend()===null){if(legend.data("igChartLegend")||value.data&&value.data("igChartLegend")){legend=!legend?value.data("igChartLegend"):legend.data("igChartLegend");legend.options.owner=this.widget.options;legend._owner=this.widget;if(!legend._ownerCount){legend._ownerCount=1}else{legend._ownerCount+=1}}else{legend=legend.igChartLegend(value).data("igChartLegend");if(!legend._ownerCount){legend._ownerCount=1}else{legend._ownerCount+=1}}item.legend(legend.legend)}else{$("#"+item.legend().name()).igChartLegend(value);item.legend()._ownerCount+=1}}else{if(item.legend()!==null&&item.legend().name().length>0&&$("#"+item.legend().name()).length>0){legend=$("#"+item.legend().name()).data().igChartLegend;legend._ownerCount-=1;item.legend(null);if(legend._ownerCount==0){var legendElement=legend.element;legend.destroy();if(legendElement[0]._remove){legendElement.remove()}}}}},_htmlCheckExpr:/^[^<]*(<[\w\W]+>)[^>]*$/,_addTooltip:function(chart,series,clss){var widgetId=chart.getContainerID();if(this._tooltip[series.name()]===undefined){this._tooltip[series.name()]=$('<div id="'+widgetId+"_"+series.name()+'_tooltip" class="'+clss+'"></div>');this._bindTooltipEvents(this,this._tooltip[series.name()]);series.toolTip(this._tooltip[series.name()])}},_removeTooltip:function(series){if(this._tooltip[series.name()]!==undefined){this._removeTooltipEvents(this._tooltip[series.name()]);delete this._tooltip[series.name()];series.toolTip(null)}},_bindTooltipEvents:function(chart,tooltip){tooltip.updateToolTip=$.ig.Delegate.prototype.combine(tooltip.updateToolTip,jQuery.proxy(this._fireToolTip_updateToolTip,this));tooltip.hideToolTip=$.ig.Delegate.prototype.combine(tooltip.hideToolTip,jQuery.proxy(this._fireToolTip_hideToolTip,this))},_removeTooltipEvents:function(tooltip){delete tooltip.updateToolTip;delete tooltip.hideToolTip},_fireToolTip_hideToolTip:function(args){var e={},noCancel;e=this._getChartEvt(args);e.tempId=e.series.name;e.element=null;if(e.series!==null){e.element=this._tooltip[e.tempId]}noCancel=this._trigger(this.events.tooltipHiding,null,e);if(noCancel){$.each(this._tooltip,function(i,tip){tip.hide()});this._trigger(this.events.tooltipHidden,null,e)}},_fireToolTip_updateToolTip:function(args){var e={},noCancel,template;e=this._getChartEvt(args);e.tempId=e.series.name;e.element=null;if(e.series!==null){e.element=this._tooltip[e.tempId]}noCancel=this._trigger(this.events.tooltipShowing,null,e);if(e===null){noCancel=false}if(noCancel){if(e.series!==null&&this._tooltipTemplate[e.tempId]!==undefined){template=this._tooltipTemplate[e.tempId]}if(template){this._tooltip[e.tempId].children().remove();if(e.item===null){noCancel=false}if(noCancel){this._tooltip[e.tempId].html($.ig.tmpl(template,e))}}if(noCancel){this._tooltip[e.tempId].show();this._trigger(this.events.tooltipShown,null,e)}}else{$.each(this._tooltip,function(i,tip){tip.hide()})}},_getChartEvt:function(evtArgs){var e={},seriesOpt=this._getSeriesOpt(evtArgs),pos,widget=this,intSeries=null;e.doughnut=widget.options;e.series=seriesOpt;e.item=evtArgs.item();if(evtArgs.series&&evtArgs.series()!==null){intSeries=evtArgs.series()}if(evtArgs.originalEvent&&evtArgs.originalEvent()!==null&&evtArgs.originalEvent().position&&evtArgs.originalEvent().position()!==null){pos=evtArgs.originalEvent().position();e.positionX=pos.__x;e.positionY=pos.__y}return e},_getSeriesOpt:function(evtArgs){var widget=this;if(widget._seriesOpt[evtArgs.series().name()]){return widget._seriesOpt[evtArgs.series().name()]}return widget.options},_setSeriesOption:function(ringSeriesBase,key,value,chart){if(key==="legend"){this._setLegend(ringSeriesBase,value,chart);return true}else if(key==="showTooltip"){if(value===true){this._addTooltip(chart,ringSeriesBase,this.css.tooltip)}if(value===false){this._removeTooltip(ringSeriesBase,chart)}return}else if(key==="tooltipTemplate"&&ringSeriesBase.toolTip){if($.ig.tmpl){if(this._htmlCheckExpr.test(value)){templ=value}else{if($("#"+value).length>0){templ=$("#"+value).text()}else if($(value).length>0){templ=$(value).text()}else{templ=value}}this._tooltipTemplate[ringSeriesBase.name()]=templ}return}else if(key==="legendItemBadgeTemplate"||key==="legendItemTemplate"){this._setDataTemplate(ringSeriesBase,key,value);return}else if(key==="isSurfaceInteractionDisabled"){ringSeriesBase.isSurfaceInteractionDisabled($.ig.util.toNullable($.ig.Boolean.prototype.$type,value));return true}else if("othersCategoryText"==key&&null==value){value=String.empty()}else if("childrenMemberPath"==key&&"RingSeriesCollection"==chart.series().getType().name){return}switch(key){case"itemsSource":ringSeriesBase.itemsSource(value);return true;case"valueMemberPath":ringSeriesBase.valueMemberPath(value);return true;case"labelMemberPath":ringSeriesBase.labelMemberPath(value);return true;case"labelsPosition":switch(value){case"none":ringSeriesBase.labelsPosition(0);break;case"center":ringSeriesBase.labelsPosition(1);break;case"insideEnd":ringSeriesBase.labelsPosition(2);break;case"outsideEnd":ringSeriesBase.labelsPosition(3);break;case"bestFit":ringSeriesBase.labelsPosition(4);break}return true;case"leaderLineVisibility":switch(value){case"visible":ringSeriesBase.leaderLineVisibility(0);break;case"collapsed":ringSeriesBase.leaderLineVisibility(1);break}return true;case"leaderLineStyle":ringSeriesBase.leaderLineStyle(value);return true;case"leaderLineType":switch(value){case"straight":ringSeriesBase.leaderLineType(0);break;case"arc":ringSeriesBase.leaderLineType(1);break;case"spline":ringSeriesBase.leaderLineType(2);break}return true;case"leaderLineMargin":ringSeriesBase.leaderLineMargin(value);return true;case"othersCategoryThreshold":ringSeriesBase.othersCategoryThreshold(value);return true;case"othersCategoryType":switch(value){case"number":ringSeriesBase.othersCategoryType(0);break;case"percent":ringSeriesBase.othersCategoryType(1);break}return true;case"othersCategoryText":ringSeriesBase.othersCategoryText(value);return true;case"formatLabel":ringSeriesBase.formatLabel(value);return true;case"labelExtent":ringSeriesBase.labelExtent(value);return true;case"startAngle":ringSeriesBase.startAngle(value);return true;case"selectedStyle":ringSeriesBase.selectedStyle(value);return true;case"brushes":var isRGB=true,val=value?value[0]:null;if(typeof val=="string"&&val=="HSV"||val=="RGB"){if(value[0]=="HSV"){isRGB=false}value=value.slice(1)}var $tempBrushCollection=new $.ig.BrushCollection;for(var i=0;value&&i<value.length;i++){var $tempBrush=$.ig.Brush.prototype.create(value[i]);$tempBrushCollection.add($tempBrush)}ringSeriesBase.brushes($tempBrushCollection);return true;case"outlines":var isRGB=true,val=value?value[0]:null;if(typeof val=="string"&&val=="HSV"||val=="RGB"){if(value[0]=="HSV"){isRGB=false}value=value.slice(1)}var $tempBrushCollection=new $.ig.BrushCollection;for(var i=0;value&&i<value.length;i++){var $tempBrush=$.ig.Brush.prototype.create(value[i]);$tempBrushCollection.add($tempBrush)}ringSeriesBase.outlines($tempBrushCollection);return true;case"isSurfaceInteractionDisabled":ringSeriesBase.isSurfaceInteractionDisabled(value);return true;case"radiusFactor":ringSeriesBase.radiusFactor(value);return true}},_set_option:function(doughnutChart,key,value){if(key==="legend"){this._setLegend(doughnutChart,value,doughnutChart);return true}else if(key==="legendItemBadgeTemplate"||key==="legendItemTemplate"){this._setDataTemplate(doughnutChart,key,value);return}if($.ui.igBaseChart.prototype._set_option.apply(this,arguments)){return true}if(key==="series"){this._iterationFlag=!this._iterationFlag;var count=value.length;for(i=0;i<count;i++){if(!value[i].name){throw new Error("Series name is missing for series: "+i)}if(this._seriesColl.hasOwnProperty(value[i].name)){if(value[i].remove){this._removeSeries(doughnutChart,value[i])}this._updateSeries(doughnutChart,value[i])}else this._addSeries(doughnutChart,value[i])}return true}else if(key==="isSurfaceInteractionDisabled"){doughnutChart.isSurfaceInteractionDisabled($.ig.util.toNullable($.ig.Boolean.prototype.$type,value));return true}switch(key){case"allowSliceSelection":doughnutChart.allowSliceSelection(value);return true;case"isSurfaceInteractionDisabled":doughnutChart.isSurfaceInteractionDisabled(value);return true;case"allowSliceExplosion":doughnutChart.allowSliceExplosion(value);return true;case"innerExtent":doughnutChart.innerExtent(value);return true;case"selectedStyle":doughnutChart.selectedStyle(value);return true}},_setDataTemplate:function(series,key,value){var tempTemplate=new $.ig.DataTemplate,requireThis=false;if(!value){if(series[key]){series[key](null)}return}if(value.requireThis){requireThis=true}if(value.render){if(requireThis){tempTemplate.render($.proxy(value.render,value))}else{tempTemplate.render(value.render)}}else{tempTemplate.render(value)}if(value.measure){if(requireThis){tempTemplate.measure($.proxy(value.measure,value))}else{tempTemplate.measure(value.measure)}}if(value.passStarting){if(requireThis){tempTemplate.passStarting($.proxy(value.passStarting,value))}else{tempTemplate.passStarting(value.passStarting)}}if(value.passCompleted){if(requireThis){tempTemplate.passCompleted($.proxy(value.passCompleted,value))}else{tempTemplate.passCompleted(value.passCompleted)}}if(series[key]){series[key](tempTemplate)}},addSeries:function(seriesObj){this._addSeries(this._chart,seriesObj)},_addSeries:function(chart,value){var series;if(!value||value.remove==true||!this._seriesColl)return;if(!this._seriesColl.hasOwnProperty(value.name)){if(!value.type||value.type==="flat"){series=new $.ig.RingSeries}else{series=new $.ig.HierarchicalRingSeries}series.name(value.name);series.chart(chart);this._seriesOpt[value.name]={};for(currentKey in value){if(value.hasOwnProperty(currentKey)){this._setSeriesOption(series,currentKey,value[currentKey],chart);this._seriesOpt[value.name][currentKey]=value[currentKey]}}this._seriesColl[value.name]=series;if(value.dataSource){this._dataBindInternal(value,value.name)}chart.series().add(series);if(series.selectedStyle()==null){series.selectedStyleResolved(chart.selectedStyle())}}},removeSeries:function(seriesObj){this._removeSeries(this._chart,seriesObj)},_removeSeries:function(chart,value){if(!value||!this._seriesColl)return;if(this._seriesColl.hasOwnProperty(value.name)){var series=this._seriesColl[value.name];if(series){delete this._seriesColl[value.name];if(chart.series().contains(series)){this._setLegend(series);chart.series().remove(series);this._removeTooltip(series)}}if(this._seriesOpt[value.name])delete this._seriesOpt[value.name];if(this._tooltipTemplate[value.name])delete this._tooltipTemplate[value.name]}},updateSeries:function(value){this._updateSeries(this._chart,value)},_updateSeries:function(chart,value){if(!value||!this._seriesColl)return;if(this._seriesColl.hasOwnProperty(value.name)){var series=this._seriesColl[value.name];if(series&&!value.remove){for(currentKey in value){if(value.hasOwnProperty(currentKey)){this._setSeriesOption(series,currentKey,value[currentKey],chart);this._seriesOpt[value.name][currentKey]=value[currentKey]}}this._seriesColl[value.name]=series;if(value.dataSource){this._dataBindInternal(value,value.name)}}}},getCenterCoordinates:function(){if(this._chart){var center=this._chart.getCenterCoordinates();return{x:center.__x,y:center.__y}}return{x:0,y:0}},getHoleRadius:function(){if(this._chart)return this._chart.getHoleRadius();return 0},exportVisualData:function(){if(this._chart){return this._chart.exportVisualData()}return null},flush:function(){if(this.chart()){this.chart().flush()}},_setOption:function(key,val){var chart=this._chart,o=this.options;if(o[key]===val){return this}$.Widget.prototype._setOption.apply(this,arguments);this._set_option(chart,key,val);return this},_defStyle:function(sel){var v,bk0,b0,style={},name=sel+"SliceStyle",span=this.element.find("SPAN");if(span.length!==1||this.options[name]){return}bk0=span.css("background-color");b0=span.css("border-top-color");sel="ui-doughnut-slice-"+sel;span.addClass(sel);v=span.css("background-color");if(v&&v!=="transparent"&&v!==bk0){style.fill=v}v=span.css("border-top-color");if(v&&v!==b0){style.stroke=v}v=parseFloat(span.css("opacity"));if(!isNaN(v)&&v>0&&v<1){style.opacity=v}span.removeClass(sel);this._set_option(this._chart,name,style)},destroy:function(){for(var seriesName in this._seriesColl){var series=this._seriesColl[seriesName];this._setLegend(series);this._removeTooltip(series);delete this._seriesColl[seriesName];delete this._seriesOpt[seriesName];delete this._tooltipTemplate[seriesName]}delete this._tooltipTemplate;delete this._seriesOpt;delete this._seriesColl;delete this._tooltip;delete this._legends;this.chart().destroy();$.ui.igBaseChart.prototype.destroy.apply(this)}});$.extend($.ui.igDoughnutChart,{version:"16.1.20161.1009"})})(jQuery);(function($){$(document).ready(function(){var wm=$("#__ig_wm__").length>0?$("#__ig_wm__"):$('<div id="__ig_wm__"></div>').appendTo(document.body);wm.css({position:"fixed",bottom:0,right:0,zIndex:1e3}).addClass("ui-igtrialwatermark")})})(jQuery);
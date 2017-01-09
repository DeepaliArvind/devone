Ext.define('Devone.view.appreportui.chartpoint.ChartPointView', {
	extend : 'Ext.panel.Panel',
	requires:['Devone.view.appreportui.chartpoint.ChartPointController'],
	xtype : 'chart-point',
	controller : 'chartPointController',
	//bodyStyle : 'background:#D8D8D8',
	itemId:"chartpoint",
	
	defualtHeight:100,
	layout : {
		type : 'hbox',
	}
});


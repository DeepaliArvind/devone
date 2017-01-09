Ext.define('Devone.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Devone.view.appreportui.ReportViewController',
	            'Devone.view.appreportui.datagrid.DataGridPanel',
	            'Devone.view.appreportui.datagrid.DataGridView',
	            'Devone.view.appreportui.querycriteria.QueryCriteriaView',
	            'Devone.view.appreportui.chart.ChartView',
	            'Devone.view.appreportui.datapoint.DataPointView',
	            'Devone.view.googlemaps.map.MapPanel',
	            'Devone.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});

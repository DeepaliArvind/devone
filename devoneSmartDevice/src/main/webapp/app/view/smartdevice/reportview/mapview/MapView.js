Ext.define('Devone.view.smartdevice.reportview.mapview.MapView', {
	extend : 'Ext.panel.Panel',
	requires:['Devone.view.smartdevice.reportview.mapview.MapViewController'],
	xtype : 'report-mapview',
	itemId:'report-mapview',
	controller:'report-mapview',
	bodyStyle:{
		background:'#ffffff'
	},
	margin:'5 0 0 0',
	listeners:{
		afterrender : "onMapViewAfterRender"
	}
});
/**
 * 
 */
Ext.define('Devone.view.logalarm.mainscreen.LogAlarmMainViewTabPanel', {
	extend : 'Ext.tab.Panel',
	xtype : 'logAlarmMainViewTabPanel',
	
	requires : ['Devone.view.logalarm.mainscreen.LogAlarmMainViewTabPanelController', 'Ext.ux.TabReorderer','Devone.view.logalarm.mainscreen.AddLogAlarm'],
	
	controller : 'logAlarmMainViewTabPanelController',
	defaults: {
        bodyPadding: 10,
        autoScroll : true,
    },
});
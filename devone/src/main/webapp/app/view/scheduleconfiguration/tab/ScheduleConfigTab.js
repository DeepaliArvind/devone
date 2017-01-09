/**
 * 
 */
Ext.define("Devone.view.scheduleconfiguration.tab.ScheduleConfigTab", {
	extend : 'Ext.tab.Panel',
	xtype : 'schedulerConfigTab',
	requires : [ 'Devone.view.scheduleconfiguration.tab.ScheduleConfigTabController' ],
	controller : 'schedulerConfigTabController'
});
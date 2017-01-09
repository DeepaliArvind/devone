Ext.define('Devone.view.databrowsercalendar.DBCalendar', {
	extend : 'Devone.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Devone.view.databrowsercalendar.DBCalendarController',
	             'Devone.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});

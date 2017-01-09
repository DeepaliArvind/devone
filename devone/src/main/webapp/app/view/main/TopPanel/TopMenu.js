Ext.define('Devone.view.main.TopPanel.TopMenu', { 
	extend: 'Ext.toolbar.Toolbar',
    xtype: 'topMenu',
    requires:['Devone.view.main.TopPanel.TopMenuController',/*'Devone.view.main.ResourceWindow'*/],
    controller :'topMenuController',
    border:0,
    itemId:'topMenuPanel',
    autoScroll :true,
    items:[]
});
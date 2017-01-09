Ext.define('Devone.devone.smartdevice.com.controller.shoppingcontext.retail.OnlineShoppingController', {
     extend: 'Devone.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.OnlineShoppingController',
     onOrderProcessClick: function(me, e, eOpts) {
          var formComponent = new Ext.create('Devone.devone.smartdevice.com.view.shoppingcontext.retail.ProcessOrder');
          formComponent.title = 'ProcessOrder';
          var tabs = Ext.getCmp('appMainTabPanel');
          tabs.remove(this.getView());
          tabs.add(formComponent);
          tabs.setActiveTab(formComponent);
     },
     onItemCatelogueClick: function(me, e, eOpts) {
          var formComponent = new Ext.create('Devone.devone.smartdevice.com.view.shoppingcontext.retail.ItemsCatelogue');
          formComponent.title = 'ItemsCatelogue';
          var tabs = Ext.getCmp('appMainTabPanel');
          tabs.remove(this.getView());
          tabs.add(formComponent);
          tabs.setActiveTab(formComponent);
     },
     onFormExt13754Afterrender: function(me, eOpts) {
          var jsonData = {};
          var scope = this.getView();
          Ext.Ajax.request({
               url: AppRestUrl + 'secure/GetLoggedInUserWS/getLoggedInUser',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         scope.sender.down('#').setValue(responseData.loginCorecontactsLastname);
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     }
});
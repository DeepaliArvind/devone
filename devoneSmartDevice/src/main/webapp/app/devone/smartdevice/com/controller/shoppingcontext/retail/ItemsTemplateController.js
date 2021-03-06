Ext.define('Devone.devone.smartdevice.com.controller.shoppingcontext.retail.ItemsTemplateController', {
     extend: 'Devone.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.ItemsTemplateController',
     onAddToCartClick: function(me, e, eOpts) {
          var componentArray = [this.view.down('#itemId_textfield'), this.view.down('#qty_numberfield')];
          if (this.validateAndShowErrorMessages(me, componentArray, false)) {
               return;
          }
          var jsonData = {};
          jsonData.itemId = this.view.down('#itemId_textfield').getValue();
          jsonData.qty = this.view.down('#qty_numberfield').getValue();
          me.setDisabled(true);
          var scope = this.getView();
          Ext.Ajax.request({
               url: AppRestUrl + 'secure/Cart/',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               maskEnable: true,
               maskEle: scope,
               success: function(response, scope) {
                    scope.me.setDisabled(false);
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', responseText.response.message);
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    scope.me.setDisabled(false);
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     }
});
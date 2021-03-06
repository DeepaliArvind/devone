Ext.define('Devone.devone.web.com.controller.shoppingcontext.retail.OrderProcessingScreenController', {
     extend: 'Devone.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.OrderProcessingScreenController',
     onPaymentProcessingClick: function(me, e, eOpts) {
          var componentArray = [this.view.down('#amount_numberfield'), this.view.down('#ccHolderName_textfield'), this.view.down('#creditCardNo_textfield'), this.view.down('#customerId_textfield'), this.view.down('#cvvNo_textfield'), this.view.down('#expiryMonth_numberfield'), this.view.down('#expiryYear_numberfield')];
          if (this.validateAndShowErrorMessages(me, componentArray, false)) {
               return;
          }
          var jsonData = {};
          jsonData.amount = this.view.down('#amount_numberfield').getValue();
          jsonData.ccHolderName = this.view.down('#ccHolderName_textfield').getValue();
          jsonData.creditCardNo = this.view.down('#creditCardNo_textfield').getValue();
          jsonData.customerId = this.view.down('#customerId_textfield').getValue();
          jsonData.cvvNo = this.view.down('#cvvNo_textfield').getValue();
          jsonData.expiryMonth = this.view.down('#expiryMonth_numberfield').getValue();
          jsonData.expiryYear = this.view.down('#expiryYear_numberfield').getValue();
          me.setDisabled(true);
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/OrderProcessingWS/pOrderProcessing',
               async: false,
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
                         Ext.Msg.alert('Server Response', 'Order processed successfully...!');
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
     },
     onFormExt9882Afterrender: function(me, eOpts) {
          var jsonData = {};
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/GetCartTotalWS/getCartTotal',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         scope.sender.down('#OrderTotal_displayfield').setValue(responseData.cartSubtotal);
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
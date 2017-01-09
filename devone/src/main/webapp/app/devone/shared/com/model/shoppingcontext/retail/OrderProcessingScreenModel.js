Ext.define('Devone.devone.shared.com.model.shoppingcontext.retail.OrderProcessingScreenModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "CartItems",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "paymentProcessing",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "OrderTotal",
          "type": "string",
          "defaultValue": ""
     }]
});
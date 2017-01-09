Ext.define('Devone.devone.shared.com.model.shoppingcontext.retail.ProcessOrderModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "CartItems",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "PaymentProcessing",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "cartTotal",
          "type": "string",
          "defaultValue": ""
     }]
});
Ext.define('Devone.devone.shared.com.model.shoppingcontext.OrderDetailQRMModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "qty",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "subTotal",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "itemName",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "price",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "productIdDesc",
          "type": "string",
          "defaultValue": ""
     }]
});
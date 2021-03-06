Ext.define('Devone.devone.shared.com.model.shoppingcontext.retail.OrderDetailModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "orderDetailId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "itemid",
          "reference": "Item",
          "defaultValue": ""
     }, {
          "name": "itemItemName",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "qty",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "subTotal",
          "type": "number",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "OrderMain",
          "reference": "OrderMainModel"
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});
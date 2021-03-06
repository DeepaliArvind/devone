Ext.define('Devone.devone.shared.com.model.shoppingcontext.retail.CartModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "cartid",
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
          "name": "userid",
          "reference": "UserDetail",
          "defaultValue": ""
     }, {
          "name": "userDetailUserDetailName",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});
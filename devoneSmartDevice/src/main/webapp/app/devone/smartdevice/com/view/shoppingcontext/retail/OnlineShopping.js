Ext.define('Devone.devone.smartdevice.com.view.shoppingcontext.retail.OnlineShopping', {
     "xtype": "onlineShoppingView",
     "items": [{
          "xtype": "panel",
          "items": [{
               "xtype": "displayfield",
               "margin": 5,
               "bindable": "Welcome",
               "style": "word-break: break-word; word-wrap: break-word;",
               "value": "Welcome",
               "name": "Welcome",
               "itemId": "Welcome_displayfield",
               "defaultValue": "Welcome"
          }, {
               "xtype": "displayfield",
               "margin": 5,
               "bindable": "Name",
               "style": "word-break: break-word; word-wrap: break-word;",
               "name": "Name",
               "itemId": "Name_displayfield",
               "flex": 1
          }],
          "layout": {
               "type": "hbox"
          },
          "autoScroll": true,
          "border": true,
          "margin": 5,
          "itemId": "panel_ext_13897",
          "columnWidth": "1",
          "flex": 1,
          "dockedItems": []
     }, {
          "xtype": "panel",
          "items": [{
               "xtype": "button",
               "name": "OrderProcess",
               "text": "OrderProcess",
               "margin": 5,
               "scale": "medium",
               "itemId": "OrderProcess_button",
               "listeners": {
                    "click": "onOrderProcessClick"
               }
          }],
          "border": true,
          "autoScroll": false,
          "layout": "fit",
          "margin": 5,
          "itemId": "panel_ext_13806",
          "dockedItems": [],
          "columnWidth": "1",
          "flex": 1
     }, {
          "xtype": "panel",
          "items": [{
               "xtype": "button",
               "name": "ItemCatelogue",
               "text": "ItemCatelogue",
               "margin": 5,
               "scale": "medium",
               "itemId": "ItemCatelogue_button",
               "listeners": {
                    "click": "onItemCatelogueClick"
               }
          }],
          "border": true,
          "autoScroll": false,
          "layout": "fit",
          "margin": 5,
          "itemId": "panel_ext_13769",
          "dockedItems": [],
          "columnWidth": "1",
          "flex": 1
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "margin": 5,
     "itemId": "form_ext_13754",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "listeners": {
          "afterrender": "onFormExt13754Afterrender",
          "scope": "controller"
     },
     "requires": ["Devone.devone.smartdevice.com.controller.shoppingcontext.retail.OnlineShoppingController", "Devone.devone.shared.com.viewmodel.shoppingcontext.retail.OnlineShoppingViewModel", "Devone.devone.shared.com.model.shoppingcontext.retail.OnlineShoppingModel"],
     "viewModel": "OnlineShoppingViewModel",
     "controller": "OnlineShoppingController"
});
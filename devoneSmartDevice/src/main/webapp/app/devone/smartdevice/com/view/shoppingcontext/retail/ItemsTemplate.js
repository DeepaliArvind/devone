Ext.define('Devone.devone.smartdevice.com.view.shoppingcontext.retail.ItemsTemplate', {
     "xtype": "itemsTemplateView",
     "items": [{
          "xtype": "panel",
          "items": [{
               "xtype": "textfield",
               "fieldLabel": "Product",
               "margin": 5,
               "bindable": "productIdDesc",
               "name": "productIdDesc",
               "itemId": "productIdDesc_textfield"
          }, {
               "xtype": "textfield",
               "fieldLabel": "Item",
               "margin": 5,
               "bindable": "itemName",
               "name": "itemName",
               "itemId": "itemName_textfield"
          }, {
               "xtype": "image",
               "name": "img",
               "height": 100,
               "width": 100,
               "border": true,
               "src": AppRestUrl+"",
               "margin": 5,
               "bindable": "img",
               "itemId": "img_image",
               "bind": {
                    "src": "{img}"
               }
          }, {
               "xtype": "numberfield",
               "fieldLabel": "Price",
               "name": "price",
               "margin": 5,
               "bindable": "price",
               "itemId": "price_numberfield"
          }, {
               "xtype": "numberfield",
               "fieldLabel": "Quantity",
               "name": "qty",
               "margin": 5,
               "bindable": "qty",
               "itemId": "qty_numberfield"
          }, {
               "xtype": "textfield",
               "fieldLabel": "itemId",
               "margin": 5,
               "bindable": "itemId",
               "name": "itemId",
               "itemId": "itemId_textfield",
               "hidden": true
          }, {
               "xtype": "button",
               "name": "AddToCart",
               "text": "Add To Cart",
               "margin": 5,
               "scale": "medium",
               "itemId": "AddToCart_button",
               "listeners": {
                    "click": "onAddToCartClick"
               }
          }],
          "layout": {
               "type": "vbox"
          },
          "border": true,
          "autoScroll": true,
          "margin": 5,
          "itemId": "panel_ext_11314",
          "columnWidth": "1",
          "flex": 1,
          "dockedItems": []
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "margin": 5,
     "itemId": "form_ext_11304",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "requires": ["Devone.devone.smartdevice.com.controller.shoppingcontext.retail.ItemsTemplateController", "Devone.devone.shared.com.viewmodel.shoppingcontext.retail.ItemsTemplateViewModel", "Devone.devone.shared.com.model.shoppingcontext.retail.ItemsTemplateModel"],
     "viewModel": "ItemsTemplateViewModel",
     "controller": "ItemsTemplateController"
});
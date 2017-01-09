Ext.define('Devone.devone.smartdevice.com.view.shoppingcontext.retail.ItemsCatelogue', {
     "xtype": "itemsCatelogueView",
     "items": [{
          "xtype": "listViewBaseView",
          "name": "cartList",
          "isListPanel": true,
          "autoScroll": true,
          "border": false,
          "layout": "column",
          "defaults": {
               "columnWidth": "1.0"
          },
          "templateConfig": {
               "uiId": "19CD4ADB-74B6-4936-8918-08F05FE4D592",
               "uiClass": "Devone.devone.smartdevice.com.view.shoppingcontext.retail.ItemsTemplate",
               "uiType": 2,
               "url": AppRestUrl+"secure/GetItemsWS/getItems",
               "requestMethodType": "POST"
          },
          "padding": 0,
          "margin": 5,
          "itemId": "cartList_panel",
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
     "itemId": "form_ext_11744",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "requires": ["Devone.devone.smartdevice.com.controller.shoppingcontext.retail.ItemsCatelogueController", "Devone.devone.shared.com.viewmodel.shoppingcontext.retail.ItemsCatelogueViewModel", "Devone.devone.shared.com.model.shoppingcontext.retail.ItemsCatelogueModel", "Devone.view.fw.component.ListViewBaseView"],
     "viewModel": "ItemsCatelogueViewModel",
     "controller": "ItemsCatelogueController"
});
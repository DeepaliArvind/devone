Ext.define('Devone.devone.web.com.view.shoppingcontext.retail.ItemCatelogue', {
     "xtype": "itemCatelogueView",
     "items": [{
          "xtype": "listViewBaseView",
          "name": "itemList",
          "isListPanel": true,
          "autoScroll": true,
          "border": false,
          "layout": "column",
          "defaults": {
               "columnWidth": "1.0"
          },
          "templateConfig": {
               "uiId": "E4C8CEAF-A587-4C3F-9B82-9D54BEC1AAAD",
               "uiClass": "Devone.devone.web.com.view.shoppingcontext.retail.ItemTemplate",
               "uiType": 2,
               "url": "secure/GetItemsWS/getItems",
               "requestMethodType": "POST"
          },
          "padding": 0,
          "margin": 5,
          "itemId": "itemList_panel",
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
     "itemId": "form_ext_9744",
     "dockedItems": [],
     "requires": ["Devone.devone.web.com.view.shoppingcontext.retail.ItemTemplate", "Devone.devone.web.com.controller.shoppingcontext.retail.ItemCatelogueController", "Devone.devone.shared.com.viewmodel.shoppingcontext.retail.ItemCatelogueViewModel", "Devone.devone.shared.com.model.shoppingcontext.retail.ItemCatelogueModel", "Devone.view.fw.component.ListViewBaseView"],
     "extend": "Ext.form.Panel",
     "viewModel": "ItemCatelogueViewModel",
     "controller": "ItemCatelogueController"
});
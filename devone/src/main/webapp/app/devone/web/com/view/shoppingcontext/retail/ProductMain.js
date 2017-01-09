Ext.define('Devone.devone.web.com.view.shoppingcontext.retail.ProductMain', {
     "xtype": "productMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "ProductMainController",
     "restURL": "/Product",
     "defaults": {
          "split": true
     },
     "requires": ["Devone.devone.shared.com.model.shoppingcontext.retail.ProductModel", "Devone.devone.web.com.controller.shoppingcontext.retail.ProductMainController", "Devone.devone.shared.com.viewmodel.shoppingcontext.retail.ProductViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Product",
               "name": "ProductTreeContainer",
               "itemId": "ProductTreeContainer",
               "restURL": "/Product",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "ProductTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "itemId": "searchBox",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Product",
                    "title": "Product",
                    "name": "Product",
                    "itemId": "ProductForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "productId",
                         "itemId": "productId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "ProductId",
                         "margin": "5 5 5 5",
                         "fieldLabel": "ProductId<font color='red'> *<\/font>",
                         "fieldId": "9AE76A0C-A48A-4FF8-B4DE-BD00164C6457",
                         "minLength": "1",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "productId"
                    }, {
                         "name": "productIdDesc",
                         "itemId": "productIdDesc",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "ProductIdDesc",
                         "margin": "5 5 5 5",
                         "fieldLabel": "ProductIdDesc<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "FA988044-32D8-4B80-A6DD-BEA52700F59A",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "productIdDesc",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "88E4C307-A522-41E9-8092-BC3E2CE0A274",
                         "bindable": "versionId",
                         "hidden": true
                    }, {
                         "name": "activeStatus",
                         "itemId": "activeStatus",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "activeStatus",
                         "margin": "5 5 5 5",
                         "value": "1",
                         "fieldLabel": "activeStatus",
                         "fieldId": "3C6FC8AB-7286-47AA-89FD-18BA9B69253A",
                         "bindable": "systemInfo.activeStatus",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 90,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 90,
                              "customId": 900
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 90,
                              "customId": 901,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 90,
                              "customId": 902,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Product",
                    "title": "Details Grid",
                    "name": "ProductGrid",
                    "itemId": "ProductGrid",
                    "restURL": "/Product",
                    "columns": [{
                         "header": "ProductId",
                         "dataIndex": "productId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "ProductIdDesc",
                         "dataIndex": "productIdDesc",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Product",
               "title": "Product",
               "name": "Product",
               "itemId": "ProductForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "productId",
                    "itemId": "productId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "ProductId",
                    "margin": "5 5 5 5",
                    "fieldLabel": "ProductId<font color='red'> *<\/font>",
                    "fieldId": "9AE76A0C-A48A-4FF8-B4DE-BD00164C6457",
                    "minLength": "1",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "productId"
               }, {
                    "name": "productIdDesc",
                    "itemId": "productIdDesc",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "ProductIdDesc",
                    "margin": "5 5 5 5",
                    "fieldLabel": "ProductIdDesc<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "FA988044-32D8-4B80-A6DD-BEA52700F59A",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "productIdDesc",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "88E4C307-A522-41E9-8092-BC3E2CE0A274",
                    "bindable": "versionId",
                    "hidden": true
               }, {
                    "name": "activeStatus",
                    "itemId": "activeStatus",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "activeStatus",
                    "margin": "5 5 5 5",
                    "value": "1",
                    "fieldLabel": "activeStatus",
                    "fieldId": "3C6FC8AB-7286-47AA-89FD-18BA9B69253A",
                    "bindable": "systemInfo.activeStatus",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 90,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 90,
                         "customId": 900
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 90,
                         "customId": 901,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 90,
                         "customId": 902,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});
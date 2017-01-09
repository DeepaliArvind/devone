Ext.define('Devone.devone.web.com.view.shoppingcontext.retail.ItemMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "itemMainView",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "ItemMainController",
     "restURL": "/Item",
     "defaults": {
          "split": true
     },
     "requires": ["Devone.devone.shared.com.model.shoppingcontext.retail.ItemModel", "Devone.devone.web.com.controller.shoppingcontext.retail.ItemMainController", "Devone.devone.shared.com.model.shoppingcontext.retail.ProductModel", "Devone.view.fw.component.FileUploadComponent", "Devone.devone.shared.com.viewmodel.shoppingcontext.retail.ItemViewModel"],
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
               "displayName": "Item",
               "name": "ItemTreeContainer",
               "itemId": "ItemTreeContainer",
               "margin": "5 0 5 5",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "useArrows": true,
                    "name": "entityTreePanel",
                    "title": "Browse",
                    "rootVisible": false,
                    "itemId": "ItemTree",
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
                    "xtype": "form",
                    "displayName": "Item",
                    "name": "Item",
                    "itemId": "ItemForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form0",
                         "customWidgetType": "vdCard",
                         "header": {
                              "hidden": true
                         },
                         "items": [{
                              "layout": "column",
                              "customWidgetType": "vdColumnLayout",
                              "header": {
                                   "hidden": true
                              },
                              "xtype": "panel",
                              "items": [{
                                   "name": "itemId",
                                   "itemId": "itemId",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "itemId",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "itemId<font color='red'> *<\/font>",
                                   "fieldId": "C68A1500-B3D9-4D3A-9CD2-1F76C815B1ED",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "itemId"
                              }, {
                                   "name": "productId",
                                   "itemId": "productId",
                                   "xtype": "customcombobox",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Product Desc",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "customStore": {
                                        "model": "Devone.devone.shared.com.model.shoppingcontext.retail.ProductModel",
                                        "autoLoad": true,
                                        "autoSync": true,
                                        "sorters": [{
                                             "property": "primaryDisplay",
                                             "direction": "ASC"
                                        }],
                                        "proxy": {
                                             "type": "ajax",
                                             "url": "secure/Product/findAll",
                                             "actionMethods": {
                                                  "read": "GET"
                                             },
                                             "headers": {
                                                  "Content-Type": "application/json"
                                             },
                                             "extraParams": {},
                                             "reader": {
                                                  "type": "json",
                                                  "rootProperty": "response.data"
                                             }
                                        }
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "Product Desc<font color='red'> *<\/font>",
                                   "fieldId": "BD5AB84F-94D0-43A3-862F-4AE724AB4B97",
                                   "restURL": "Product",
                                   "bindable": "productId",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "itemName",
                                   "itemId": "itemName",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "Item Name",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Item Name<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "F5340063-79B5-43D8-A858-A2E9133C25F0",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "itemName",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "img",
                                   "itemId": "img",
                                   "xtype": "fileupload",
                                   "customWidgetType": "vdFileUpload",
                                   "displayName": "Image",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Image<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "05E5AB25-AFF5-48BD-A48E-CCBD67342297",
                                   "bindable": "img",
                                   "columnWidth": 0.5,
                                   "title": "Image<font color='red'> *<\/font>"
                              }, {
                                   "name": "stock",
                                   "itemId": "stock",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Stock",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Stock<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "ABF8AB30-7497-4923-9364-46DBE513D0C8",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "stock",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "price",
                                   "itemId": "price",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Price",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Price<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "890099DB-E6C4-4B58-B406-68107F64CE07",
                                   "minValue": "-9223372000000000000",
                                   "maxValue": "9223372000000000000",
                                   "bindable": "price",
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
                                   "fieldId": "06F68265-DD4F-4462-8D56-14B00FBDA840",
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
                                   "fieldId": "76B58FC2-D83B-4F64-852F-D4980B74EF5B",
                                   "bindable": "systemInfo.activeStatus",
                                   "hidden": true
                              }]
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Get Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "layout": "card",
                    "defaults": {
                         "autoScroll": true
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "Item",
                    "title": "Details Grid",
                    "name": "ItemGrid",
                    "itemId": "ItemGrid",
                    "requires": [],
                    "columns": [{
                         "header": "itemId",
                         "dataIndex": "itemId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Product Desc",
                         "dataIndex": "productId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "productProductName",
                         "dataIndex": "productProductName"
                    }, {
                         "header": "Item Name",
                         "dataIndex": "itemName",
                         "flex": 1
                    }, {
                         "header": "Image",
                         "dataIndex": "img",
                         "flex": 1
                    }, {
                         "header": "Stock",
                         "dataIndex": "stock",
                         "flex": 1
                    }, {
                         "header": "Price",
                         "dataIndex": "price",
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
               "xtype": "form",
               "displayName": "Item",
               "name": "Item",
               "itemId": "ItemForm",
               "bodyPadding": 10,
               "items": [{
                    "xtype": "form",
                    "itemId": "form0",
                    "customWidgetType": "vdCard",
                    "header": {
                         "hidden": true
                    },
                    "items": [{
                         "layout": "column",
                         "customWidgetType": "vdColumnLayout",
                         "header": {
                              "hidden": true
                         },
                         "xtype": "panel",
                         "items": [{
                              "name": "itemId",
                              "itemId": "itemId",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "itemId",
                              "margin": "5 5 5 5",
                              "fieldLabel": "itemId<font color='red'> *<\/font>",
                              "fieldId": "C68A1500-B3D9-4D3A-9CD2-1F76C815B1ED",
                              "minLength": "1",
                              "maxLength": "256",
                              "hidden": true,
                              "value": "",
                              "bindable": "itemId"
                         }, {
                              "name": "productId",
                              "itemId": "productId",
                              "xtype": "customcombobox",
                              "customWidgetType": "vdCombo",
                              "displayName": "Product Desc",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "customStore": {
                                   "model": "Devone.devone.shared.com.model.shoppingcontext.retail.ProductModel",
                                   "autoLoad": true,
                                   "autoSync": true,
                                   "sorters": [{
                                        "property": "primaryDisplay",
                                        "direction": "ASC"
                                   }],
                                   "proxy": {
                                        "type": "ajax",
                                        "url": "secure/Product/findAll",
                                        "actionMethods": {
                                             "read": "GET"
                                        },
                                        "headers": {
                                             "Content-Type": "application/json"
                                        },
                                        "extraParams": {},
                                        "reader": {
                                             "type": "json",
                                             "rootProperty": "response.data"
                                        }
                                   }
                              },
                              "allowBlank": false,
                              "fieldLabel": "Product Desc<font color='red'> *<\/font>",
                              "fieldId": "BD5AB84F-94D0-43A3-862F-4AE724AB4B97",
                              "restURL": "Product",
                              "bindable": "productId",
                              "columnWidth": 0.5
                         }, {
                              "name": "itemName",
                              "itemId": "itemName",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "Item Name",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Item Name<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "F5340063-79B5-43D8-A858-A2E9133C25F0",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "itemName",
                              "columnWidth": 0.5
                         }, {
                              "name": "img",
                              "itemId": "img",
                              "xtype": "fileupload",
                              "customWidgetType": "vdFileUpload",
                              "displayName": "Image",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Image<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "05E5AB25-AFF5-48BD-A48E-CCBD67342297",
                              "bindable": "img",
                              "columnWidth": 0.5,
                              "title": "Image<font color='red'> *<\/font>"
                         }, {
                              "name": "stock",
                              "itemId": "stock",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Stock",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Stock<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "ABF8AB30-7497-4923-9364-46DBE513D0C8",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "bindable": "stock",
                              "columnWidth": 0.5
                         }, {
                              "name": "price",
                              "itemId": "price",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Price",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Price<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "890099DB-E6C4-4B58-B406-68107F64CE07",
                              "minValue": "-9223372000000000000",
                              "maxValue": "9223372000000000000",
                              "bindable": "price",
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
                              "fieldId": "06F68265-DD4F-4462-8D56-14B00FBDA840",
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
                              "fieldId": "76B58FC2-D83B-4F64-852F-D4980B74EF5B",
                              "bindable": "systemInfo.activeStatus",
                              "hidden": true
                         }]
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Get Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "layout": "card",
               "defaults": {
                    "autoScroll": true
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});
Ext.define('Devone.devone.web.com.controller.shoppingcontext.retail.ItemMainController', {
     extend: 'Devone.view.fw.frameworkController.AggregateViewController',
     alias: 'controller.ItemMainController',
     pagingSize: 50,
     init: function() {
          var form = this.view.down('#ItemForm');
          form.reset();
          if (this.view.disableDB != null && this.view.disableDB) {
               this.updateFormUI(form, 'Update', true);
               this.hideDataBrowser();
          } else {
               this.updateFormUI(form, 'Save', true);
               this.renderTreeGridData(true, null);
          }
     },
     updateFormUI: function(form, status, butText) {
          for (var index = 0; index < form.items.items.length; index++) {
               var item = form.items.items[index];
               if (item.xtype == 'form' || item.xtype == 'panel') {
                    this.updateFormUI(item, status);
               }
               if (item.isCompositeKey) {
                    if (status == 'Save') {
                         item.setDisabled(false);
                    } else if (status == 'Update') {
                         item.setDisabled(true);
                    }
               }
          }
          if (butText) {
               if (status == 'Save') {
                    form.down('#saveFormButton').setText('Save');
               } else if (status == 'Update') {
                    form.down('#saveFormButton').setText('Update');
               }
          }
     },
     renderTreeGridData: function(fromInit, responseData) {
          if (responseData == null) {
               this.setGridConfig();
          } else if (responseData.response.success) {
               var data = responseData.response.data;
               var tree = this.view.down('#ItemTree');
               var rootNode = tree.getRootNode();
               rootNode.removeAll();
               for (var counter = 0; counter < data.length; counter++) {
                    var childNode = {
                         text: data[counter].primaryDisplay,
                         bConfig: data[counter],
                         leaf: true,
                         icon: 'images/table_icon.png'
                    };
                    rootNode.appendChild(childNode);
               }
               tree.getStore().sort('text', 'ASC');
               this.setGridData(data);
          } else if (!sessionTimeOutFlag) {
               Ext.Msg.alert('Server Response', responseData.response.message);
          }
     },
     /*********************Main Controller Functions*********************************/
     onDeleteActionColumnClickMainGrid: function(grid, rowIndex) {
          var recordId = grid.store.data.items[rowIndex].data.primaryKey;
          me = this;
          Ext.MessageBox.confirm({
               title: 'Confirm',
               msg: 'Delete Record',
               buttons: Ext.MessageBox.YESNO,
               fn: function(clickedButtonId) {
                    if (clickedButtonId == 'yes') {
                         me.deleteRecord(recordId);
                    }
               },
               animateTarget: this,
               icon: Ext.MessageBox.QUESTION
          })
     },
     deleteRecord: function(recordId) {
          var me = this;
          var restURL = this.view.restURL;
          var url = 'secure' + restURL + '/' + recordId;
          var jsonData = {};
          Ext.Ajax.request({
               url: url,
               method: 'DELETE',
               jsonData: jsonData,
               success: function(response, opts) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', responseText.response.message);
                         me.refreshMainForm(null, null, opts.method);
                    } else if (!sessionTimeOutFlag) {
                         me.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    me.addCommunicationLog(response, false);
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         me.responseHandler(responseText);
                    }
               }
          });
     },
     resetForm: function(but) {
          var form = but.up('form');
          form.down('#saveFormButton').setText('Save');
          form.reset();
          var grid = this.view.down('#ItemGrid');
          var tree = this.view.down('#ItemTree');
          tree.setSelection();
          grid.setSelection();
     },
     hideDataBrowser: function() {
          var form = this.view.down('#Item');
          var grid = this.view.down('#ItemGrid');
          var tree = this.view.down('#ItemTreeContainer');
          this.view.down('#addNewForm').destroy();
          grid.setHidden(true);
          tree.setHidden(true);
          if (this.view.primaryKey != null) {
               this.findById(this.view.primaryKey);
          }
     },
     onTreeRefreshClick: function(event, toolEl, panelHeader) {
          this.renderTreeGridData();
     },
     onGridRefreshClick: function(event, toolEl, panelHeader) {
          this.renderTreeGridData();
     },
     refreshMainForm: function(but, data, method) {
          var formPanel = this.view.down('#ItemForm');
          this.updateFormUI(formPanel, 'Save', true); /** Adding data to tree and grid */
          if (data != null) {
               var grid = this.view.down('#ItemGrid');
               var tree = this.view.down('#ItemTree');
               if (method == 'PUT') {
                    if (!(data instanceof Object)) {
                         var data = {
                              'findKey': data
                         };
                    }
                    data = this.findRecordById(this.view.restURL, data);
                    grid.getSelectionModel().selected.items[0].data = data;
                    grid.reconfigure();
                    var node = this.findChild(tree.getRootNode(), 'primaryKey', data.primaryKey);
                    if (node) {
                         node.set('text', data.primaryDisplay);
                         node.bConfig = data;
                         tree.reconfigure();
                    }
               }
          } else {
               if (method == 'DELETE') {
                    this.renderTreeGridData();
               }
          }
          if (but != null) {
               this.resetForm(but);
          } else {
               var but = this.view.down('#resetFormButton');
               this.resetForm(but);
          }
     },
     /********************************Tree Controller Functions**********************************/
     setTreeData: function(data) {
          var tree = this.view.down('#ItemTree');
          var rootNode = tree.getRootNode();
          rootNode.removeAll();
          for (var counter = 0; counter < data.length; counter++) {
               var childNode = {
                    text: data[counter].primaryDisplay,
                    bConfig: data[counter],
                    leaf: true,
                    icon: 'images/table_icon.png'
               };
               rootNode.appendChild(childNode);
          }
          tree.getStore().sort('text', 'ASC');
     },
     updateTreeWithPageData: function(records) {
          var data = [];
          var limitedRecords = [];
          if (records.length <= this.noOfRecordsPerGridPage) {
               limitedRecords = records;
          } else {
               limitedRecords = Ext.Array.slice(records, 0, this.noOfRecordsPerGridPage);
          }
          for (var counter = 0; counter < limitedRecords.length; counter++) {
               data.push(Ext.clone(limitedRecords[counter]));
          }
          this.setTreeData(data);
     },
     onFilterClick: function(but, evt) {
          var me = this;
          var currentObject = this.getView();
          var form = but.up('form');
          if (!form.isValid()) {
               return;
          }
          var searchData = this.getData(form);
          Ext.Ajax.request({
               url: 'secure' + currentObject.restURL + '/search',
               method: 'POST',
               maskEnable: true,
               maskEle: currentObject,
               view: currentObject,
               jsonData: Ext.JSON.encode(searchData),
               success: function(response, opts) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         me.renderTreeGridData(false, responseText);
                    } else if (!sessionTimeOutFlag) {
                         me.responseHandler(responseText);
                    }
               },
               failure: function(response, opts) {
                    var currentView = opts.view;
                    var compRef = currentView.down('#ItemTree');
                    me.addCommunicationLog(response, false, compRef);
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         me.responseHandler(responseText);
                    }
               }
          });
     },
     treeClick: function(me, record, item, index, e, eOpts) {
          var primaryKey = record.data.bConfig.primaryKey;
          if (record.data.leaf) {
               var Item = this.view.down('#ItemForm');
               Item.reset();
               var gridPanel = this.view.down('#ItemGrid');
               var foundRecord = gridPanel.store.findRecord('primaryKey', primaryKey);
               if (gridPanel && foundRecord) {
                    gridPanel.setSelection(foundRecord);
               }
          }
          var formPanel = this.getView().up().down('#ItemForm');
          formPanel.down('#saveFormButton').setText('Update'); /** Finding record by id */
          jsonData = {
               'findKey': primaryKey
          }
          var data = this.findRecordById(this.view.restURL, jsonData);
          this.modifyComponents(data, formPanel);
          this.updateFormUI(formPanel, 'Update', true);
          this.showFirstCard(formPanel);
          tabPanel = formPanel.up('tabpanel');
          tabPanel.setActiveTab(0);
     },
     /********************************Grid Controller Functions***********************************/
     setGridConfig: function(data) {
          var grid = this.view.down('#ItemGrid');
          var currentObject = this;
          var url = this.getView().restURL;
          var gridStore = Ext.create('Ext.data.Store', {
               model: 'Devone.devone.shared.com.model.shoppingcontext.retail.ItemModel',
               autoLoad: {
                    params: {
                         limit: this.pagingSize
                    }
               },
               pageSize: this.pagingSize,
               proxy: {
                    type: 'ajax',
                    api: {
                         'read': 'secure' + url + '/findPageWiseData',
                    },
                    actionMethods: {
                         'read': 'GET'
                    },
                    headers: {
                         'Content-Type': 'application/json',
                    },
                    reader: {
                         'type': 'json',
                         'rootProperty': 'response.data',
                         'totalProperty': 'response.totalSize'
                    },
                    enablePaging: true
               },
               sorters: [{
                    property: 'primaryDisplay',
                    direction: 'ASC'
               }],
               listeners: {
                    load: function(storeObj, records, successful, eOpts) {
                         var tree = currentObject.view.down('#ItemGrid');
                         var searchBox = currentObject.view.down('#searchBox');
                         searchBox.reset();
                         currentObject.onTriggerfieldChange(searchBox);
                         var formattedRecords = [];
                         for (var index = 0; index < records.length; index++) {
                              formattedRecords[index] = records[index].data;
                         }
                         currentObject.updateTreeWithPageData(formattedRecords);
                    }
               }
          });
          var previousPagingBar = grid.down('pagingtoolbar');
          if (previousPagingBar) {
               grid.dockedItems.remove(previousPagingBar);
          }
          grid.addDocked({
               xtype: 'pagingtoolbar',
               store: gridStore,
               dock: 'bottom',
               displayInfo: true,
               displayMsg: '{0}-{1} of {2}',
               doRefresh: function(refreshBtn, e) {
                    var grid = this.up('grid');
                    var gridTools = grid.tools;
                    var sortedTool = Ext.Array.filter(gridTools, function(item, index, gridTools) {
                         if (item.type == 'refresh') {
                              return true;
                         }
                    });
                    if (sortedTool.length > 0) {
                         var refreshTool = sortedTool[0];
                         refreshTool.fireEvent('click');
                    }
               }
          });
          grid.setStore(gridStore);
     },
     onGridItemClick: function(me, record, item, index, e, eOpts) {
          var primaryKey = record.data.primaryKey;
          jsonData = {
               'findKey': primaryKey
          } /** Finding record by id */
          var data = this.findRecordById(this.view.restURL, jsonData);
          var treePanel = this.view.up().up().down('#ItemTree');
          var foundNode = this.findChild(treePanel.getRootNode(), 'primaryKey', primaryKey);
          if (treePanel && foundNode) {
               treePanel.setSelection(foundNode);
          }
          var formPanel = this.getView().up().down('#ItemForm');
          formPanel.down('#saveFormButton').setText('Update');
          this.modifyComponents(data, formPanel);
          this.updateFormUI(formPanel, 'Update', true);
          this.showFirstCard(formPanel);
          tabPanel = formPanel.up('tabpanel');
          tabPanel.setActiveTab(0);
     },
     renderFormValue: function(val, me) {
          store = this.view.up().down('#Item').down('#' + me.column.dataIndex + '').store;
          if (store.data.length == 0) {
               return '';
          }
          var displayValue = store.findRecord('primaryKey', val).data.primaryDisplay;
          return displayValue != null ? displayValue : '';
     },
     setGridData: function(data) {
          var gridStore = this.view.down('#ItemGrid').store;
          gridStore.removeAll();
          gridStore.add(data);
          gridStore.sort('primaryDisplay', 'ASC');
     },
     /********************************Form Controller Functions***********************************/
     findById: function(primaryKey) {
          var me = this;
          var restURL = this.view.restURL;
          Ext.Ajax.request({
               url: 'secure' + restURL + '/findById',
               method: 'POST',
               controller: me,
               jsonData: {
                    'findKey': primaryKey
               },
               success: function(response, request) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         request.controller.loadData(responseText);
                    } else if (!sessionTimeOutFlag) {
                         me.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         me.responseHandler(responseText);
                    }
               }
          });
     },
     loadData: function(responseText) {
          var formPanel = this.getView().up().down('#ItemForm');
          this.modifyComponents(responseText.response.data, formPanel);
     },
     fetchDataFromStore: function(store) {
          storeItems = store.data.items;
          obj = {};
          arr = [];
          for (counter in storeItems) {
               arr.push(storeItems[counter].data);
          }
          return obj['objArr'] = arr;
     },
     renderFormValue: function(value, metaData, record, row, col, store, gridView) {
          try {
               var comboStore = this.getView().down('#' + metaData.column.dataIndex).getStore();
               var index = comboStore.findExact('primaryKey', value);
               return comboStore.getAt(index).data.primaryDisplay;
          } catch (e) {
               return value;
          }
     },
     saveForm: function(but, evt) {
          var form = but.up('form');
          var firstCard = form.down('#form0');
          if (!firstCard.getForm().isValid()) {
               if (this.validateAndShowErrorMessages(but, firstCard.items.items[0].items.items, true)) {
                    this.showCard(form, firstCard);
                    return;
               }
          }
          var jsonData = this.getData(form);
          var method;
          if (but.text == 'Save') {
               method = 'POST'
          } else if (but.text == 'Update') {
               method = 'PUT';
          }
          restURL = this.view.restURL;
          var me = this;
          Ext.Ajax.request({
               url: 'secure' + restURL + '/',
               but: but,
               method: method,
               maskEnable: true,
               maskEle: form,
               jsonData: jsonData,
               success: function(response, opts) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', responseText.response.message);
                         me.refreshMainForm(but, responseText.response.data, opts.method);
                    } else if (!sessionTimeOutFlag) {
                         me.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    me.addCommunicationLog(response, false);
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         me.responseHandler(responseText);
                    }
               }
          }, this);
     },
});
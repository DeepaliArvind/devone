package com.app.server.service.shoppingcontext.retail;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.app.shared.shoppingcontext.retail.Item;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import com.athena.server.pluggable.utils.bean.FindByBean;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Item Transaction table", complexity = Complexity.MEDIUM)
@RequestMapping("/Item")
public class ItemServiceImpl extends ItemService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ItemRepository<Item> itemrepo;

    /**
     * Retrieve list of  <Item> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<Item> lstitem = itemrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        responseBean.add("data", lstitem);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "findAll", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve Page-wise list of  <Item> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findPageWiseData", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findPageWiseData(@RequestParam("page") Integer page, @RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws Exception {
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        responseBean.add("data", itemrepo.findPageWiseData(limit, page));
        responseBean.add("totalSize", itemrepo.getTotalPageCount());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "findPageWiseData", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <Item> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Item
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody Item entity) throws Exception {
        itemrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "save", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <Item> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Item>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<Item> entity, @RequestHeader("isArray") boolean request) throws Exception {
        itemrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "save", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <Item> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        itemrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "delete", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <Item> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Item
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody Item entity) throws Exception {
        itemrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "update", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <Item> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Item>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<Item> entity, @RequestHeader("isArray") boolean request) throws Exception {
        itemrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "update", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Item> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByProductId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByProductId(@RequestBody FindByBean findByBean) throws Exception {
        List<Item> lstitem = itemrepo.findByProductId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        responseBean.add("data", lstitem);
        Log.out.println("SOPRT124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "findByProductId", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Item> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        Item lstitem = itemrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Item"));
        responseBean.add("data", lstitem);
        Log.out.println("SOPRT124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemServiceImpl", "findById", "Item");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}

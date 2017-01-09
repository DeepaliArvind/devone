package com.app.server.service.shoppingcontext.retail;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.server.businessservice.shoppingcontext.retail.OrderMainBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.app.server.repository.shoppingcontext.retail.OrderMainRepository;
import com.app.shared.shoppingcontext.retail.OrderMain;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for OrderMain Transaction table", complexity = Complexity.MEDIUM)
@RequestMapping("/OrderMain")
public class OrderMainServiceImpl extends OrderMainService {

    @Autowired
    private OrderMainBusinessService ordermainbz;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private OrderMainRepository<OrderMain> orderMainrepo;

    /**
     * Retrieve list of  <OrderMain> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<OrderMain> lstordermain = orderMainrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        responseBean.add("data", lstordermain);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "findAll", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve Page-wise list of  <OrderMain> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findPageWiseData", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findPageWiseData(@RequestParam("page") Integer page, @RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws Exception {
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        responseBean.add("data", orderMainrepo.findPageWiseData(limit, page));
        responseBean.add("totalSize", orderMainrepo.getTotalPageCount());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "findPageWiseData", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <OrderMain> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- OrderMain
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody OrderMain entity) throws Exception {
        orderMainrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "save", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <OrderMain> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<OrderMain>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<OrderMain> entity, @RequestHeader("isArray") boolean request) throws Exception {
        orderMainrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "save", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <OrderMain> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        orderMainrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "delete", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <OrderMain> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- OrderMain
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody OrderMain entity) throws Exception {
        ordermainbz.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "update", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <OrderMain> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<OrderMain>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<OrderMain> entity, @RequestHeader("isArray") boolean request) throws Exception {
        ordermainbz.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "update", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <OrderMain> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByUserId(@RequestBody FindByBean findByBean) throws Exception {
        List<OrderMain> lstordermain = orderMainrepo.findByUserId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        responseBean.add("data", lstordermain);
        Log.out.println("SOPRT124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "findByUserId", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <OrderMain> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        OrderMain lstordermain = orderMainrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderMain"));
        responseBean.add("data", lstordermain);
        Log.out.println("SOPRT124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainServiceImpl", "findById", "OrderMain");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}

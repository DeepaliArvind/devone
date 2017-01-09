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
import com.app.server.repository.shoppingcontext.retail.CartRepository;
import com.app.shared.shoppingcontext.retail.Cart;
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
import com.app.server.businessservice.shoppingcontext.retail.AddToCart;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Cart Transaction table", complexity = Complexity.MEDIUM)
@RequestMapping("/Cart")
public class CartServiceImpl extends CartService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CartRepository<Cart> cartrepo;

    /**
     * Retrieve list of  <Cart> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<Cart> lstcart = cartrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "findAll", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve Page-wise list of  <Cart> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findPageWiseData", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findPageWiseData(@RequestParam("page") Integer page, @RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws Exception {
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", cartrepo.findPageWiseData(limit, page));
        responseBean.add("totalSize", cartrepo.getTotalPageCount());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "findPageWiseData", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <Cart> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Cart>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<Cart> entity, @RequestHeader("isArray") boolean request) throws Exception {
        cartrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "save", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <Cart> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        cartrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "delete", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <Cart> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Cart
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody Cart entity) throws Exception {
        cartrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "update", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <Cart> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Cart>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<Cart> entity, @RequestHeader("isArray") boolean request) throws Exception {
        cartrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "update", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Cart> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByItemId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByItemId(@RequestBody FindByBean findByBean) throws Exception {
        List<Cart> lstcart = cartrepo.findByItemId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println("SOPRT124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "findByItemId", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Cart> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByUserId(@RequestBody FindByBean findByBean) throws Exception {
        List<Cart> lstcart = cartrepo.findByUserId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println("SOPRT124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "findByUserId", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Cart> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        Cart lstcart = cartrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println("SOPRT124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "findById", "Cart");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @Autowired
    private AddToCart addtocart;

    /**
     * Saves the new  <Cart> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Cart
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody Cart entity) throws Exception {
        com.spartan.pluggable.logger.alarms.AppAlarm appAlarm = Log.getAlarm("SOPRT247100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new com.athena.server.pluggable.utils.bean.ResponseBean(appAlarm);
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
        addtocart.addToCart(entity);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddToCart", "addToCart"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "com.app.server.businessservice.shoppingcontext.retail.AddToCart", "addToCart");
        return new org.springframework.http.ResponseEntity<com.athena.server.pluggable.utils.bean.ResponseBean>(responseBean, httpStatus);
    }
}

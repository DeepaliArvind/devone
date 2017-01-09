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
import com.app.server.repository.shoppingcontext.retail.ProductRepository;
import com.app.shared.shoppingcontext.retail.Product;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Product Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/Product")
public class ProductServiceImpl extends ProductService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ProductRepository<Product> productrepo;

    /**
     * Retrieve list of  <Product> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<Product> lstproduct = productrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Product"));
        responseBean.add("data", lstproduct);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ProductServiceImpl", "findAll", "Product");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve Page-wise list of  <Product> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findPageWiseData", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findPageWiseData(@RequestParam("page") Integer page, @RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws Exception {
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Product"));
        responseBean.add("data", productrepo.findPageWiseData(limit, page));
        responseBean.add("totalSize", productrepo.getTotalPageCount());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ProductServiceImpl", "findPageWiseData", "Product");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <Product> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Product
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody Product entity) throws Exception {
        productrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Product"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ProductServiceImpl", "save", "Product");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <Product> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Product>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<Product> entity, @RequestHeader("isArray") boolean request) throws Exception {
        productrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Product"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ProductServiceImpl", "save", "Product");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <Product> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        productrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Product"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ProductServiceImpl", "delete", "Product");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <Product> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Product
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody Product entity) throws Exception {
        productrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Product"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ProductServiceImpl", "update", "Product");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <Product> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Product>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<Product> entity, @RequestHeader("isArray") boolean request) throws Exception {
        productrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SOPRT123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Product"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ProductServiceImpl", "update", "Product");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Product> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        Product lstproduct = productrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SOPRT124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Product"));
        responseBean.add("data", lstproduct);
        Log.out.println("SOPRT124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductServiceImpl", "findById", "Product");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}

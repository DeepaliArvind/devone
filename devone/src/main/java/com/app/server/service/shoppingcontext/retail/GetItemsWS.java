package com.app.server.service.shoppingcontext.retail;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.server.businessservice.shoppingcontext.retail.GetItems;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping("/GetItemsWS")
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "GetItemsWS", complexity = Complexity.HIGH)
public class GetItemsWS {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private GetItems getitems;

    @RequestMapping(value = "/getItems", consumes = "application/json", method = RequestMethod.POST)
    public HttpEntity<ResponseBean> getItems() throws Exception {
        com.spartan.pluggable.logger.alarms.AppAlarm appAlarm = Log.getAlarm("SOPRT247100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new com.athena.server.pluggable.utils.bean.ResponseBean(appAlarm);
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
        java.util.List<com.app.shared.shoppingcontext.FetchItemsRM> _ruleOutput = getitems.getItems();
        responseBean.add("success", true);
        responseBean.add("message", "Successfully executed ");
        responseBean.add("data", _ruleOutput);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "com.app.server.businessservice.shoppingcontext.retail.GetItems", "getItems");
        return new org.springframework.http.ResponseEntity<com.athena.server.pluggable.utils.bean.ResponseBean>(responseBean, httpStatus);
    }
}

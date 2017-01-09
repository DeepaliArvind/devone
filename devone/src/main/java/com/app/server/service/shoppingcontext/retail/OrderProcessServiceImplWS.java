package com.app.server.service.shoppingcontext.retail;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.server.businessservice.shoppingcontext.retail.OrderProcessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import org.springframework.http.HttpStatus;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.shoppingcontext.retail.PaymentDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/OrderProcessServiceImplWS")
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "OrderProcessServiceImplWS", complexity = Complexity.HIGH)
public class OrderProcessServiceImplWS {

    @Autowired
    private OrderProcessServiceImpl orderprocessserviceimpl;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @RequestMapping(value = "/processOrder", consumes = "application/json", method = RequestMethod.POST)
    public HttpEntity<ResponseBean> processOrder(@RequestBody PaymentDetails paymentDetails) throws Exception {
        AppAlarm appAlarm = Log.getAlarm("null");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        com.app.shared.shoppingcontext.retail.TransactionResponse transactionresponse = orderprocessserviceimpl.processOrder(paymentDetails);
        responseBean.add("data", transactionresponse);
        responseBean.add("success", true);
        responseBean.add("message", String.format(appAlarm.getMessage(), "OrderProcessServiceImpl"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "OrderProcessServiceImpl", "processOrder", "OrderProcessServiceImpl", "processOrder");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}

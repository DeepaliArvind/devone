package com.app.server.businessservice.shoppingcontext.retail;
import org.springframework.stereotype.Service;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.deo.camel.utility.ExternalIntegrationCaller;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.shoppingcontext.retail.PaymentDetails;
import com.app.shared.shoppingcontext.retail.TransactionResponse;
import org.apache.commons.lang.exception.ExceptionUtils;

@Service
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "OrderProcessServiceImpl", complexity = Complexity.HIGH)
public class OrderProcessServiceImpl {

    @Autowired
    private ExternalIntegrationCaller externalIntegrationCaller;

    public TransactionResponse processOrder(PaymentDetails paymentDetails) throws Exception {
        try {
            java.util.HashMap map = new java.util.HashMap();
            map.put("paymentDetails", paymentDetails);
            com.app.shared.shoppingcontext.retail.TransactionResponse transactionresponse = (com.app.shared.shoppingcontext.retail.TransactionResponse) externalIntegrationCaller.executeRoute("8C490B7C-D8B5-4C67-8CE3-0DDD54AB0547", map);
            return transactionresponse;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

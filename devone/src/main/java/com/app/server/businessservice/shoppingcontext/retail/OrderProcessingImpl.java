package com.app.server.businessservice.shoppingcontext.retail;
import com.app.server.businessservice.appbasicsetup.usermanagement.DocumentTemplateDomainService;
import com.app.server.businessservice.appbasicsetup.usermanagement.NotificationDomainService;
import com.app.server.businessservice.shoppingcontext.SumSubTotalBzService;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.server.repository.shoppingcontext.retail.CartRepository;
import com.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.app.server.repository.shoppingcontext.retail.OrderMainRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
import com.app.shared.shoppingcontext.retail.Cart;
import com.app.shared.shoppingcontext.retail.Item;
import com.app.shared.shoppingcontext.retail.OrderMain;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.server.session.bizService.SessionDataMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.customexceptions.InvalidCard;
import com.app.customexceptions.TransactioFailed;
import com.app.shared.shoppingcontext.retail.PaymentDetails;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;
import java.lang.Override;

@Component
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "OrderProcessingImpl", complexity = Complexity.HIGH)
public class OrderProcessingImpl implements OrderProcessing {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private SumSubTotalBzService sumSubTotalBzService;

    @Autowired
    private LoginRepository<Login> loginRepository;

    @Autowired
    private CartRepository<Cart> cartRepository;

    @Autowired
    private SessionDataMgtService sessionService;

    @Autowired
    private OrderMainRepository<OrderMain> orderMainRepository;

    @Autowired
    private DocumentTemplateDomainService documentTemplateDomainService;

    @Autowired
    private NotificationDomainService notificationDomainService;

    @Autowired
    private OrderProcessServiceImpl orderProcessServiceImpl;

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Override
    public void pOrderProcessing(PaymentDetails orderProcess) throws InvalidCard, SessionDataNotFoundException, TransactioFailed, Exception {
        java.lang.String userIdFromSession = (java.lang.String) sessionService.getSessionData("userId");
        if (userIdFromSession == null) {
            throw new com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException();
        }
        if (orderProcess != null) {
            if (orderProcess.getCreditCardNo() == null) {
                throw new com.app.customexceptions.InvalidCard("InvalidCard", "SOPRT227302406", null);
            }
            com.app.shared.shoppingcontext.retail.TransactionResponse transactionResponse = orderProcessServiceImpl.processOrder(orderProcess);
            if (transactionResponse != null) {
                if (transactionResponse.getStatus().equals(false)) {
                    throw new com.app.customexceptions.TransactioFailed("TransactioFailed", "SOPRT227303406", null);
                }
                java.util.List<com.app.shared.shoppingcontext.retail.Cart> cartList = cartRepository.findByUserId(userIdFromSession);
                com.app.shared.shoppingcontext.acl.OrderProcessingAcl orderProcessingAcl = new com.app.shared.shoppingcontext.acl.OrderProcessingAcl(cartList);
                com.app.shared.shoppingcontext.retail.OrderMain orderMain = orderMainRepository.save(orderProcessingAcl.mapCartToOrder());
                for (com.app.shared.shoppingcontext.retail.Cart cartListElement : cartList) {
                    com.app.shared.shoppingcontext.retail.Item item = itemRepository.findById(cartListElement.getItemId());
                    item.setStock(item.getStock() - cartListElement.getQty());
                    itemRepository.update(item);
                    cartListElement.getSystemInfo().setActiveStatus(0);
                    cartRepository.update(cartListElement);
                }
                java.util.List<com.app.shared.shoppingcontext.SumSubTotalRM> sumSubTotalRMList1 = sumSubTotalBzService.executeQuerySumSubTotal(orderMain.getOrderMainID());
                for (com.app.shared.shoppingcontext.SumSubTotalRM sumSubTotalRMList1Element : sumSubTotalRMList1) {
                    orderMain.setGrandTotal(sumSubTotalRMList1Element.getOrderdetailSubtotal());
                    orderMain.setOrderDate(new java.sql.Timestamp(java.lang.System.currentTimeMillis()));
                    orderMain.setUserId(runtimeLogInfoHelper.getRuntimeLogInfo().getUserId());
                }
                orderMainRepository.update(orderMain);
                com.app.bean.DocumentTemplate documentTemplate1 = new com.app.bean.DocumentTemplate();
                documentTemplate1.setTemplateId("OrderDetailPdf");
                documentTemplate1.addReference("address", "R.B.Center,Aundh,Pune.");
                documentTemplate1.addReference("emailid", "abc@a.com");
                documentTemplate1.addReference("orderId", orderMain.getOrderMainID());
                documentTemplate1.addReference("phNo", "3352135416541");
                documentTemplate1.addReference("TransactionId", transactionResponse.getTransactionId());
                com.app.bean.DocumentBean documentBean1 = documentTemplateDomainService.createDocument(documentTemplate1);
                java.util.List<com.app.shared.appbasicsetup.usermanagement.Login> loginList1 = loginRepository.findByUserId(userIdFromSession);
                for (com.app.shared.appbasicsetup.usermanagement.Login loginList1Element : loginList1) {
                    com.app.bean.EmailBean emailBean1 = new com.app.bean.EmailBean();
                    emailBean1.addAttachmentPath(documentBean1.getDocumentPath());
                    emailBean1.addRecipient(loginList1Element.getLoginId());
                    emailBean1.setEmailSubject("Order Details");
                    com.app.bean.NotificationResponseBean notificationResponseBean1 = notificationDomainService.sendMail(emailBean1);
                }
            }
        }
    }
}

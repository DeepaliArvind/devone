package com.app.server.businessservice.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.customexceptions.InvalidCard;
import com.app.customexceptions.TransactioFailed;
import com.app.shared.shoppingcontext.retail.PaymentDetails;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "OrderProcessing", complexity = Complexity.HIGH)
public interface OrderProcessing {

    public void pOrderProcessing(PaymentDetails orderProcess) throws InvalidCard, SessionDataNotFoundException, TransactioFailed, Exception;
}

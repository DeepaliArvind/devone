package com.app.server.businessservice.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.CartTotalRM;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "GetCartTotal", complexity = Complexity.HIGH)
public interface GetCartTotal {

    public CartTotalRM getCartTotal() throws SessionDataNotFoundException, BusinessRuleUnableToProceedException, Exception;
}

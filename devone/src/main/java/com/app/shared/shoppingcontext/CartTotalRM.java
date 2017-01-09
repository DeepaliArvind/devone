package com.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "CartTotal", complexity = Complexity.MEDIUM)
public class CartTotalRM implements DTOMapperInterface {

    private Double cartSubtotal;

    public CartTotalRM(Object obj) {
        if (obj != null) {
            cartSubtotal = (obj == null ? null : new java.lang.Double(obj.toString()));
        } else {
            cartSubtotal = null;
        }
    }

    public Double getCartSubtotal() {
        return cartSubtotal;
    }
}

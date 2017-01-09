package com.app.server.businessservice.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.customexceptions.OutOffStock;
import com.app.shared.shoppingcontext.retail.Cart;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "AddToCart", complexity = Complexity.HIGH)
public interface AddToCart {

    public void addToCart(Cart entity) throws OutOffStock, Exception;
}

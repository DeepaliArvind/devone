package com.app.server.businessservice.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.FetchCartTotalRM;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "GetCartItems", complexity = Complexity.HIGH)
public interface GetCartItems {

    public List<FetchCartTotalRM> getCartItems() throws SessionDataNotFoundException, Exception;
}

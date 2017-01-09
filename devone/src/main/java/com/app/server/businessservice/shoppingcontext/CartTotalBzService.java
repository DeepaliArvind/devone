package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.CartTotalRM;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "CartTotalBzService", complexity = Complexity.HIGH)
public interface CartTotalBzService {

    public List<CartTotalRM> executeQueryCartTotal(String userId1) throws Exception;
}

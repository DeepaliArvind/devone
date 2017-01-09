package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.OrderDetailQRM;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "OrderDetailBzService", complexity = Complexity.HIGH)
public interface OrderDetailBzService {

    public List<OrderDetailQRM> executeQueryOrderDetailQ(String orderMainID1) throws Exception;
}

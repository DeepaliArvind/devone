package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.SumSubTotalRM;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "SumSubTotalBzService", complexity = Complexity.HIGH)
public interface SumSubTotalBzService {

    public List<SumSubTotalRM> executeQuerySumSubTotal(String orderMainID1) throws Exception;
}

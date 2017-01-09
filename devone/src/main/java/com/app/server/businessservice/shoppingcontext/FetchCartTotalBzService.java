package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.FetchCartTotalRM;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "FetchCartTotalBzService", complexity = Complexity.HIGH)
public interface FetchCartTotalBzService {

    public List<FetchCartTotalRM> executeQueryFetchCartTotal(String userId1) throws Exception;
}

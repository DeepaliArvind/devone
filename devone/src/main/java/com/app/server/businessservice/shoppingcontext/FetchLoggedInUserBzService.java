package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.FetchLoggedInUserRM;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "FetchLoggedInUserBzService", complexity = Complexity.HIGH)
public interface FetchLoggedInUserBzService {

    public List<FetchLoggedInUserRM> executeQueryFetchLoggedInUser(String userId1) throws Exception;
}

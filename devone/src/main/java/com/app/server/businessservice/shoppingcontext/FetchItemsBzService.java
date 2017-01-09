package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.FetchItemsRM;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "FetchItemsBzService", complexity = Complexity.HIGH)
public interface FetchItemsBzService {

    public List<FetchItemsRM> executeQueryFetchItems() throws Exception;
}

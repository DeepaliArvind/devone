package com.app.server.businessservice.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.FetchItemsRM;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "GetItems", complexity = Complexity.HIGH)
public interface GetItems {

    public List<FetchItemsRM> getItems() throws Exception;
}

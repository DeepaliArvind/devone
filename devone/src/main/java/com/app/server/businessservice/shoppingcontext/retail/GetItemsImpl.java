package com.app.server.businessservice.shoppingcontext.retail;
import com.app.server.businessservice.shoppingcontext.FetchItemsBzService;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.FetchItemsRM;
import java.lang.Override;
import java.util.List;

@Component
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "GetItemsImpl", complexity = Complexity.HIGH)
public class GetItemsImpl implements GetItems {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private FetchItemsBzService fetchItemsBzService;

    @Override
    public List<FetchItemsRM> getItems() throws Exception {
        java.util.List<com.app.shared.shoppingcontext.FetchItemsRM> fetchItemsRMList = fetchItemsBzService.executeQueryFetchItems();
        return fetchItemsRMList;
    }
}

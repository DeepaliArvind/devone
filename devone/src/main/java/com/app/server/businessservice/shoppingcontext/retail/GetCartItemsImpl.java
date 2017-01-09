package com.app.server.businessservice.shoppingcontext.retail;
import com.app.server.businessservice.shoppingcontext.FetchCartTotalBzService;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.server.session.bizService.SessionDataMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.FetchCartTotalRM;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;
import java.lang.Override;
import java.util.List;

@Component
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "GetCartItemsImpl", complexity = Complexity.HIGH)
public class GetCartItemsImpl implements GetCartItems {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private SessionDataMgtService sessionService;

    @Autowired
    private FetchCartTotalBzService fetchCartTotalBzService;

    @Override
    public List<FetchCartTotalRM> getCartItems() throws SessionDataNotFoundException, Exception {
        java.lang.String userIdFromSession = (java.lang.String) sessionService.getSessionData("userId");
        if (userIdFromSession == null) {
            throw new com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException();
        }
        java.util.List<com.app.shared.shoppingcontext.FetchCartTotalRM> fetchCartTotalRMList = fetchCartTotalBzService.executeQueryFetchCartTotal(userIdFromSession);
        return fetchCartTotalRMList;
    }
}

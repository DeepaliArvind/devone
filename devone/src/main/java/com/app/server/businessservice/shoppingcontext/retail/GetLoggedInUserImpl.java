package com.app.server.businessservice.shoppingcontext.retail;
import com.app.server.businessservice.shoppingcontext.FetchLoggedInUserBzService;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.server.session.bizService.SessionDataMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.FetchLoggedInUserRM;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;
import java.lang.Override;

@Component
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "GetLoggedInUserImpl", complexity = Complexity.HIGH)
public class GetLoggedInUserImpl implements GetLoggedInUser {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private FetchLoggedInUserBzService fetchLoggedInUserBzService;

    @Autowired
    private SessionDataMgtService sessionService;

    @Override
    public FetchLoggedInUserRM getLoggedInUser() throws SessionDataNotFoundException, BusinessRuleUnableToProceedException, Exception {
        java.lang.String userIdFromSession = (java.lang.String) sessionService.getSessionData("userId");
        if (userIdFromSession == null) {
            throw new com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException();
        }
        java.util.List<com.app.shared.shoppingcontext.FetchLoggedInUserRM> fetchLoggedInUserRMList = fetchLoggedInUserBzService.executeQueryFetchLoggedInUser(userIdFromSession);
        for (com.app.shared.shoppingcontext.FetchLoggedInUserRM fetchLoggedInUserRMListElement : fetchLoggedInUserRMList) {
            return fetchLoggedInUserRMListElement;
        }
        throw new com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException();
    }
}

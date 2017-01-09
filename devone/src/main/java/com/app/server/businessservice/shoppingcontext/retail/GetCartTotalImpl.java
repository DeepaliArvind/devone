package com.app.server.businessservice.shoppingcontext.retail;
import com.app.server.businessservice.shoppingcontext.CartTotalBzService;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.server.session.bizService.SessionDataMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.CartTotalRM;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;
import java.lang.Override;

@Component
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "GetCartTotalImpl", complexity = Complexity.HIGH)
public class GetCartTotalImpl implements GetCartTotal {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CartTotalBzService cartTotalBzService;

    @Autowired
    private SessionDataMgtService sessionService;

    @Override
    public CartTotalRM getCartTotal() throws SessionDataNotFoundException, BusinessRuleUnableToProceedException, Exception {
        java.lang.String userIdFromSession = (java.lang.String) sessionService.getSessionData("userId");
        if (userIdFromSession == null) {
            throw new com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException();
        }
        java.util.List<com.app.shared.shoppingcontext.CartTotalRM> cartTotalRMList = cartTotalBzService.executeQueryCartTotal(userIdFromSession);
        for (com.app.shared.shoppingcontext.CartTotalRM cartTotalRMListElement : cartTotalRMList) {
            return cartTotalRMListElement;
        }
        throw new com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException();
    }
}

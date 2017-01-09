package com.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "FetchLoggedInUser", complexity = Complexity.MEDIUM)
public class FetchLoggedInUserRM implements DTOMapperInterface {

    private String loginCorecontactsLastname;

    private String lastName;

    private String firstName;

    public FetchLoggedInUserRM(Object[] aryObject) {
        if (aryObject != null) {
            loginCorecontactsLastname = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            lastName = (aryObject[1] == null ? null : new java.lang.String(aryObject[1].toString()));
            firstName = (aryObject[2] == null ? null : new java.lang.String(aryObject[2].toString()));
        } else {
            loginCorecontactsLastname = null;
            lastName = null;
            firstName = null;
        }
    }

    public String getLoginCorecontactsLastname() {
        return loginCorecontactsLastname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}

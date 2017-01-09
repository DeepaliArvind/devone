package com.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "SumSubTotal", complexity = Complexity.MEDIUM)
public class SumSubTotalRM implements DTOMapperInterface {

    private Double orderdetailSubtotal;

    public SumSubTotalRM(Object obj) {
        if (obj != null) {
            orderdetailSubtotal = (obj == null ? null : new java.lang.Double(obj.toString()));
        } else {
            orderdetailSubtotal = null;
        }
    }

    public Double getOrderdetailSubtotal() {
        return orderdetailSubtotal;
    }
}

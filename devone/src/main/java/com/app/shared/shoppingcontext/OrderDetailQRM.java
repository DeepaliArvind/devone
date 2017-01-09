package com.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "OrderDetail", complexity = Complexity.MEDIUM)
public class OrderDetailQRM implements DTOMapperInterface {

    private Integer qty;

    private Double subTotal;

    private String itemName;

    private Double price;

    private String productIdDesc;

    public OrderDetailQRM(Object[] aryObject) {
        if (aryObject != null) {
            qty = (aryObject[0] == null ? null : new java.lang.Integer(aryObject[0].toString()));
            subTotal = (aryObject[1] == null ? null : new java.lang.Double(aryObject[1].toString()));
            itemName = (aryObject[2] == null ? null : new java.lang.String(aryObject[2].toString()));
            price = (aryObject[3] == null ? null : new java.lang.Double(aryObject[3].toString()));
            productIdDesc = (aryObject[4] == null ? null : new java.lang.String(aryObject[4].toString()));
        } else {
            qty = null;
            subTotal = null;
            itemName = null;
            price = null;
            productIdDesc = null;
        }
    }

    public Integer getQty() {
        return qty;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getPrice() {
        return price;
    }

    public String getProductIdDesc() {
        return productIdDesc;
    }
}

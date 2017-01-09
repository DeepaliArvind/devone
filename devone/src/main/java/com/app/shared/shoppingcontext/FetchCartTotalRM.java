package com.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "FetchCartTotal", complexity = Complexity.MEDIUM)
public class FetchCartTotalRM implements DTOMapperInterface {

    private Integer qty;

    private Double subTotal;

    private String itemId;

    private String itemName;

    private String img;

    private Integer stock;

    private Double price;

    private String productIdDesc;

    public FetchCartTotalRM(Object[] aryObject) {
        if (aryObject != null) {
            qty = (aryObject[0] == null ? null : new java.lang.Integer(aryObject[0].toString()));
            subTotal = (aryObject[1] == null ? null : new java.lang.Double(aryObject[1].toString()));
            itemId = (aryObject[2] == null ? null : new java.lang.String(aryObject[2].toString()));
            itemName = (aryObject[3] == null ? null : new java.lang.String(aryObject[3].toString()));
            img = (aryObject[4] == null ? null : new java.lang.String(aryObject[4].toString()));
            stock = (aryObject[5] == null ? null : new java.lang.Integer(aryObject[5].toString()));
            price = (aryObject[6] == null ? null : new java.lang.Double(aryObject[6].toString()));
            productIdDesc = (aryObject[7] == null ? null : new java.lang.String(aryObject[7].toString()));
        } else {
            qty = null;
            subTotal = null;
            itemId = null;
            itemName = null;
            img = null;
            stock = null;
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

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getImg() {
        return img;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public String getProductIdDesc() {
        return productIdDesc;
    }
}

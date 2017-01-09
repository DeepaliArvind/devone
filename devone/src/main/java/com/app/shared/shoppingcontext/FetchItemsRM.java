package com.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "FetchItems", complexity = Complexity.MEDIUM)
public class FetchItemsRM implements DTOMapperInterface {

    private String itemId;

    private String itemName;

    private String img;

    private Integer stock;

    private Double price;

    private String productIdDesc;

    public FetchItemsRM(Object[] aryObject) {
        if (aryObject != null) {
            itemId = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            itemName = (aryObject[1] == null ? null : new java.lang.String(aryObject[1].toString()));
            img = (aryObject[2] == null ? null : new java.lang.String(aryObject[2].toString()));
            stock = (aryObject[3] == null ? null : new java.lang.Integer(aryObject[3].toString()));
            price = (aryObject[4] == null ? null : new java.lang.Double(aryObject[4].toString()));
            productIdDesc = (aryObject[5] == null ? null : new java.lang.String(aryObject[5].toString()));
        } else {
            itemId = null;
            itemName = null;
            img = null;
            stock = null;
            price = null;
            productIdDesc = null;
        }
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

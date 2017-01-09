package com.app.shared.shoppingcontext.retail;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import java.io.Serializable;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "ItemTemplate", complexity = Complexity.MEDIUM)
@XmlRootElement
public class ItemTemplate implements EntityValidatorInterface, Serializable {

    private static final long serialVersionUID = 1483948754188L;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    @Min(1)
    @Max(65535)
    private String productIdDesc;

    @Min(1)
    @Max(65535)
    private String itemName;

    @Min(1)
    @Max(65535)
    private String img;

    @Min(-2147483648L)
    @Max(2147483647)
    private Integer price;

    @Min(-2147483648L)
    @Max(2147483647)
    private Integer qty;

    @Min(1)
    @Max(65535)
    private String itemId;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public String getProductIdDesc() {
        return productIdDesc;
    }

    public void setProductIdDesc(String productIdDesc) {
        this.productIdDesc = productIdDesc;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> validateFactory) {
        this.dtoValidator = validateFactory;
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.dtoValidator != null) {
            isValid = this.dtoValidator.validateEntity(this);
            this.isDtoValidated = true;
        } else {
            throw new SecurityException();
        }
        return isValid;
    }
}

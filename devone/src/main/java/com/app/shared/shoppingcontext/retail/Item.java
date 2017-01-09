package com.app.shared.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import javax.persistence.SqlResultSetMapping;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "Item")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Item", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "ItemResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.shoppingcontext.retail.Item.class, columns = { @javax.persistence.ColumnResult(name = "itemId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "productId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "productProductIdDesc", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "itemName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "img", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stock", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "price", type = java.lang.Double.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "Item.findAll", query = " select u from Item u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Item.findByProductId", query = "select e from Item e where e.systemInfo.activeStatus=1 and e.productId=:productId"), @javax.persistence.NamedQuery(name = "Item.findById", query = "select e from Item e where e.systemInfo.activeStatus=1 and e.itemId =:itemId") })
public class Item implements Serializable, CommonEntityInterface, Comparator<Item> {

    private static final long serialVersionUID = 1483948531908L;

    @Column(name = "itemName")
    @JsonProperty("itemName")
    @NotNull
    @Size(min = 1, max = 256)
    private String itemName;

    @Column(name = "img")
    @JsonProperty("img")
    @NotNull
    @Size(min = 1, max = 256)
    private String img;

    @Column(name = "stock")
    @JsonProperty("stock")
    @NotNull
    @Min(-2147483648L)
    @Max(2147483647)
    private Integer stock;

    @Column(name = "price")
    @JsonProperty("price")
    @NotNull
    @Min(-9223372000000000000L)
    @Max(9223372000000000000L)
    private Double price;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "itemId")
    @JsonProperty("itemId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String itemId;

    @Column(name = "productId")
    @JsonProperty("productId")
    private String productId;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private Integer versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    @Transient
    @JsonIgnore
    private boolean isEntityValidated = false;

    @Transient
    private String productProductIdDesc;

    public Item() {
    }

    public Item(String itemId, String productId, String productProductIdDesc, String itemName, String img, Integer stock, Double price) {
        this.itemId = itemId;
        this.productId = productId;
        this.productProductIdDesc = productProductIdDesc;
        this.itemName = itemName;
        this.img = img;
        this.stock = stock;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        if (itemName != null) {
            this.itemName = itemName;
        }
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        if (img != null) {
            this.img = img;
        }
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        if (stock != null) {
            this.stock = stock;
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price != null) {
            this.price = price;
        }
    }

    public String getPrimaryKey() {
        return itemId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public void setPrimaryDisplay(String primaryDisplay) {
        this.primaryDisplay = primaryDisplay;
    }

    /**
     * Returns boolean value if System information's active status =-1
     * @return boolean
     */
    @JsonIgnore
    public boolean isHardDelete() {
        boolean isHardDelete = false;
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            isHardDelete = true;
        } else {
            isHardDelete = false;
        }
        return isHardDelete;
    }

    /**
     * Validates the entity fields based on java.validation.constraints annotaions and sets isEntityValided value in System information object
     * @return boolean
     * @throws java.lang.SecurityException
     */
    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    /**
     * Sets EntityValidator object
     * @param validateFactory
     */
    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> validateFactory) {
        this.entityValidator = validateFactory;
    }

    /**
     * Creates a new entity audit object and  if primarykey fields are null then sets values in the entity audit field.
     * @param customerId
     * @param userId
     */
    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    /**
     * Creates a new entity audit object and System Information object and based on @params RECORD_TYPE sets created by and updated by values in the entity audit field.
     * @param CustomerId
     * @param userId
     * @param RECORD_TYPE
     */
    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    /**
     * Returns Logged in user informatio.
     * @return auditInfo
     */
    @JsonIgnore
    public String getLoggedInUserInfo() {
        return "";
    }

    /**
     * Creates new System Information object.
     * @param RECORD_TYPE
     */
    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    /**
     * Sets active status in System Information object.
     * @param activeStatus
     */
    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    /**
     * Returns system information object.
     * @return systemInfo
     */
    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    /**
     * Creates System information obect if null and sets transaction access code in that object.
     * @param transactionAccessCode
     */
    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
    }

    public String getProductProductIdDesc() {
        return productProductIdDesc;
    }

    public void setProductProductIdDesc(String productProductIdDesc) {
        this.productProductIdDesc = productProductIdDesc;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param Item
     * @param Item
     */
    @Override
    public int compare(Item object1, Item object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((itemName == null ? " " : itemName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (itemId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = itemId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.shoppingcontext.retail.Item other = (com.app.shared.shoppingcontext.retail.Item) obj;
            if (itemId == null) {
                isEquals = false;
            } else if (!itemId.equals(other.itemId)) {
                isEquals = false;
            }
        } catch (java.lang.Exception ignore) {
            isEquals = false;
        }
        return isEquals;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isEntityValidated;
    }

    @JsonIgnore
    public void setEntityValidated(boolean isEntityValidated) {
        this.isEntityValidated = isEntityValidated;
    }
}

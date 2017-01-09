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
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import javax.persistence.SqlResultSetMapping;
import java.lang.Override;

@Table(name = "OrderDetail")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "OrderDetail", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "OrderDetailResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.shoppingcontext.retail.OrderDetail.class, columns = { @javax.persistence.ColumnResult(name = "orderDetailId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "itemId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "itemItemName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "qty", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "subTotal", type = java.lang.Double.class) }))
public class OrderDetail implements Serializable, CommonEntityInterface, Comparator<OrderDetail> {

    private static final long serialVersionUID = 1483948534976L;

    @Column(name = "qty")
    @JsonProperty("qty")
    @NotNull
    @Min(-2147483648L)
    @Max(2147483647)
    private Integer qty;

    @Column(name = "subTotal")
    @JsonProperty("subTotal")
    @NotNull
    @Min(-9223372000000000000L)
    @Max(9223372000000000000L)
    private Double subTotal;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "orderDetailId")
    @JsonProperty("orderDetailId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String orderDetailId;

    @Column(name = "itemId")
    @JsonProperty("itemId")
    private String itemId;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderMainID", referencedColumnName = "orderMainID")
    private OrderMain orderMain;

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
    private String itemItemName;

    public OrderDetail() {
    }

    public OrderDetail(String orderDetailId, String itemId, String itemItemName, Integer qty, Double subTotal) {
        this.orderDetailId = orderDetailId;
        this.itemId = itemId;
        this.itemItemName = itemItemName;
        this.qty = qty;
        this.subTotal = subTotal;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        if (qty != null) {
            this.qty = qty;
        }
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        if (subTotal != null) {
            this.subTotal = subTotal;
        }
    }

    public String getPrimaryKey() {
        return orderDetailId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return orderDetailId;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public OrderMain getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMain orderMain) {
        this.orderMain = orderMain;
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

    public String getItemItemName() {
        return itemItemName;
    }

    public void setItemItemName(String itemItemName) {
        this.itemItemName = itemItemName;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (orderDetailId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = orderDetailId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.shoppingcontext.retail.OrderDetail other = (com.app.shared.shoppingcontext.retail.OrderDetail) obj;
            if (orderDetailId == null) {
                isEquals = false;
            } else if (!orderDetailId.equals(other.orderDetailId)) {
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

    @Transient
    @JsonIgnore
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String _fieldName) {
        this.fieldName = _fieldName;
    }

    @Override
    public int compare(OrderDetail object1, OrderDetail object2) {
        switch(((fieldName))) {
            case "orderDetailId":
                return (object1.getOrderDetailId().compareTo(object2.getOrderDetailId()) == 0) ? 0 : ((object1.getOrderDetailId().compareTo(object2.getOrderDetailId()) > 0) ? 1 : -1);
        }
        return 0;
    }
}

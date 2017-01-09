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
import java.sql.Timestamp;
import javax.persistence.Column;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import javax.persistence.SqlResultSetMapping;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "OrderMain")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "OrderMain", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "OrderMainResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.shoppingcontext.retail.OrderMain.class, columns = { @javax.persistence.ColumnResult(name = "orderMainID", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "orderDate", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "userId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userdetailAuthAlgo", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "grandTotal", type = java.lang.Double.class) }))
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "orderMainID")
@NamedQueries({ @javax.persistence.NamedQuery(name = "OrderMain.findAll", query = " select u from OrderMain u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "OrderMain.findByUserId", query = "select e from OrderMain e where e.systemInfo.activeStatus=1 and e.userId=:userId"), @javax.persistence.NamedQuery(name = "OrderMain.findById", query = "select e from OrderMain e where e.systemInfo.activeStatus=1 and e.orderMainID =:orderMainID") })
public class OrderMain implements Serializable, CommonEntityInterface, Comparator<OrderMain> {

    private static final long serialVersionUID = 1483948533764L;

    @Column(name = "orderDate")
    @JsonProperty("orderDate")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp orderDate;

    @Column(name = "grandTotal")
    @JsonProperty("grandTotal")
    @Max(9223372000000000000L)
    private Double grandTotal;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "orderMainID")
    @JsonProperty("orderMainID")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String orderMainID;

    @Column(name = "userId")
    @JsonProperty("userId")
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orderMain")
    private List<OrderDetail> orderDetail;

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
    private String userdetailAuthAlgo;

    public OrderMain() {
    }

    public OrderMain(String orderMainID, Timestamp orderDate, String userId, String userdetailAuthAlgo, Double grandTotal) {
        this.orderMainID = orderMainID;
        this.orderDate = orderDate;
        this.userId = userId;
        this.userdetailAuthAlgo = userdetailAuthAlgo;
        this.grandTotal = grandTotal;
    }

    public Timestamp getOrderDate() {
        return (orderDate == null ? orderDate : new Timestamp(orderDate.getTime()));
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = (orderDate == null ? orderDate : new Timestamp(orderDate.getTime()));
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getPrimaryKey() {
        return orderMainID;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return orderMainID;
    }

    public String getOrderMainID() {
        return orderMainID;
    }

    public void setOrderMainID(String orderMainID) {
        this.orderMainID = orderMainID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public OrderMain addOrderDetail(OrderDetail orderDetail) {
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<com.app.shared.shoppingcontext.retail.OrderDetail>();
        }
        if (orderDetail != null) {
            orderDetail.setOrderMain(this);
            this.orderDetail.add(orderDetail);
        }
        return this;
    }

    public OrderMain removeOrderDetail(OrderDetail orderDetail) {
        if (this.orderDetail != null) {
            this.orderDetail.remove(orderDetail);
        }
        return this;
    }

    public OrderMain addAllOrderDetail(List<OrderDetail> orderDetail) {
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<com.app.shared.shoppingcontext.retail.OrderDetail>();
        }
        if (orderDetail != null) {
            for (int i = 0; i < orderDetail.size(); i++) {
                orderDetail.get(i).setOrderMain(this);
            }
            this.orderDetail.addAll(orderDetail);
        }
        return this;
    }

    @JsonIgnore
    public Integer getTotalNumberOfOrderDetail() {
        int count = 0;
        if (this.orderDetail != null) {
            count = this.orderDetail.size();
        }
        return count;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        for (int i = 0; i < orderDetail.size(); i++) {
            if (orderDetail.get(i).getOrderMain() == null) {
                orderDetail.get(i).setOrderMain(this);
            }
        }
        this.orderDetail = orderDetail;
    }

    @JsonIgnore
    public List<OrderDetail> getDeletedOrderDetailList() {
        List<OrderDetail> orderdetailToRemove = new java.util.ArrayList<OrderDetail>();
        for (OrderDetail orderdetail : this.orderDetail) {
            if (orderdetail.isHardDelete()) {
                orderdetailToRemove.add(orderdetail);
            }
        }
        orderDetail.removeAll(orderdetailToRemove);
        return orderdetailToRemove;
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
        setValidatororderDetail(validateFactory);
    }

    private void setValidatororderDetail(EntityValidatorHelper<Object> validateFactory) {
        if (orderDetail != null) {
            for (int i = 0; i < orderDetail.size(); i++) {
                orderDetail.get(i).setEntityValidator(validateFactory);
            }
        }
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
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId, recordType);
            orderDetail.setSystemInformation(recordType);
        }
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
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
        }
        if (this.orderDetail == null) {
            this.orderDetail = new java.util.ArrayList<OrderDetail>();
        }
        for (OrderDetail orderDetail : this.orderDetail) {
            orderDetail.setEntityAudit(customerId, userId);
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
            this.systemInfo.setActiveStatus(-1);
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

    public String getUserdetailAuthAlgo() {
        return userdetailAuthAlgo;
    }

    public void setUserdetailAuthAlgo(String userdetailAuthAlgo) {
        this.userdetailAuthAlgo = userdetailAuthAlgo;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param OrderMain
     * @param OrderMain
     */
    @Override
    public int compare(OrderMain object1, OrderMain object2) {
        return 0;
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
        if (orderMainID == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = orderMainID.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.shoppingcontext.retail.OrderMain other = (com.app.shared.shoppingcontext.retail.OrderMain) obj;
            if (orderMainID == null) {
                isEquals = false;
            } else if (!orderMainID.equals(other.orderMainID)) {
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

    public Double calTax() {
        return grandTotal * 0.0125;
    }
}

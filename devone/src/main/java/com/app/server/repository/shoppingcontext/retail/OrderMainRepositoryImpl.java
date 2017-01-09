package com.app.server.repository.shoppingcontext.retail;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.shoppingcontext.retail.OrderMain;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import org.eclipse.persistence.internal.jpa.EJBQueryImpl;
import org.eclipse.persistence.queries.DatabaseQuery;
import java.lang.Override;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import com.app.shared.shoppingcontext.retail.OrderDetail;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for OrderMain Transaction table", complexity = Complexity.MEDIUM)
public class OrderMainRepositoryImpl extends CommonUtilRepositoryImpl implements OrderMainRepository<OrderMain> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    private final String PAGE_WISE_FIND_ALL = "SELECT ordermain.orderMainID as orderMainID,ordermain.orderDate as orderDate,ordermain.userId as userId,userdetail.authAlgo as userdetailAuthAlgo,ordermain.grandTotal as grandTotal FROM OrderMain ordermain LEFT JOIN UserDetail userdetail ON ordermain.userId=userdetail.userId WHERE ordermain.activeStatus=1";

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<OrderMain> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<OrderMain> query = emanager.createNamedQuery("OrderMain.findAll").getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <OrderMain> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        String countquery = "SELECT COUNT(*)  FROM (" + PAGE_WISE_FIND_ALL + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <OrderMain>
     * @return List<OrderMain>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<OrderMain> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNativeQuery(PAGE_WISE_FIND_ALL, "OrderMainResultSetMapping");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<OrderMain> listOfOrderMain = query.getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfOrderMain.size());
        return listOfOrderMain;
    }

    /**
     * Saves the new  <OrderMain> object.
     * @return OrderMain
     * @Params Object of OrderMain
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public OrderMain save(OrderMain entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SOPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <OrderMain> object.
     * @return java.util.List<OrderMain>
     * @Params list of OrderMain
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<OrderMain> save(List<OrderMain> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (OrderMain orderMain : entity) {
            emanager.persist(orderMain);
        }
        Log.out.println("SOPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <OrderMain> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        OrderMain orderMain = emanager.find(com.app.shared.shoppingcontext.retail.OrderMain.class, id);
        orderMain.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(orderMain);
        Log.out.println("SOPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <OrderMain> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        OrderMain orderMain = emanager.find(com.app.shared.shoppingcontext.retail.OrderMain.class, id);
        emanager.remove(orderMain);
        Log.out.println("SOPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "remove", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteOrderDetail(List<OrderDetail> orderdetail) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.app.shared.shoppingcontext.retail.OrderDetail _orderdetail : orderdetail) {
            com.app.shared.shoppingcontext.retail.OrderDetail s = emanager.find(com.app.shared.shoppingcontext.retail.OrderDetail.class, _orderdetail.getOrderDetailId());
            emanager.remove(s);
        }
    }

    /**
     * Updates the <OrderMain> object.
     * @Params Object of OrderMain
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(OrderMain entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SOPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <OrderMain> object.
     * @Params list of OrderMain
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<OrderMain> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (OrderMain orderMain : entity) {
            emanager.merge(orderMain);
        }
        Log.out.println("SOPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of OrderMain objects by filtering on refernce key <userId>
     * @return List<OrderMain>
     * @Params userId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<OrderMain> findByUserId(String userId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("OrderMain.findByUserId");
        query.setParameter("userId", userId);
        java.util.List<com.app.shared.shoppingcontext.retail.OrderMain> listOfOrderMain = query.getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findByUserId", "Total Records Fetched = " + listOfOrderMain.size());
        return listOfOrderMain;
    }

    /**
     * Return OrderMain object by filtering on refernce key <orderMainID>
     * @return OrderMain
     * @Params orderMainID of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public OrderMain findById(String orderMainID) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("OrderMain.findById");
        query.setParameter("orderMainID", orderMainID);
        OrderMain listOfOrderMain = (OrderMain) query.getSingleResult();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findById", "Total Records Fetched = " + listOfOrderMain);
        return listOfOrderMain;
    }
}

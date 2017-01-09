package com.app.server.repository.shoppingcontext.retail;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.shoppingcontext.retail.Cart;
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

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Cart Transaction table", complexity = Complexity.MEDIUM)
public class CartRepositoryImpl extends CommonUtilRepositoryImpl implements CartRepository<Cart> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    private final String PAGE_WISE_FIND_ALL = "SELECT cart.cartid as cartid,cart.itemId as itemId,item.itemName as itemItemName,cart.qty as qty,cart.subTotal as subTotal,cart.userId as userId,userdetail.authAlgo as userdetailAuthAlgo FROM Cart cart LEFT JOIN Item item ON cart.itemId=item.itemId LEFT JOIN UserDetail userdetail ON cart.userId=userdetail.userId WHERE cart.activeStatus=1";

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Cart> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Cart> query = emanager.createNamedQuery("Cart.findAll").getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Cart> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        String countquery = "SELECT COUNT(*)  FROM (" + PAGE_WISE_FIND_ALL + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Cart>
     * @return List<Cart>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Cart> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNativeQuery(PAGE_WISE_FIND_ALL, "CartResultSetMapping");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Cart> listOfCart = query.getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfCart.size());
        return listOfCart;
    }

    /**
     * Saves the new  <Cart> object.
     * @return Cart
     * @Params Object of Cart
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Cart save(Cart entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SOPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Cart> object.
     * @return java.util.List<Cart>
     * @Params list of Cart
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Cart> save(List<Cart> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Cart cart : entity) {
            emanager.persist(cart);
        }
        Log.out.println("SOPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Cart> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Cart cart = emanager.find(com.app.shared.shoppingcontext.retail.Cart.class, id);
        cart.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(cart);
        Log.out.println("SOPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Cart> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Cart cart = emanager.find(com.app.shared.shoppingcontext.retail.Cart.class, id);
        emanager.remove(cart);
        Log.out.println("SOPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <Cart> object.
     * @Params Object of Cart
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Cart entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SOPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Cart> object.
     * @Params list of Cart
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Cart> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Cart cart : entity) {
            emanager.merge(cart);
        }
        Log.out.println("SOPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of Cart objects by filtering on refernce key <itemId>
     * @return List<Cart>
     * @Params itemId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Cart> findByItemId(String itemId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Cart.findByItemId");
        query.setParameter("itemId", itemId);
        java.util.List<com.app.shared.shoppingcontext.retail.Cart> listOfCart = query.getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findByItemId", "Total Records Fetched = " + listOfCart.size());
        return listOfCart;
    }

    /**
     * Return list of Cart objects by filtering on refernce key <userId>
     * @return List<Cart>
     * @Params userId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Cart> findByUserId(String userId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Cart.findByUserId");
        query.setParameter("userId", userId);
        java.util.List<com.app.shared.shoppingcontext.retail.Cart> listOfCart = query.getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findByUserId", "Total Records Fetched = " + listOfCart.size());
        return listOfCart;
    }

    /**
     * Return Cart object by filtering on refernce key <cartid>
     * @return Cart
     * @Params cartid of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Cart findById(String cartid) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Cart.findById");
        query.setParameter("cartid", cartid);
        Cart listOfCart = (Cart) query.getSingleResult();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findById", "Total Records Fetched = " + listOfCart);
        return listOfCart;
    }
}

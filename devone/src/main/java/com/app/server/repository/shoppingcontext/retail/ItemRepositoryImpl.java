package com.app.server.repository.shoppingcontext.retail;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.shoppingcontext.retail.Item;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Item Transaction table", complexity = Complexity.MEDIUM)
public class ItemRepositoryImpl extends CommonUtilRepositoryImpl implements ItemRepository<Item> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    private final String PAGE_WISE_FIND_ALL = "SELECT item.itemId as itemId,item.productId as productId,product.productIdDesc as productProductIdDesc,item.itemName as itemName,item.img as img,item.stock as stock,item.price as price FROM Item item LEFT JOIN Product product ON item.productId=product.productId WHERE item.activeStatus=1";

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Item> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Item> query = emanager.createNamedQuery("Item.findAll").getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Item> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        String countquery = "SELECT COUNT(*)  FROM (" + PAGE_WISE_FIND_ALL + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Item>
     * @return List<Item>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Item> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNativeQuery(PAGE_WISE_FIND_ALL, "ItemResultSetMapping");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Item> listOfItem = query.getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfItem.size());
        return listOfItem;
    }

    /**
     * Saves the new  <Item> object.
     * @return Item
     * @Params Object of Item
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Item save(Item entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SOPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Item> object.
     * @return java.util.List<Item>
     * @Params list of Item
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Item> save(List<Item> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Item item : entity) {
            emanager.persist(item);
        }
        Log.out.println("SOPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Item> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Item item = emanager.find(com.app.shared.shoppingcontext.retail.Item.class, id);
        item.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(item);
        Log.out.println("SOPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Item> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Item item = emanager.find(com.app.shared.shoppingcontext.retail.Item.class, id);
        emanager.remove(item);
        Log.out.println("SOPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <Item> object.
     * @Params Object of Item
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Item entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SOPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Item> object.
     * @Params list of Item
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Item> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Item item : entity) {
            emanager.merge(item);
        }
        Log.out.println("SOPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of Item objects by filtering on refernce key <productId>
     * @return List<Item>
     * @Params productId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Item> findByProductId(String productId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Item.findByProductId");
        query.setParameter("productId", productId);
        java.util.List<com.app.shared.shoppingcontext.retail.Item> listOfItem = query.getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "findByProductId", "Total Records Fetched = " + listOfItem.size());
        return listOfItem;
    }

    /**
     * Return Item object by filtering on refernce key <itemId>
     * @return Item
     * @Params itemId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Item findById(String itemId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Item.findById");
        query.setParameter("itemId", itemId);
        Item listOfItem = (Item) query.getSingleResult();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ItemRepositoryImpl", "findById", "Total Records Fetched = " + listOfItem);
        return listOfItem;
    }
}

package com.app.server.repository.shoppingcontext.retail;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.shoppingcontext.retail.Product;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Product Master table Entity", complexity = Complexity.LOW)
public class ProductRepositoryImpl extends CommonUtilRepositoryImpl implements ProductRepository<Product> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Product> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Product> query = emanager.createNamedQuery("Product.findAll").getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Product> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("Product.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Product>
     * @return List<Product>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Product> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Product.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Product> listOfProduct = query.getResultList();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfProduct.size());
        return listOfProduct;
    }

    /**
     * Saves the new  <Product> object.
     * @return Product
     * @Params Object of Product
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Product save(Product entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SOPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Product> object.
     * @return java.util.List<Product>
     * @Params list of Product
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Product> save(List<Product> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Product product : entity) {
            emanager.persist(product);
        }
        Log.out.println("SOPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Product> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Product product = emanager.find(com.app.shared.shoppingcontext.retail.Product.class, id);
        product.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(product);
        Log.out.println("SOPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Product> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Product product = emanager.find(com.app.shared.shoppingcontext.retail.Product.class, id);
        emanager.remove(product);
        Log.out.println("SOPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <Product> object.
     * @Params Object of Product
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Product entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SOPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Product> object.
     * @Params list of Product
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Product> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Product product : entity) {
            emanager.merge(product);
        }
        Log.out.println("SOPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Product object by filtering on refernce key <productId>
     * @return Product
     * @Params productId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Product findById(String productId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Product.findById");
        query.setParameter("productId", productId);
        Product listOfProduct = (Product) query.getSingleResult();
        Log.out.println("SOPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ProductRepositoryImpl", "findById", "Total Records Fetched = " + listOfProduct);
        return listOfProduct;
    }
}

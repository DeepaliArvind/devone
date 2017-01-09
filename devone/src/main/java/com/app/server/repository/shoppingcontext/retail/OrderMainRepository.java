package com.app.server.repository.shoppingcontext.retail;
import com.app.server.repository.core.CommonUtilRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.shoppingcontext.retail.OrderDetail;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for OrderMain Transaction table", complexity = Complexity.MEDIUM)
public interface OrderMainRepository<T> extends CommonUtilRepository {

    List<T> findAll() throws Exception;

    long getTotalPageCount() throws Exception;

    List<T> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void remove(String id) throws Exception;

    public void deleteOrderDetail(List<OrderDetail> orderdetail);

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByUserId(String userId) throws Exception;

    T findById(String orderMainID) throws Exception;
}

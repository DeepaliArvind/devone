package com.app.server.service.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.shoppingcontext.retail.OrderMain;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for OrderMain Transaction table", complexity = Complexity.MEDIUM)
public abstract class OrderMainService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> findPageWiseData(Integer page, Integer start, Integer limit) throws Exception;

    abstract HttpEntity<ResponseBean> save(OrderMain entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<OrderMain> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(OrderMain entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<OrderMain> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByUserId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}

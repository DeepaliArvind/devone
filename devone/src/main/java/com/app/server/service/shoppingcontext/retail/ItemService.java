package com.app.server.service.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.shoppingcontext.retail.Item;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Item Transaction table", complexity = Complexity.MEDIUM)
public abstract class ItemService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> findPageWiseData(Integer page, Integer start, Integer limit) throws Exception;

    abstract HttpEntity<ResponseBean> save(Item entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Item> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Item entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Item> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByProductId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}

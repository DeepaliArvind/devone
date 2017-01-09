package com.app.server.service.shoppingcontext.retail;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.shoppingcontext.retail.Cart;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Cart Transaction table", complexity = Complexity.MEDIUM)
public abstract class CartService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> findPageWiseData(Integer page, Integer start, Integer limit) throws Exception;

    abstract HttpEntity<ResponseBean> save(Cart entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Cart> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Cart entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Cart> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByItemId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByUserId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}

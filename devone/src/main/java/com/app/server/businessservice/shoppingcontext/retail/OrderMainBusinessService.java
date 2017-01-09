package com.app.server.businessservice.shoppingcontext.retail;
import com.app.shared.shoppingcontext.retail.OrderMain;
import java.util.List;

public interface OrderMainBusinessService {

    void update(OrderMain entity) throws Exception;

    void update(List<OrderMain> entity) throws Exception;
}

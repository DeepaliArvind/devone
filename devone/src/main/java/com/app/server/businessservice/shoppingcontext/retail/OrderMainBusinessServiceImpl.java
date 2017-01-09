package com.app.server.businessservice.shoppingcontext.retail;
import com.app.server.repository.shoppingcontext.retail.OrderMainRepository;
import com.app.shared.shoppingcontext.retail.OrderMain;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderMainBusinessServiceImpl implements OrderMainBusinessService {

    @Autowired
    private OrderMainRepository orderMainRepository;

    /**
     * Update the <OrderMain> object
     * @Params Object of OrderMain
     * @throws java.lang.Exception
     */
    @Override
    public void update(OrderMain entity) throws Exception {
        if (entity.isHardDelete()) {
            orderMainRepository.delete(entity.getOrderMainID());
        } else {
            orderMainRepository.deleteOrderDetail(entity.getDeletedOrderDetailList());
            orderMainRepository.deleteOrderDetail(entity.getDeletedOrderDetailList());
            orderMainRepository.deleteOrderDetail(entity.getDeletedOrderDetailList());
            orderMainRepository.deleteOrderDetail(entity.getDeletedOrderDetailList());
            orderMainRepository.update(entity);
        }
    }

    /**
     * Update the list of <OrderMain> object
     * @Params List of OrderMain Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<OrderMain> entity) throws Exception {
        for (OrderMain ordermain : entity) {
            if (ordermain.isHardDelete()) {
                orderMainRepository.delete(ordermain.getOrderMainID());
            } else {
                orderMainRepository.deleteOrderDetail(ordermain.getDeletedOrderDetailList());
                orderMainRepository.deleteOrderDetail(ordermain.getDeletedOrderDetailList());
                orderMainRepository.deleteOrderDetail(ordermain.getDeletedOrderDetailList());
                orderMainRepository.deleteOrderDetail(ordermain.getDeletedOrderDetailList());
                orderMainRepository.update(ordermain);
            }
        }
    }
}

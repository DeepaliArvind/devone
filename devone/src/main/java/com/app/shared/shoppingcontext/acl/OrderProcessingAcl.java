package com.app.shared.shoppingcontext.acl;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.shoppingcontext.retail.Cart;
import java.util.List;
import com.app.shared.shoppingcontext.retail.OrderMain;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "OrderProcessingAcl", complexity = Complexity.MEDIUM)
public class OrderProcessingAcl {

    public OrderProcessingAcl(List<Cart> _cart) {
        this.cartInput = _cart;
        this.doMapping();
    }

    private List<Cart> cartInput;

    private OrderMain ordermainOutput;

    public OrderMain mapCartToOrder() {
        return ordermainOutput;
    }

    public void doMapping() {
        ordermainOutput = new OrderMain();
        java.util.List<com.app.shared.shoppingcontext.retail.OrderDetail> lstorderDetailEntities = new java.util.ArrayList<com.app.shared.shoppingcontext.retail.OrderDetail>();
        for (com.app.shared.shoppingcontext.retail.Cart _orderDetail : cartInput) {
            com.app.shared.shoppingcontext.retail.OrderDetail orderdetail = new com.app.shared.shoppingcontext.retail.OrderDetail();
            orderdetail.setItemId(_orderDetail.getItemId());
            orderdetail.setQty(_orderDetail.getQty());
            orderdetail.setSubTotal(_orderDetail.getSubTotal());
            lstorderDetailEntities.add(orderdetail);
        }
        ordermainOutput.setOrderDetail(lstorderDetailEntities);
    }
}

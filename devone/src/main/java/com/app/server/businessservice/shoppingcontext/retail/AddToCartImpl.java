package com.app.server.businessservice.shoppingcontext.retail;
import com.app.server.repository.shoppingcontext.retail.CartRepository;
import com.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.app.shared.shoppingcontext.retail.Cart;
import com.app.shared.shoppingcontext.retail.Item;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.customexceptions.OutOffStock;
import java.lang.Override;

@Component
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "AddToCartImpl", complexity = Complexity.HIGH)
public class AddToCartImpl implements AddToCart {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Autowired
    private CartRepository<Cart> cartRepository;

    @Override
    public void addToCart(Cart entity) throws OutOffStock, Exception {
        if (entity != null) {
            com.app.shared.shoppingcontext.retail.Item item = itemRepository.findById(entity.getItemId());
            if (item.getStock() < item.getStock() - entity.getQty()) {
                throw new com.app.customexceptions.OutOffStock("OutOffStock", "SOPRT227565406", null);
            }
            if (item.getStock() >= item.getStock() - entity.getQty()) {
                entity.setSubTotal(item.getPrice() * entity.getQty());
                entity.setUserId(runtimeLogInfoHelper.getRuntimeLogInfo().getUserId());
                com.app.shared.shoppingcontext.retail.Cart cart1 = cartRepository.save(entity);
            }
        }
    }
}

package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.shoppingcontext.OrderDetailQRM;
import java.lang.Override;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "OrderDetailBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class OrderDetailBzServiceImpl implements OrderDetailBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<OrderDetailQRM> executeQueryOrderDetailQ(String orderMainID1) throws Exception {
        java.util.List<com.app.shared.shoppingcontext.OrderDetailQRM> listDtoInterface = new java.util.ArrayList<com.app.shared.shoppingcontext.OrderDetailQRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "03E7A3F9-7C85-4A78-9884-93DA2BC2035C");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            atg.taglib.json.util.JSONObject jsonObjectOrderMainID1 = new atg.taglib.json.util.JSONObject();
            jsonObjectOrderMainID1.put("name", "orderMainID");
            jsonObjectOrderMainID1.put("value", orderMainID1);
            jsonObjectOrderMainID1.put("datatype", "VARCHAR");
            jsonObjectOrderMainID1.put("index", 1);
            jsonArray.add(jsonObjectOrderMainID1);
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.shoppingcontext.OrderDetailQRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}

package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.shoppingcontext.CartTotalRM;
import java.lang.Override;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "CartTotalBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class CartTotalBzServiceImpl implements CartTotalBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<CartTotalRM> executeQueryCartTotal(String userId1) throws Exception {
        java.util.List<com.app.shared.shoppingcontext.CartTotalRM> listDtoInterface = new java.util.ArrayList<com.app.shared.shoppingcontext.CartTotalRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "8C2A23A0-535E-4B7C-A49E-C8B4AB252A72");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            atg.taglib.json.util.JSONObject jsonObjectUserId1 = new atg.taglib.json.util.JSONObject();
            jsonObjectUserId1.put("name", "userId");
            jsonObjectUserId1.put("value", userId1);
            jsonObjectUserId1.put("datatype", "VARCHAR");
            jsonObjectUserId1.put("index", 1);
            jsonArray.add(jsonObjectUserId1);
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.shoppingcontext.CartTotalRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}

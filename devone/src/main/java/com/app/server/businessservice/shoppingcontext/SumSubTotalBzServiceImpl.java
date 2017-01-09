package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.shoppingcontext.SumSubTotalRM;
import java.lang.Override;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "SumSubTotalBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class SumSubTotalBzServiceImpl implements SumSubTotalBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<SumSubTotalRM> executeQuerySumSubTotal(String orderMainID1) throws Exception {
        java.util.List<com.app.shared.shoppingcontext.SumSubTotalRM> listDtoInterface = new java.util.ArrayList<com.app.shared.shoppingcontext.SumSubTotalRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "03C1A88D-7185-47FC-902B-CF336114A16B");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            atg.taglib.json.util.JSONObject jsonObjectOrderMainID1 = new atg.taglib.json.util.JSONObject();
            jsonObjectOrderMainID1.put("name", "orderMainID");
            jsonObjectOrderMainID1.put("value", orderMainID1);
            jsonObjectOrderMainID1.put("datatype", "VARCHAR");
            jsonObjectOrderMainID1.put("index", 1);
            jsonArray.add(jsonObjectOrderMainID1);
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.shoppingcontext.SumSubTotalRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}

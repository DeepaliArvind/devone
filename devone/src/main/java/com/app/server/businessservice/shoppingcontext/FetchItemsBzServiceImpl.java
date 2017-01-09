package com.app.server.businessservice.shoppingcontext;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.shoppingcontext.FetchItemsRM;
import java.lang.Override;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "FetchItemsBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class FetchItemsBzServiceImpl implements FetchItemsBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<FetchItemsRM> executeQueryFetchItems() throws Exception {
        java.util.List<com.app.shared.shoppingcontext.FetchItemsRM> listDtoInterface = new java.util.ArrayList<com.app.shared.shoppingcontext.FetchItemsRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "DF39FE05-D60F-44FC-AA1E-7042E5539A00");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.shoppingcontext.FetchItemsRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}

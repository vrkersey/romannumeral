package Utils;

import java.util.HashMap;
import java.util.Map;

public class RequestUtils {
    public static Map<String, String> getParameters(String fullQuery) {

        String[] queries = fullQuery.split("&");
        Map<String, String> queryParameters = new HashMap<>();
        for (String query : queries) {
            String[] queryParts = query.split("=");
            String queryKey = queryParts[0];
            String queryValue = queryParts.length == 2 ? queryParts[1] : "";
            queryParameters.put(queryKey, queryValue);
        }
        return queryParameters;
    }
}

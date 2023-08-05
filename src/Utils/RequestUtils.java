package Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility for getting information from request data
 */
public class RequestUtils {

    /**
     * Converts a URI query string into a key value pair
     * Duplicate query parameters are discarded
     * @param fullQuery - String - full query String ie. query=15&min=1&max=100
     * @return - Map of key value pairs
     */
    public static Map<String, String> getParameters(String fullQuery) {
        String[] queries = fullQuery.split("&");
        Map<String, String> queryParameters = new HashMap<>();
        for (String query : queries) {
            String[] queryParts = query.split("=");
            String queryKey = queryParts[0];
            String queryValue = queryParts.length == 2 ? queryParts[1] : "";
            if (!queryParameters.containsKey(queryKey)) {
                queryParameters.put(queryKey, queryValue);
            }
        }
        return queryParameters;
    }
}

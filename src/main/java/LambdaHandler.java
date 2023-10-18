import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.CountryItem;
import model.ProjectResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final String apiKey = "?api_key=3d31acb4-a9ed-470e-8610-5b30a800d764";
    private final String baseUrl = "https://api.globalgiving.org/api/public/services/";
    private final String countryUrl = "https://restcountries.com/v3.1/all?fields=name,cca2";
    private final HttpClient client = HttpClient.newHttpClient();
    private String cca2;
    private String countryName;

    private APIGatewayProxyResponseEvent getCountryList() {
        HttpRequest countriesRequest = HttpRequest.newBuilder().uri(URI.create(countryUrl)).build();
        try {
            HttpResponse<String> response = client.send(countriesRequest, HttpResponse.BodyHandlers.ofString());
            return generateResponse(response.statusCode(), response.body());
        } catch (Exception e) {
            return generateResponse(TaskStatus.SERVER_ERROR, e.getMessage());
        }
    }

    private APIGatewayProxyResponseEvent getProjects(Context context) {
        String projectsUrl = baseUrl + "search/projects/summary" + apiKey + "&q=*&filter=country:" + cca2 + ",theme:agriculture,theme:hunger";
        HttpRequest projectsRequest = HttpRequest.newBuilder().uri(URI.create(projectsUrl)).headers("Accept", "application/json").build();
        try {
            HttpResponse<String> response = client.send(projectsRequest, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == TaskStatus.SUCCESS) {
                ProjectResponse searchItem;
                try {
                    if (responseBody != null) {
                        searchItem = new Gson().fromJson(responseBody, ProjectResponse.class);
                        if (searchItem.getSearch().getResponse().getNumberFound() > 0) {
                            String projectResponseBody = new Gson().toJson(searchItem.getSearch().getResponse().getProjects());
                            context.getLogger().log("Country body: " + projectResponseBody);
                            return generateResponse(TaskStatus.SUCCESS, projectResponseBody);
                        } else
                            return generateResponse(TaskStatus.NOT_FOUND, "No active projects for " + countryName);
                    }
                } catch (Exception e) {
                    return generateResponse(TaskStatus.SERVER_ERROR, e.getMessage());
                }
            } else
                return generateResponse(statusCode, responseBody);
        } catch (Exception e) {
            return generateResponse(TaskStatus.SERVER_ERROR, e.getMessage());
        }
        return generateResponse(TaskStatus.SERVER_ERROR, "Internal server error");
    }

    APIGatewayProxyResponseEvent generateResponse(int taskStatus, String responseBody) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(taskStatus)
                .withHeaders(getHeaders())
                .withBody(responseBody);
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        Map<String, String> queryParams = input.getQueryStringParameters();
        countryName = queryParams.get("country");
        if (countryName == null) {
            return generateResponse(TaskStatus.SERVER_ERROR, "No params supplied");
        }

        APIGatewayProxyResponseEvent countryListResponse = getCountryList();
        ArrayList<CountryItem> countryList;
        context.getLogger().log("Country status: " + countryListResponse.getStatusCode());
        context.getLogger().log("Country body: " + countryListResponse.getBody());
        if (countryListResponse.getStatusCode() == TaskStatus.SUCCESS) {
            countryList = new Gson().fromJson(countryListResponse.getBody(), new TypeToken<ArrayList<CountryItem>>() {
            }.getType());
        } else {
            return generateResponse(countryListResponse.getStatusCode(), countryListResponse.getBody());
        }
        Optional<CountryItem> matchedCountry = countryList.stream()
                .filter(country ->
                        Objects.equals(country.getName().getName().trim().toLowerCase(), countryName.trim().toLowerCase()
                        )).findFirst();
        if (matchedCountry.isPresent()) {
            cca2 = matchedCountry.get().getCca2();
            context.getLogger().log("Country cca2: " + cca2);
            return getProjects(context);
        } else {
            return generateResponse(TaskStatus.NOT_FOUND, "No matching countries");
        }
    }
}

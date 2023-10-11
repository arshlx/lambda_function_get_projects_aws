import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.CountryItem;
import model.ProjectResponse;

import javax.net.ssl.SSLSession;
import java.io.PrintStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class LambdaHandler implements RequestHandler<String, String> {
    private static final PrintStream syso = System.out;

    private static final String apiKey = "?api_key=3d31acb4-a9ed-470e-8610-5b30a800d764";
    private static final String baseUrl = "https://api.globalgiving.org/api/public/services/";
    private static final String countryUrl = "https://restcountries.com/v3.1/all?fields=name,cca2";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static String cca2;

    public static void main(String[] args) {
        HttpResponse<String> listResponse = getCountryList();
        ArrayList<CountryItem> countryList = new Gson().fromJson(listResponse.body(), new TypeToken<ArrayList<CountryItem>>() {
        }.getType());
        syso.println(countryList);

        String input = "ukraine";
        Optional<CountryItem> matchedCountryOptional = countryList.stream()
                .filter(country -> Objects.equals(country.getName().getName().trim().toLowerCase(), input.trim().toLowerCase()))
                .findFirst();
        if (matchedCountryOptional.isPresent()) {
            CountryItem matchedCountry = matchedCountryOptional.get();
            cca2 = matchedCountry.getCca2();
            syso.println("CCA2: " + cca2);
            getProjects();
        } else {
            syso.println("Status code: " + TaskStatus.NOT_FOUND + "\nResponse body: Not Found");
        }
    }

    private static HttpResponse<String> getCountryList() {
        HttpRequest countriesRequest = HttpRequest.newBuilder().uri(URI.create(countryUrl)).build();
        try {
            return client.send(countriesRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            return new HttpResponse<>() {
                @Override
                public int statusCode() {
                    return TaskStatus.EXCEPTION;
                }

                @Override
                public HttpRequest request() {
                    return null;
                }

                @Override
                public Optional<HttpResponse<String>> previousResponse() {
                    return Optional.empty();
                }

                @Override
                public HttpHeaders headers() {
                    return null;
                }

                @Override
                public String body() {
                    return e.getMessage();
                }

                @Override
                public Optional<SSLSession> sslSession() {
                    return Optional.empty();
                }

                @Override
                public URI uri() {
                    return null;
                }

                @Override
                public HttpClient.Version version() {
                    return null;
                }
            };
        }
    }

    private static String getProjects() {
        String projectsUrl = baseUrl + "search/projects/summary" + apiKey + "&q=*&filter=country:" + cca2 + ",theme:agriculture,theme:hunger";
        HttpRequest projectsRequest = HttpRequest.newBuilder().uri(URI.create(projectsUrl)).headers("Accept", "application/json").build();

        try {
            HttpResponse<String> response = client.send(projectsRequest, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == TaskStatus.SUCCESS) {
                ProjectResponse searchItem = null;
                try {
                    searchItem = new Gson().fromJson(responseBody, ProjectResponse.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert searchItem != null;
                String finalResponse = new Gson().toJson(searchItem.getSearch().getResponse().getProjects());
                if (finalResponse == null) return "Status code: " + TaskStatus.NOT_FOUND + "\nResponse body: Not Found";
                else {
                    syso.println("Status code: " + statusCode + "\nResponse body: " + finalResponse);
                    return "Status code: " + statusCode + "\nResponse body: " + finalResponse;
                }
            } else return "Status code: " + statusCode + "\nResponse body: " + responseBody;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String handleRequest(String input, Context context) {
        HttpResponse<String> countryListResponse = getCountryList();

        ArrayList<CountryItem> countryList;
        if (countryListResponse.statusCode() == TaskStatus.SUCCESS) {
            countryList = new Gson().fromJson(countryListResponse.body(), new TypeToken<ArrayList<CountryItem>>() {
            }.getType());
        } else {
            return "Status code: " + countryListResponse + "\nResponse body: " + countryListResponse;
        }

        Optional<CountryItem> matchedCountry = countryList.stream()
                .filter(country -> Objects.equals(country.getName().getName().trim().toLowerCase(), input.trim().toLowerCase()))
                .findFirst();
        if (matchedCountry.isPresent()) {
            cca2 = matchedCountry.get().getCca2();
            return getProjects();
        } else {
            return "Status code: " + TaskStatus.NOT_FOUND + "\nNot Found";
        }
    }


}

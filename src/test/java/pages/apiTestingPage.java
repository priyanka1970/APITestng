package pages;

import Utils.ConfigUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
//import utils.ConfigUtil;

public class apiTestingPage {
    private static final String BASE_URL = ConfigUtil.getProperty("base.url");
//    private static final String AUTH_TOKEN = ConfigUtil.getProperty("auth.token");

    public static Response sendRequest(String method, String endpoint, Map<String, String> bodyData) {
        RequestSpecification request = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(bodyData);

//        // Add Authorization header only if token is not null/empty
//        if (AUTH_TOKEN != null && !AUTH_TOKEN.isEmpty() && !AUTH_TOKEN.equals("your_token_here")) {
//            request.header("Authorization", "Bearer " + AUTH_TOKEN);
//        }

        if (bodyData != null) {
            request.body(bodyData);
        }

        switch (method.toUpperCase()) {
            case "GET":
                return request.get(endpoint);
            case "POST":
                return request.post(endpoint);
            case "PUT":
                return request.put(endpoint);
            case "DELETE":
                return request.delete(endpoint);
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    }
}
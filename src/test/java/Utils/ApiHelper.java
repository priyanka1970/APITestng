package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class ApiHelper {
    private static final String BASE_URL = "http://127.0.0.1:8000";

    public static Response sendPostRequest(String endpoint, String body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(BASE_URL + endpoint);
    }

    public static Response sendGetRequest(String endpoint, String token) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get(BASE_URL + endpoint);
    }

    public static Response sendPutRequest(String endpoint, String token, String body) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .put(BASE_URL + endpoint);
    }

    public static Response sendDeleteRequest(String endpoint, String token) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .delete(BASE_URL + endpoint);
    }
}

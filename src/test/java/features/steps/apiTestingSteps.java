package features.steps;

import Utils.ApiHelper;
import Utils.ConfigUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.And;

import static org.hamcrest.Matchers.equalTo;

public class apiTestingSteps {
    private Response response;
    private  String token;
    private  int bookId;

    String baseUrl= ConfigUtil.getProperty("baseUrl");
    String email= ConfigUtil.getProperty("email");
    String password= ConfigUtil.getProperty("password");



    // Setup RestAssured to handle HTTPS & redirects
    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

//    @Given("I send a POST request to {string} with email {string} and password {string}")
//    public void sendPostLoginRequest(String endpoint, String email, String password) {
//        response = RestAssured.given()
//                .header("Content-Type", "application/json")
//                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
//                .post("http://127.0.0.1:8000" + endpoint);
//
//        // Debugging logs
//        System.out.println("Login Request URL: " + "http://127.0.0.1:8000" + endpoint);
//        System.out.println("Login Response Status: " + response.statusCode());
//        System.out.println("Login Response Body: " + response.getBody().asString());
//    }

    @Given("I send a POST request to {string}")
    public void sendPostLoginRequest(String endpoint) {
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
//                .post("http://127.0.0.1:8000" + endpoint);
                .post(baseUrl + endpoint);

        // Debugging logs
//        System.out.println("Login Request URL: " + "http://127.0.0.1:8000" + endpoint);
        System.out.println("Login Request URL: " + baseUrl + endpoint);
        System.out.println("Login Response Status: " + response.statusCode());
        System.out.println("Login Response Body: " + response.getBody().asString());
    }

    @Then("the response status should be {int}")
    public void validateResponseStatus(int expectedStatus) {
        response.then().statusCode(expectedStatus).log().all();
    }

    @And("I save the access token")
    public void saveAccessToken() {
        token = response.jsonPath().getString("access_token");
        System.out.println("Access Token: " + token);
    }

    @Given("I send a POST request to {string} with name {string}, author {string}, published_year {int}, and book_summary {string}")
    public void sendPostCreateBookRequest(String endpoint, String name, String author, int year, String summary) {
        response = RestAssured.given()
                .redirects().follow(true)
                .header("Authorization", "Bearer " + token) // Pass the token for authorization
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + name + "\", \"author\":\"" + author + "\", \"published_year\":" + year + ", \"book_summary\":\"" + summary + "\"}")
                .post(baseUrl + endpoint);

        // Debugging logs for redirects
        System.out.println("Book Creation Request URL: " + baseUrl + endpoint);
        System.out.println("Redirected URL: " + response.getHeader("Location"));
        System.out.println("Response Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        response.then().statusCode(200).log().all();
    }

    @And("I save the book ID")
    public void saveBookId() {
        bookId = response.jsonPath().getInt("id");
        System.out.println("Saved Book ID: " + bookId);
    }

    @Given("I send a GET request to {string} with authorization")
    public void sendGetBookByIdRequest(String endpoint) {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token) // Ensure authorization
                .get(baseUrl + endpoint.replace("{book_id}", String.valueOf(bookId)));

        // Debugging logs
        System.out.println("GET Request URL: " + baseUrl + endpoint.replace("{book_id}", String.valueOf(bookId)));
        System.out.println("Response Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        response.then().statusCode(200).log().all();
    }

    @Given("I send a PUT request to {string} with new name {string}, author {string}, published_year {int}, and book_summary {string}")
    public void sendPutUpdateBookRequest(String endpoint, String name, String author, int year, String summary) {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + name + "\", \"author\":\"" + author + "\", \"published_year\":" + year + ", \"book_summary\":\"" + summary + "\"}")
                .put(baseUrl + endpoint.replace("{book_id}", String.valueOf(bookId)));

        response.then().statusCode(200).log().all();
    }

    @Given("I send a DELETE request to {string} with authorization")
    public void sendDeleteBookRequest(String endpoint) {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .delete(baseUrl + endpoint.replace("{book_id}", String.valueOf(bookId)));

        response.then().statusCode(200).log().all();
    }

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = RestAssured.get(baseUrl + endpoint);
//        response = RestAssured.get("http://127.0.0.1:8000" + endpoint);
    }
}

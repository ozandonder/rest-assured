import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserRequester {

    private ValidatableResponse response = null;
    private User user = new User();

    User createUser(String json) {
        try {
            user = given()
                    .contentType(ContentType.JSON)
                    .body(json)
                    .when()
                    .baseUri("https://fakerestapi.azurewebsites.net/api/v1")
                    .post("/Users")
                    .as(User.class);
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        new Gson().toJson(user);
        return user;
    }

    User getUser(int userId) {
        try {
            String requestUrl = "https://fakerestapi.azurewebsites.net/api/v1/Users/" + userId;
            user = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(requestUrl).as(User.class);
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        new Gson().toJson(user);
        return user;
    }

    User editUser(String userId, String json) {
        try {
            String requestUrl = "https://fakerestapi.azurewebsites.net/api/v1/Users/" + userId;
            user = given()
                    .contentType(ContentType.JSON)
                    .body(json)
                    .when()
                    .put(requestUrl)
                    .as(User.class);
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        new Gson().toJson(user);
        return user;
    }

    ValidatableResponse deleteUser(String userId) {
        try {
            String requestUrl = "https://fakerestapi.azurewebsites.net/api/v1/Users/" + userId;
            response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(requestUrl)
                    .then();
        } catch (RuntimeException rte) {
            throw new RuntimeException(rte);
        }
        return response;
    }
}

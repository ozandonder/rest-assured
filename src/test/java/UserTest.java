import com.google.gson.Gson;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    UserRequester userRequester = new UserRequester();
    ValidatableResponse response;
    Gson gson = new Gson();
    User userModel = new User();
    User user;


    @Test
    void createUser() {
        userModel.setUser(1, "jhon", "123qwe");
        user = userRequester.createUser(gson.toJson(userModel));
        assertAll("User info incorrect",
                () -> assertEquals(String.valueOf(user.getId()), "1"),
                () -> assertEquals(user.getUserName(), "jhon"),
                () -> assertEquals(user.getPassword(), "123qwe")
        );
    }

    @Test
    void getUser() {
        user = userRequester.getUser(9);
        assertAll("User info incorrect",
                () -> assertEquals(String.valueOf(user.getId()), "9"),
                () -> assertEquals(user.getUserName(), "User 9"),
                () -> assertEquals(user.getPassword(), "Password9")
        );
    }

    @Test
    void editUser() {
        String userStub = "{\n" +
                "  \"id\": 3 ,\n" +
                "  \"userName\": \"jhon\",\n" +
                "  \"password\": \"123qwe\"\n" +
                "}";
        user = userRequester.editUser("2", userStub);
        assertAll("User info incorrect",
                () -> assertEquals(String.valueOf(user.getId()), "3"),
                () -> assertEquals(user.getUserName(), "jhon"),
                () -> assertEquals(user.getPassword(), "123qwe")
        );
    }

    @Test
    void deleteUser() {
        response = userRequester.deleteUser("1");
        assertEquals(String.valueOf(response.extract().statusCode()), "200");
    }
}

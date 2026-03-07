package api;

import constants.Urls;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testdata.UserTest;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_OK;

public class UserApi {
    private static final String REGISTER = "/api/auth/register";
    private static final String LOGIN = "/api/auth/login";
    private static final String USER = "/api/auth/user";

    @Step("Создать пользователя через API")
    public static void createUser(UserTest user) {
        RestAssured.baseURI = Urls.BASE_URL;

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(REGISTER)
                .then()
                .statusCode(HTTP_OK);
    }
        @Step("Залогиниться и получить accessToken")
        public static String loginAndGetToken(UserTest user) {
            RestAssured.baseURI = Urls.BASE_URL;

            UserTest creds = new UserTest(user.getEmail(), user.getPassword(), null);

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(creds)
                    .when()
                    .post(LOGIN);
            response.then().statusCode(HTTP_OK);

            return response.then().extract().path("accessToken");
        }

        @Step("Удалить пользователя")
        public static void deleteUser(String accessToken) {
            if (accessToken == null || accessToken.isEmpty()) return;

            RestAssured.baseURI = Urls.BASE_URL;

            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(USER)
                    .then()
                    .statusCode(HTTP_ACCEPTED);
        }
    }
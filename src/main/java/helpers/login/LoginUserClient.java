package helpers.login;

import helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.LoginUser;

public class LoginUserClient extends Client {

    public static String LOGIN_PATH = "/api/auth/login";

    @Step("Login user request")
    public Response loginUserRequest(LoginUser loginBody){
        return spec()
                .body(loginBody)
                .when()
                .post(LOGIN_PATH);
    }
}

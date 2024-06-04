package helpers.create.user;

import helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.CreateUser;

public class CreateUserClient extends Client {

    private static final String CREATE_USER_PATH="/api/auth/register";

    @Step("Create user request")
    public Response createUserRequest(CreateUser createBody){
        return spec()
                .body(createBody)
                .when()
                .post(CREATE_USER_PATH);
    }

}

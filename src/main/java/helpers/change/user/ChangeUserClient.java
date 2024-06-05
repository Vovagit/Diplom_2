package helpers.change.user;

import helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.ChangeUser;

public class ChangeUserClient extends Client {

    public static final String CHANGE_PATH="/api/auth/user";

        @Step("Change authorized request")
        public Response changeAuthorizedRequest(String token, ChangeUser changeBody){
            return spec()
                    .auth()
                    .oauth2(token)
                    .body(changeBody)
                    .patch(CHANGE_PATH);
        }

        @Step("Change without authorized request")
        public Response changeWithoutAuthorisedRequest(ChangeUser changeBody){
            return spec()
                    .body(changeBody)
                    .patch(CHANGE_PATH);
        }
}

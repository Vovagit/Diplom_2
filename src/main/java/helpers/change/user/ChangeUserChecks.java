package helpers.change.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangeUserChecks {

    @Step("Check success change user")
    public void checkSuccessChangeUser(Response resp){

        boolean change = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");

        assertTrue(change);
    }

    @Step("Check without authorized change user")
    public void checkWithoutAuthorizedChangeUser(Response resp){

        String message = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("message");

        assertEquals("You should be authorised",message);

    }

}

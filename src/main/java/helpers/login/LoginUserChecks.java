package helpers.login;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginUserChecks {

    @Step("Check success login")
    public void checkSuccessLogin(Response resp){

        boolean login = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");

        assertTrue(login);

    }

    @Step("Check bad login")
    public void checkBadLogin(Response resp){

        boolean login = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("success");

        assertFalse(login);
    }
}

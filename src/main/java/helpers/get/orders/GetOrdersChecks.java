package helpers.get.orders;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;
import static org.junit.Assert.*;

public class GetOrdersChecks {

    @Step("Check get orders authorize")
    public void checkGetOrdersAuthorize(Response resp){

        boolean getOrders = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");

        assertTrue(getOrders);
    }

    @Step("Check get orders without authorize")
    public void checkGetOrdersWithoutAuthorize(Response resp){

        String message = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("message");

        assertEquals("You should be authorised",message);
    }
}

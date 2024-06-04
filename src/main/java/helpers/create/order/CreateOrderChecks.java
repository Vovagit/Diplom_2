package helpers.create.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateOrderChecks {

    @Step("Check success create order")
    public void checkSuccessCreateOrder(Response resp){

        boolean order = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");

        assertTrue(order);
    }

    @Step("Check empty create order")
    public void checkEmptyCreateOrder(Response resp){

        String message = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");

        assertEquals("Ingredient ids must be provided",message);
    }

    @Step("Check invalid hash create order")
    public void checkInvalidHashCreateOrder(Response resp){
                resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }

}

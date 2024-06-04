package helpers.get.orders;

import helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetOrdersClient extends Client {

    private static final String GET_ORDERS_PATH="/api/orders";

    @Step("Get orders send authorize request")
    public Response getOrdersAuthorizeRequest(String token){
        return spec()
                .auth().oauth2(token)
                .get(GET_ORDERS_PATH);
    }

    @Step("Get orders send without authorize request")
    public Response getOrdersWithoutAuthorizeRequest(){
        return spec()
                .get(GET_ORDERS_PATH);
    }

}

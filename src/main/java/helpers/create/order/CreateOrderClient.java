package helpers.create.order;

import helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.CreateOrder;

public class CreateOrderClient extends Client {

    private static final String CREATE_ORDER_PATH="/api/orders";

    @Step("Create order authorized request")
    public Response createOrderAuthorizedRequest(String token, CreateOrder createOrder){
        return spec()
                .auth()
                .oauth2(token)
                .body(createOrder)
                .post(CREATE_ORDER_PATH);
    }

    @Step("Create order without authorized request")
    public Response createOrderWithoutAuthorizedRequest(CreateOrder createOrder){
        return spec()
                .body(createOrder)
                .post(CREATE_ORDER_PATH);
    }

}

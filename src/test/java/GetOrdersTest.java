import helpers.create.user.CreateUserHelper;
import helpers.delete.user.DeleteUserChecks;
import helpers.delete.user.DeleteUserClient;
import helpers.get.orders.GetOrdersChecks;
import helpers.get.orders.GetOrdersClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class GetOrdersTest {

    private final CreateUserHelper createUserHelper = new CreateUserHelper();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private final GetOrdersClient getOrdersClient = new GetOrdersClient();
    private final GetOrdersChecks getOrdersChecks = new GetOrdersChecks();


    private String token;

    @After
    public void deleteUser() {

        if (token != null) {
            Response delResp = delClient.deleteUserRequest(token);
            delChecks.checkSuccessDeleteUser(delResp);
        }
    }

    @Test
    @DisplayName("Test get orders authorized")
    public void getOrdersAuthorizedTest() {

        createUserHelper.createRandomUser();
        token = createUserHelper.getToken();

        Response resp = getOrdersClient.getOrdersAuthorizeRequest(token);
        getOrdersChecks.checkGetOrdersAuthorize(resp);
    }

    @Test
    @DisplayName("Test get orders without authorized")
    public void getOrdersWithoutAuthorizedTest() {

        Response resp = getOrdersClient.getOrdersWithoutAuthorizeRequest();
        getOrdersChecks.checkGetOrdersWithoutAuthorize(resp);

    }

}

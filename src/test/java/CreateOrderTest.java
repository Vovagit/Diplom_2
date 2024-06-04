import helpers.create.order.CreateOrderChecks;
import helpers.create.order.CreateOrderClient;
import helpers.create.user.CreateUserHelper;
import helpers.delete.user.DeleteUserChecks;
import helpers.delete.user.DeleteUserClient;
import helpers.get.ingredient.GetIngredientChecks;
import helpers.get.ingredient.GetIngredientClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.CreateOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CreateOrderTest {

    private final CreateUserHelper createUserHelper = new CreateUserHelper();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private final CreateOrderClient createOrderClient = new CreateOrderClient();
    private final CreateOrderChecks createOrderChecks = new CreateOrderChecks();
    private final GetIngredientClient getIngredientClient = new GetIngredientClient();
    private final GetIngredientChecks getIngredientChecks = new GetIngredientChecks();
    Random rand = new Random();
    private String token;
    private ArrayList<String> ingredientsValid = new ArrayList<>();
    public final ArrayList<String> ingredientsInvalid = new ArrayList<>(Arrays.asList("101010101","101010101"));

    @After
    public void deleteUser() {

        if (token != null) {
            Response delResp = delClient.deleteUserRequest(token);
            delChecks.checkSuccessDeleteUser(delResp);
        }

    }

    @Before
    public void before(){

        Response resp = getIngredientClient.getIngredientRequest();
        getIngredientChecks.ckeckSuccessGetIngredient(resp);
        ArrayList<String> dataId=resp.path("data._id");
        ingredientsValid.add(dataId.get(rand.nextInt(dataId.size())));
        ingredientsValid.add(dataId.get(rand.nextInt(dataId.size())));

    }

    @Test
    @DisplayName("Test create order with authorize")
    public void createOrderAuthorizedTest(){

        createUserHelper.createRandomUser();
        token = createUserHelper.getToken();
        CreateOrder createOrder = new CreateOrder(ingredientsValid);
        Response resp = createOrderClient.createOrderAuthorizedRequest(token,createOrder);
        createOrderChecks.checkSuccessCreateOrder(resp);

    }

    @Test
    @DisplayName("Test create order without authorize")
    public void createOrderWithoutAuthorizedTest(){

        CreateOrder createOrder = new CreateOrder(ingredientsValid);
        Response resp = createOrderClient.createOrderWithoutAuthorizedRequest(createOrder);
        createOrderChecks.checkSuccessCreateOrder(resp);

    }

    @Test
    @DisplayName("Test create empty order")
    public void createEmptyOrder(){

        createUserHelper.createRandomUser();
        token = createUserHelper.getToken();
        CreateOrder createOrder = new CreateOrder();
        Response resp = createOrderClient.createOrderAuthorizedRequest(token,createOrder);
        createOrderChecks.checkEmptyCreateOrder(resp);

    }

    @Test
    @DisplayName("Test create invalid hash order")
    public void createInvalidHashOrder(){

        createUserHelper.createRandomUser();
        token = createUserHelper.getToken();
        CreateOrder createOrder = new CreateOrder(ingredientsInvalid);
        Response resp = createOrderClient.createOrderAuthorizedRequest(token,createOrder);
        createOrderChecks.checkInvalidHashCreateOrder(resp);

    }

}

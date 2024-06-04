import helpers.create.user.CreateUserChecks;
import helpers.create.user.CreateUserClient;
import helpers.delete.user.DeleteUserChecks;
import helpers.delete.user.DeleteUserClient;
import helpers.create.user.CreateUserHelper;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import pojo.CreateUser;


public class CreateUserTest {

    private final CreateUserHelper createUserHelper = new CreateUserHelper();
    private final CreateUserClient client = new CreateUserClient();
    private final CreateUserChecks check = new CreateUserChecks();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private String token;

    @After
    public void deleteUser() {
        if (token != null) {
            Response delResp = delClient.deleteUserRequest(token.replace("Bearer ",""));
            delChecks.checkSuccessDeleteUser(delResp);
        }
    }

    @Test
    @DisplayName("Create user test")
    public void uniqueUserCreateTest (){

        createUserHelper.createRandomUser();
        token = createUserHelper.getToken();

    }

    @Test
    @DisplayName("Create User already create")
    public void createUserAlredyRegister(){

        createUserHelper.createRandomUser();
        token = createUserHelper.getToken();
        String email = createUserHelper.getEmail();
        String password = createUserHelper.getPassword();
        String name = createUserHelper.getName();
        CreateUser createBody= new CreateUser(email,password,name);
        Response newResp = client.createUserRequest(createBody);
        check.checkAlreadyCreateUser(newResp);

    }

    @Test
    @DisplayName("Create User without email")
    public void createUserWithoutEmailTest(){

        CreateUser createBody = new CreateUser();
        createBody.setName(RandomStringUtils.randomAlphabetic(6));
        createBody.setPassword(RandomStringUtils.randomAlphabetic(6));
        Response clientResp = client.createUserRequest(createBody);
        check.checkRequiredBody(clientResp);
    }

    @Test
    @DisplayName("Create User without password")
    public void createUserWithoutPassTest(){

        CreateUser createBody =new CreateUser();
        createBody.setName(RandomStringUtils.randomAlphabetic(6));
        createBody.setEmail(String.format("Vova.%s@test.ru", RandomStringUtils.randomAlphabetic(7)));
        Response clientResp = client.createUserRequest(createBody);
        check.checkRequiredBody(clientResp);
    }

    @Test
    @DisplayName("Create User without name")
    public void createUserWithoutNameTest(){

        CreateUser createBody =new CreateUser();
        createBody.setEmail(String.format("Vova.%s@test.ru", RandomStringUtils.randomAlphabetic(7)));
        createBody.setPassword(RandomStringUtils.randomAlphabetic(6));
        Response clientResp = client.createUserRequest(createBody);
        check.checkRequiredBody(clientResp);

    }

}

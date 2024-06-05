import helpers.create.user.CreateUserHelper;
import helpers.delete.user.DeleteUserChecks;
import helpers.delete.user.DeleteUserClient;
import helpers.login.LoginUserChecks;
import helpers.login.LoginUserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.LoginUser;

public class LoginTest {

    private final CreateUserHelper createUserHelper = new CreateUserHelper();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private final LoginUserClient loginClient = new LoginUserClient();
    private final LoginUserChecks loginChecks = new LoginUserChecks();
    private String token;
    private String email;
    private String password;

    @After
    public void deleteUser() {

        if (token != null) {
            Response delResp = delClient.deleteUserRequest(token);
            delChecks.checkSuccessDeleteUser(delResp);
        }
    }

    @Before
    public void before(){

        createUserHelper.createRandomUser();
        token = createUserHelper.getToken();
        email = createUserHelper.getEmail();
        password = createUserHelper.getPassword();

    }

    @Test
    @DisplayName("Test success login")
    public void succesLoginTest(){

        LoginUser loginUser = new LoginUser(email,password);
        Response loginResp = loginClient.loginUserRequest(loginUser);
        loginChecks.checkSuccessLogin(loginResp);

    }

    @Test
    @DisplayName("Test bad login without email")
        public void loginWithoutEmailTest(){

        LoginUser loginUser = new LoginUser();
        loginUser.setPassword(password);
        Response loginResp = loginClient.loginUserRequest(loginUser);
        loginChecks.checkBadLogin(loginResp);

    }

    @Test
    @DisplayName("Test bad login without password")
    public void loginWithoutPasswordTest(){

        LoginUser loginUser = new LoginUser();
        loginUser.setEmail(email);
        Response loginResp = loginClient.loginUserRequest(loginUser);
        loginChecks.checkBadLogin(loginResp);
    }

    @Test
    @DisplayName("Test bad login another Email")
    public void loginAnotherEmailTest(){

        LoginUser loginUser = new LoginUser(String.format("Vova.%s@test.ru", RandomStringUtils.randomAlphabetic(7)),password);
        Response loginResp = loginClient.loginUserRequest(loginUser);
        loginChecks.checkBadLogin(loginResp);

    }

    @Test
    @DisplayName("Test bad login another password")
    public void loginAnotherPasswordTest(){

        LoginUser loginUser = new LoginUser(email,RandomStringUtils.randomAlphabetic(6));
        Response loginResp = loginClient.loginUserRequest(loginUser);
        loginChecks.checkBadLogin(loginResp);

    }

}

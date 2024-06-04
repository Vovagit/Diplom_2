import helpers.change.user.ChangeUserChecks;
import helpers.change.user.ChangeUserClient;
import helpers.create.user.CreateUserHelper;
import helpers.delete.user.DeleteUserChecks;
import helpers.delete.user.DeleteUserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.ChangeUser;

public class ChangeDataUserTest {

    private final CreateUserHelper createUserHelper = new CreateUserHelper();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private final ChangeUserClient changeClient = new ChangeUserClient();
    private final ChangeUserChecks changeChecks = new ChangeUserChecks();
    private String newName;
    private String newEmail;
    private String token;

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
        newName = RandomStringUtils.randomAlphabetic(6);
        newEmail = String.format("Vova.%s@test.ru", RandomStringUtils.randomAlphabetic(7));

    }

    @Test
    @DisplayName("Test change user authorized Email")
    public void changeUserAuthorized(){

        ChangeUser changeUser = new ChangeUser(newName,newEmail);
        Response resp = changeClient.changeAuthorizedRequest(token.replace("Bearer ",""),changeUser);
        changeChecks.checkSuccessChangeUser(resp);

    }

    @Test
    @DisplayName("Test change user no authorized")
    public void changeUserNoAuthorized(){

        ChangeUser changeUser = new ChangeUser(newName,newEmail);
        Response resp = changeClient.changeWithoutAuthorisedRequest(changeUser);
        changeChecks.checkWithoutAuthorizedChangeUser(resp);

    }

}

package helpers.create.user;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.CreateUser;

public class CreateUserHelper {

    private static final CreateUserClient userClient = new CreateUserClient();
    private static final CreateUserChecks userChecks = new CreateUserChecks();
    private String email = String.format("Vova.%s@test.ru", RandomStringUtils.randomAlphabetic(7));
    private String name = RandomStringUtils.randomAlphabetic(6);;
    private String password = RandomStringUtils.randomAlphabetic(6);
    private String token;

    public void createRandomUser(){
        CreateUser createBody = new CreateUser(email,password,name);
        Response clientResp = userClient.createUserRequest(createBody);
        userChecks.checkSuccessCreateUser(clientResp);
        token = clientResp.path("accessToken");
    }

    public String getToken() {
        return token.replace("Bearer ","");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

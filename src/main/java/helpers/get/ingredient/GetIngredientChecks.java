package helpers.get.ingredient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;

public class GetIngredientChecks {

    @Step("Check sucess get ingredient")
    public void ckeckSuccessGetIngredient(Response resp){
        resp.then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }
}

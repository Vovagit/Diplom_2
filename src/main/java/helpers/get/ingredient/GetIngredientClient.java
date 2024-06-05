package helpers.get.ingredient;

import helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetIngredientClient extends Client {
    public static final String GET_INGREDIENT_PATH="/api/ingredients";
    @Step("Get ingredient request")
    public Response getIngredientRequest(){
        return spec().get(GET_INGREDIENT_PATH);
    }

}

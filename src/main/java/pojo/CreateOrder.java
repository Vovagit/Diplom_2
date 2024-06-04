package pojo;

import java.util.ArrayList;

public class CreateOrder {

    private ArrayList<String> ingredients;

    public CreateOrder(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public CreateOrder() {
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}

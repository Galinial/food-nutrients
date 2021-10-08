package gal.linial.foodappproject.models;

import java.util.ArrayList;

public class FoodInformationResponse {
    private ArrayList<FoodInformation> foodInformations;

    public FoodInformationResponse() {
    }

    public ArrayList<FoodInformation> getFoodInformations() {
        return foodInformations;
    }

    public void setFoodInformations(ArrayList<FoodInformation> foodInformations) {
        this.foodInformations = foodInformations;
    }

    @Override
    public String toString() {
        return "FoodInformationResponse{" +
                "foodInformations=" + foodInformations +
                '}';
    }
}

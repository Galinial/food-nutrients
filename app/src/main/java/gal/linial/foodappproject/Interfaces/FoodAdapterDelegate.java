package gal.linial.foodappproject.Interfaces;

import gal.linial.foodappproject.models.FoodInformation;

public interface FoodAdapterDelegate {
    void selectedFood(FoodInformation foodInformation);
    void showToast(FoodInformation foodInformation);
}


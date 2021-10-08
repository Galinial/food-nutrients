package gal.linial.foodappproject.ui.foodDetails;


import androidx.lifecycle.ViewModel;

import java.util.List;


import gal.linial.foodappproject.models.FoodNutrient;


public class FoodDetailsViewModel extends ViewModel {
    private List<FoodNutrient> selectedFood;

    public FoodDetailsViewModel(){

    }

    public List<FoodNutrient> getSelectedFood() {
        return selectedFood;
    }
}
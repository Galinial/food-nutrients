package gal.linial.foodappproject.ui.favoritesDetails;

import androidx.lifecycle.ViewModel;

import java.util.List;

import gal.linial.foodappproject.models.FoodNutrient;

public class FavoritesDetailsViewModel extends ViewModel {
    private List<FoodNutrient> selectedFood;

    public FavoritesDetailsViewModel(){

    }

    public List<FoodNutrient> getSelectedFood() {
        return selectedFood;
    }
}
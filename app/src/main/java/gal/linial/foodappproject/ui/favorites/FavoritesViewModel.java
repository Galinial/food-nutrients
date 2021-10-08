package gal.linial.foodappproject.ui.favorites;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import gal.linial.foodappproject.Firebase_RealTime;
import gal.linial.foodappproject.models.FoodInformation;
import gal.linial.foodappproject.models.FoodNutrient;

public class FavoritesViewModel extends ViewModel {

    private MutableLiveData<List<FoodInformation>> favoriteFoodList;
    private List<FoodNutrient> selectedFood;

    public FavoritesViewModel() {
        favoriteFoodList = new MutableLiveData<>();
        Firebase_RealTime.fetchFavorites(itemList -> favoriteFoodList.postValue(itemList));
    }

    public MutableLiveData<List<FoodInformation>> getFavoriteFoodList() {
        return favoriteFoodList;
    }

    public List<FoodNutrient> getSelectedFood() {
        return selectedFood;
    }
}
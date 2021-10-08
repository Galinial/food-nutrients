package gal.linial.foodappproject.ui.foodList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import gal.linial.foodappproject.models.FDCDataSource;
import gal.linial.foodappproject.models.FoodInformation;

public class FoodListViewModel extends ViewModel {

    private MutableLiveData<List<FoodInformation>> foodInformation = new MutableLiveData<>();
    private MutableLiveData<Throwable> exc = new MutableLiveData<>();
    private MutableLiveData<FoodInformation> selectedFood  = new MutableLiveData<>();
    private MutableLiveData<FoodInformation> favoriteFoodAdd = new MutableLiveData<>();

    public FoodListViewModel(){
        FDCDataSource.getShared().getFoodInfo(foodInformation , exc);
    }

    public MutableLiveData<FoodInformation> getSelectedFood() {
        return selectedFood;
    }

    public MutableLiveData<List<FoodInformation>> getFoodInformation() {
        return foodInformation;
    }

    public MutableLiveData<Throwable> getExc() {
        return exc;
    }

    public MutableLiveData<FoodInformation> getFavoriteFood() {
        return favoriteFoodAdd;
    }

    public void setFavoriteFoodAdd(MutableLiveData<FoodInformation> favoriteFoodAdd) {
        this.favoriteFoodAdd = favoriteFoodAdd;
    }
}
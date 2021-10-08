package gal.linial.foodappproject.Interfaces;

import gal.linial.foodappproject.models.FoodInformation;

public interface FoodAdapterDelegateFavorite extends FoodAdapterDelegate {
    void delete(FoodInformation foodInformation);
}

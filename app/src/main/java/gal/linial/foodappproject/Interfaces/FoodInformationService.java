package gal.linial.foodappproject.Interfaces;

import java.util.List;

import gal.linial.foodappproject.models.FoodInformation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodInformationService {
    @GET("v1/foods")
    Call<List<FoodInformation>> getInformationForChicken(@Query("fdcIds") String fdcIds, @Query("format") String format, @Query("api_key") String apiKey);
}

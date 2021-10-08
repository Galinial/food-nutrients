package gal.linial.foodappproject.models;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gal.linial.foodappproject.Interfaces.FoodInformationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FDCDataSource {
    private static FDCDataSource shared;
    //hide the constructor:
    private FDCDataSource(){}
    public static FDCDataSource getShared(){
        //lazy loading:
        if (shared == null){
            shared = new FDCDataSource();
        }
        return shared;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nal.usda.gov/fdc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    FoodInformationService service = retrofit.create(FoodInformationService.class);

    public void getFoodInfo(MutableLiveData<List<FoodInformation>> foodCallBack , MutableLiveData<Throwable> exceptionCallBack){
        Call<List<FoodInformation>> foodInformationListResponseCall = service.getInformationForChicken("173688,1098453,748967,790214,1104812,1099160,1101529,171620,173706,172041,1102958,170721,170717,170328,1100211,1103276,1103352,325871,172064,173297,172065,173305,172074,173308,173302,173301","abridged","WRDjOCoBQWBVQsSOsfJGa0w0tJcUfHU5qVZ2mB3u");
        //System.out.println("_____GGGGGGGG" + foodInformationListResponseCall.request().url());
        foodInformationListResponseCall.enqueue(new Callback<List<FoodInformation>>() {
            @Override
            public void onResponse(Call<List<FoodInformation>> call, Response<List<FoodInformation>> response) {
                List<FoodInformation> foodInformations = response.body();
                if (foodInformations == null){
                    //System.out.println("______internet problme");
                    return;
                }
                System.out.println(foodInformations.get(0).toString());
                foodCallBack.postValue(foodInformations);
            }

            @Override
            public void onFailure(Call<List<FoodInformation>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

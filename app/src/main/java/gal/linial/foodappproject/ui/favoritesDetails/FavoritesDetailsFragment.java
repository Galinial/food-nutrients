package gal.linial.foodappproject.ui.favoritesDetails;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gal.linial.foodappproject.adapters.FoodNutrientsAdapter;
import gal.linial.foodappproject.databinding.FavoritesDetailsFragmentBinding;
import gal.linial.foodappproject.models.FoodInformation;
import gal.linial.foodappproject.models.FoodNutrient;

public class FavoritesDetailsFragment extends Fragment {

    public static FavoritesDetailsFragment newInstance() {
        return new FavoritesDetailsFragment();
    }

    private FavoritesDetailsFragmentBinding binding;
    private FavoritesDetailsViewModel favoritesDetailsViewModel;
    private FoodNutrientsAdapter foodNutrientsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        favoritesDetailsViewModel =
                new ViewModelProvider(this).get(FavoritesDetailsViewModel.class);

        binding = FavoritesDetailsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        favoritesDetailsViewModel = new ViewModelProvider(this).get(FavoritesDetailsViewModel.class);
        if (args!=null){
            FoodInformation foodSelected = (FoodInformation) args.getSerializable("foodClicked");
            List<FoodNutrient> foodNutrients = foodSelected.getFoodNutrients();
            foodNutrientsAdapter =  new FoodNutrientsAdapter(foodNutrients);
            binding.recyclerViewFavoritesDetails.setAdapter(foodNutrientsAdapter);
            binding.recyclerViewFavoritesDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}
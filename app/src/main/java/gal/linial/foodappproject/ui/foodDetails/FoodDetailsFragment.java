package gal.linial.foodappproject.ui.foodDetails;


import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.List;


import gal.linial.foodappproject.databinding.FoodDetailsFragmentBinding;

import gal.linial.foodappproject.models.FoodInformation;

import gal.linial.foodappproject.models.FoodNutrient;
import gal.linial.foodappproject.adapters.FoodNutrientsAdapter;


public class FoodDetailsFragment extends Fragment {

    private FoodDetailsViewModel foodDetailsViewModel;
    private FoodDetailsFragmentBinding binding;
    private FoodNutrientsAdapter foodNutrientsAdapter;

    public static FoodDetailsFragment newInstance() {
        return new FoodDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        foodDetailsViewModel =
                new ViewModelProvider(this).get(FoodDetailsViewModel.class);

        binding = FoodDetailsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        foodDetailsViewModel = new ViewModelProvider(this).get(FoodDetailsViewModel.class);
        if (args!=null){
            FoodInformation foodSelected = (FoodInformation) args.getSerializable("foodClicked");
            List<FoodNutrient> foodNutrients = foodSelected.getFoodNutrients();
            foodNutrientsAdapter =  new FoodNutrientsAdapter(foodNutrients);
            binding.recyclerViewNutrients.setAdapter(foodNutrientsAdapter);
            binding.recyclerViewNutrients.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        binding.searchBarNutrients.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                foodNutrientsAdapter.filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
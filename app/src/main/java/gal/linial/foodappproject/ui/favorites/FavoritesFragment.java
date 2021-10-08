package gal.linial.foodappproject.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;

import gal.linial.foodappproject.Firebase_RealTime;
import gal.linial.foodappproject.Interfaces.FoodAdapterDelegate;
import gal.linial.foodappproject.Interfaces.FoodAdapterDelegateFavorite;
import gal.linial.foodappproject.R;
import gal.linial.foodappproject.adapters.FoodFavoritesAdapter;
import gal.linial.foodappproject.databinding.FragmentFavoritesBinding;
import gal.linial.foodappproject.models.FoodInformation;



public class FavoritesFragment extends Fragment implements FoodAdapterDelegateFavorite {

    private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;
    private FoodFavoritesAdapter foodInformationAdapter;
    private RecyclerView foodInfoRV;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        foodInfoRV = binding.recyclerViewFavorites;
        foodInfoRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

       favoritesViewModel.getFavoriteFoodList().observe(getViewLifecycleOwner(), new Observer<List<FoodInformation>>() {
           @Override
           public void onChanged(List<FoodInformation> foodInformations) {
               foodInformationAdapter = new FoodFavoritesAdapter(foodInformations,FavoritesFragment.this);
               foodInfoRV.setAdapter(foodInformationAdapter);
           }
       });


    };

    @Override
    public void selectedFood(FoodInformation foodInformation) {
        if (foodInformation == null) return;
        Bundle args = new Bundle();
        args.putSerializable("foodClicked" ,foodInformation);

        binding.recyclerViewFavorites.setVisibility(View.GONE);
                NavHostFragment.findNavController(getParentFragment()).
                        navigate(R.id.favoritesDetailsFragment, args);

            }

    @Override
    public void showToast(FoodInformation foodInformation) {
        Snackbar.make(getContext(),getView(),"Added to Favorites: " + foodInformation.getDescription(),2000).show();
    }

    @Override
    public void delete(FoodInformation foodInformation) {
        Firebase_RealTime.deleteFavorite(foodInformation);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
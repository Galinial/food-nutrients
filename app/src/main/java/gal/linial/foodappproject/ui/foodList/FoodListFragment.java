package gal.linial.foodappproject.ui.foodList;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import gal.linial.foodappproject.Interfaces.FoodAdapterDelegate;
import gal.linial.foodappproject.R;
import gal.linial.foodappproject.databinding.FragmentFoodListBinding;
import gal.linial.foodappproject.adapters.FoodInformationAdapter;
import gal.linial.foodappproject.models.FoodInformation;

public class FoodListFragment extends Fragment implements FoodAdapterDelegate {

    private FoodListViewModel foodListViewModel;
    private FragmentFoodListBinding binding;
    private FoodInformationAdapter foodInformationAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        foodListViewModel =
                new ViewModelProvider(this).get(FoodListViewModel.class);

        binding = FragmentFoodListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.progressBar.setVisibility(View.VISIBLE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodListViewModel = new ViewModelProvider(this).get(FoodListViewModel.class);
        foodListViewModel.getFoodInformation().observe(getViewLifecycleOwner() , foodInformations -> {
            foodInformationAdapter = new FoodInformationAdapter(foodInformations ,this);
            binding.recyclerView.setAdapter(foodInformationAdapter);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.searchBar.setVisibility(View.VISIBLE);


        });
        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                foodInformationAdapter.filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void selectedFood(FoodInformation foodInformation) {
            if (foodInformation == null) return;
            Bundle args = new Bundle();
            args.putSerializable("foodClicked" ,foodInformation);
            InputMethodManager imm = (InputMethodManager)
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binding.searchBar.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            binding.recyclerView.setVisibility(View.GONE);
            binding.searchBar.setVisibility(View.GONE);
            binding.plainAnimation.playAnimation();
            binding.plainAnimation.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    assert getParentFragment() != null;
                    NavHostFragment.findNavController(getParentFragment()).
                            navigate(R.id.action_navigation_dashboard_to_foodDetailsFragment , args);

                    foodListViewModel.getSelectedFood().postValue(null);
                }
            },binding.plainAnimation.getDuration());

    }

    @Override
    public void showToast(FoodInformation foodInformation) {
        Snackbar.make(getContext(),getView(),"Added to Favorites: " + foodInformation.getDescription(),2000).show();
    }
}
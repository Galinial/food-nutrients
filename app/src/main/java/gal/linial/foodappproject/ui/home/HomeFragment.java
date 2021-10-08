package gal.linial.foodappproject.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;



import org.jetbrains.annotations.NotNull;

import gal.linial.foodappproject.Firebase_RealTime;
import gal.linial.foodappproject.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Firebase_RealTime.saveNewUser();
        binding.textHome.setText("Hello Guest,\n" +
                "Welcome to the Data Food Central.\n" +
                "- Food nutrients are calculated by 100 Grams.\n" +
                "- Tap an item to see its nutrients." +
                "- Hold an item to add it to your favorites. \n" +
                "- You may delete it at the Favorites section by clicking the X button");
        binding.cubesAnimation.setProgress(0);
        binding.cubesAnimation.playAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
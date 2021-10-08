package gal.linial.foodappproject.adapters;

import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


import gal.linial.foodappproject.databinding.NutrientsItemBinding;
import gal.linial.foodappproject.models.FoodNutrient;

public class FoodNutrientsAdapter extends RecyclerView.Adapter<FoodNutrientsAdapter.VH> {
    private List<FoodNutrient> foodNutrients;
    private List<FoodNutrient> foodNutrientsCopy;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public FoodNutrientsAdapter(List<FoodNutrient> foodNutrients) {
     //   foodNutrients = foodNutrients.stream().filter((foodNutrient) ->  {return foodNutrient.getAmount() > 0});
        List<FoodNutrient> filteredNutrients = new ArrayList<>();
        for (FoodNutrient foodNutrient : foodNutrients) {
            if (foodNutrient.getAmount() == 0) {
                continue;
            }
            filteredNutrients.add(foodNutrient);
        }



        this.foodNutrients = filteredNutrients;

        this.foodNutrientsCopy = new ArrayList<>();
        foodNutrientsCopy.addAll(foodNutrients);
    }

    @NonNull
    @NotNull
    @Override
    public VH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        NutrientsItemBinding binding = NutrientsItemBinding.inflate(inflater, parent , false);

        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VH holder, int position) {
        FoodNutrient foodNutrient = foodNutrients.get(position);

        holder.binding.tvName.setText(foodNutrient.getName());
        holder.binding.tvAmont.setText(foodNutrient.getAmount().toString());
        holder.binding.tvUnit.setText(foodNutrient.getUnitName());
    }

    @Override
    public int getItemCount() {
        return foodNutrients.size();
    }

    public static class VH extends RecyclerView.ViewHolder{
        NutrientsItemBinding binding;
        public VH(NutrientsItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void filter(CharSequence charSequence){
        List<FoodNutrient> tempArrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(charSequence)){
            for (FoodNutrient foodNutrient : foodNutrients){
                if (foodNutrient.getName().toLowerCase().contains(charSequence))
                    tempArrayList.add(foodNutrient);
            }
        }else {
            tempArrayList.addAll(foodNutrientsCopy);
        }

        foodNutrients.clear();
        foodNutrients.addAll(tempArrayList);
        notifyDataSetChanged();
        tempArrayList.clear();
    }
}

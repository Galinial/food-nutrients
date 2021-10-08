package gal.linial.foodappproject.adapters;



import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

import gal.linial.foodappproject.Firebase_RealTime;
import gal.linial.foodappproject.Interfaces.FoodAdapterDelegate;
import gal.linial.foodappproject.databinding.FoodItemBinding;
import gal.linial.foodappproject.models.FoodInformation;



public class FoodInformationAdapter extends RecyclerView.Adapter<FoodInformationAdapter.VH> {

    private List<FoodInformation> foodInformations;
    private FoodAdapterDelegate delegate;
    private List<FoodInformation> foodInformationsCopy;

    public FoodInformationAdapter(List<FoodInformation> foodInformations,FoodAdapterDelegate delegate) {
        this.foodInformations = foodInformations;
        this.delegate = delegate;
        this.foodInformationsCopy = new ArrayList<>();
        foodInformationsCopy.addAll(foodInformations);
    }

    @NonNull
    @NotNull
    @Override
    public VH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        FoodItemBinding binding = FoodItemBinding.inflate(inflater , parent , false);

        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VH holder, int position) {
        FoodInformation foodInformation = foodInformations.get(position);

        holder.binding.tvName.setText(foodInformation.getDescription());
        holder.itemView.setOnClickListener(v -> delegate.selectedFood(foodInformation));
        holder.itemView.setOnLongClickListener(v -> {
            delegate.showToast(foodInformation);
            Firebase_RealTime.saveNewItemFavorite(foodInformation);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return foodInformations.size();
    }

    public static class VH extends RecyclerView.ViewHolder{
        FoodItemBinding binding;
        public VH(FoodItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void filter(CharSequence charSequence){
        List<FoodInformation> tempArrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(charSequence)){
            for (FoodInformation foodInformation : foodInformations){
                if (foodInformation.getDescription().toLowerCase().contains(charSequence))
                    tempArrayList.add(foodInformation);
            }
        }else {
            tempArrayList.addAll(foodInformationsCopy);
        }

        foodInformations.clear();
        foodInformations.addAll(tempArrayList);
        notifyDataSetChanged();
        tempArrayList.clear();
    }
}

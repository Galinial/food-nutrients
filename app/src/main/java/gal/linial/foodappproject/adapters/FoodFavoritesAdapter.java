package gal.linial.foodappproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gal.linial.foodappproject.Firebase_RealTime;
import gal.linial.foodappproject.Interfaces.FoodAdapterDelegate;
import gal.linial.foodappproject.Interfaces.FoodAdapterDelegateFavorite;
import gal.linial.foodappproject.databinding.FoodItemBinding;
import gal.linial.foodappproject.models.FoodInformation;

public class FoodFavoritesAdapter extends RecyclerView.Adapter<FoodFavoritesAdapter.VH> {
    private List<FoodInformation> foodInformations;
    private FoodAdapterDelegateFavorite delegate;

    public FoodFavoritesAdapter(List<FoodInformation> foodInformations, FoodAdapterDelegateFavorite delegate) {
        this.foodInformations = foodInformations;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        FoodItemBinding binding = FoodItemBinding.inflate(inflater , parent , false);

        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        FoodInformation foodInformation = foodInformations.get(position);

        holder.binding.tvName.setText(foodInformation.getDescription());
        holder.itemView.setOnClickListener(v -> delegate.selectedFood(foodInformation));
        holder.binding.deleteBtn.setVisibility(View.VISIBLE);
        holder.binding.deleteBtn.setOnClickListener(v -> delegate.delete(foodInformation));
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
}

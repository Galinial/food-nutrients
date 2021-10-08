package gal.linial.foodappproject;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import gal.linial.foodappproject.models.FoodInformation;

public class Firebase_RealTime {
    private static final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private static final DatabaseReference usersReference = reference.child("users");
    private static final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    public interface FirebaseUserInterface{
        void fetched(List<FoodInformation> itemList);
    }
    private Firebase_RealTime(){}

    public static void saveNewUser(){
        if (currentUser == null)return;
        usersReference.child(currentUser.getUid());
    }
    public static void saveNewItemFavorite(FoodInformation foodInformation){
        if (currentUser == null)return;
        usersReference.child(currentUser.getUid()).child("favorites").push().setValue(foodInformation);
    }

    public static void deleteItemFromFavorites() {

    }

    public static void  deleteFavorite(FoodInformation information) {
     usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             if (currentUser == null)return;
             DataSnapshot favorites = snapshot.child(currentUser.getUid()).child("favorites");
             for (DataSnapshot ds : favorites.getChildren()) {
                 FoodInformation info = ds.getValue(FoodInformation.class);
                 if(info == null) continue;
                 if(info.getFdcId().equals(information.getFdcId())){
                     ds.getRef().removeValue();
                 }
             }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
     });
    }


    public static void fetchFavorites(FirebaseUserInterface callback){
        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (currentUser == null)return;
                DataSnapshot favorites = snapshot.child(currentUser.getUid()).child("favorites");
                List<FoodInformation> infoList = new ArrayList<>();
                for (DataSnapshot ds : favorites.getChildren()) {
                    FoodInformation info = ds.getValue(FoodInformation.class);
                    infoList.add(info);
                }
                callback.fetched(infoList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static FirebaseUser getCurrentUser() {
        return currentUser;
    }
}
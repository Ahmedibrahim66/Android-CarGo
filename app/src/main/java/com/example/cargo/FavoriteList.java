package com.example.cargo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class FavoriteList extends Fragment {

    private ArrayList<String> Brand = new ArrayList<>();
    private ArrayList<String> Type = new ArrayList<>();
    private ArrayList<String> Price = new ArrayList<>();
    private ArrayList<String> cargoprice = new ArrayList<>();
    private ArrayList<String> Discount = new ArrayList<>();
    private ArrayList<String> mileage = new ArrayList<>();
    private ArrayList<String> model = new ArrayList<>();
    private ArrayList<String> city = new ArrayList<>();
    private ArrayList<String> dealer = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();

    private ArrayList<String> ID = new ArrayList<>();


    private static final String TAG = MainActivity.class.getSimpleName();


    private ArrayList<String> mBrand = new ArrayList<>();
    private ArrayList<String> mType = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();
    private ArrayList<String> mDiscount = new ArrayList<>();
    private ArrayList<String> mSpecs = new ArrayList<>();
    private ArrayList<String> mimages1 = new ArrayList<>();


    private RecyclerView recyclerView;



    private DrawerLayout drawer;
    ImageView iv ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v =  inflater.inflate(R.layout.fragment_favorite_list, container, false);


        ///data base data



        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final String userid = firebaseAuth.getUid();
        final DocumentReference CheckFavorite = db.collection("Users").document(userid);


        CheckFavorite.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                List<String> Favorite = (List<String>) documentSnapshot.get("Favorite");

                for(int i=0 ; i<Favorite.size() ; i++)
                {
                    db.collection("CarsForSale").document(Favorite.get(i)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot document) {

                            ID.add(document.getId());
                            Brand.add(document.get("Brad").toString());
                            Type.add(document.get("Model").toString());
                            cargoprice.add(document.get("Price").toString());
                            mileage.add(document.get("Kilometers").toString());
                            model.add(document.get("Year").toString());
                            city.add(document.get("City").toString());
                            dealer.add(document.get("Dealer").toString());
                            images.add(document.get("MainPicture").toString());



                            recycleviewcreate1(v);

                        }
                    });



                }
            }


        });


        ///databasedata


        return v;
    }


    private void recycleviewcreate1(View v){

        recyclerView = (RecyclerView) v.findViewById(R.id.rv_favoritelistcarrecycleview);
        latestofferbuycardRecycleViewAdapter adapter = new latestofferbuycardRecycleViewAdapter( ID,Brand ,Type,cargoprice ,mileage,model,city,dealer,images, getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(),  LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }


}

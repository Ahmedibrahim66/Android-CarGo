package com.example.cargo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LatestOffersBuy extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_offers_buy);

        iv = findViewById(R.id.iv_latestofferback);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });



        ///data base data

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("CarsForSale")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.get("Brad").toString());

                                ID.add(document.getId());
                                Brand.add(document.get("Brad").toString());
                                Type.add(document.get("Model").toString());
                                cargoprice.add(document.get("Price").toString());
                                mileage.add(document.get("Kilometers").toString());
                                model.add(document.get("Year").toString());
                                city.add(document.get("City").toString());
                                dealer.add(document.get("Dealer").toString());
                                images.add(document.get("MainPicture").toString());


                                recycleviewcreate1();

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        ///databasedata




        ////
        mBrand.add("BMW");
        mType.add("5-series");
        mPrice.add("285,000");
        mDiscount.add("5000");
        mSpecs.add("Ramallah - BMW 530i Full Options at Friends Motors");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69179554_2691288744249166_9139203214862712832_n.jpg?_nc_cat=102&_nc_oc=AQlS0rgC3_tdUhnObrkUzpFNRj-6Uhc3VQNGhVvyPMGDVACdZOz-4UZ6KOjZ4nc4V5U&_nc_ht=scontent.fjrs1-1.fna&oh=a8c8760bc8a9d4935ccb7b4fac9cfb76&oe=5E006D3B");


        mBrand.add("Jaguar");
        mType.add("F-pace");
        mPrice.add("250,000");
        mDiscount.add("7000");
        mSpecs.add("Ramallah - Jaguar F-pace Full Options at Friends Motors");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69891669_2699245560120151_1881406648736546816_n.jpg?_nc_cat=100&_nc_oc=AQnzO34KJOY9safJLX9RmpkW7UzqG6YMrI3yUdhNW2UO1nFo4hs0qk12m9HIX2npGI0&_nc_ht=scontent.fjrs1-1.fna&oh=ffe41503614c033af8f0fe009ffb4b19&oe=5DEEF32C");

        mBrand.add("Kia");
        mType.add("Sorento");
        mPrice.add("180,000");
        mDiscount.add("10000");
        mSpecs.add("Ramallah - Kia Sorento Full Options at Friends Motors");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/68915723_2686106861434021_2720587853296828416_n.jpg?_nc_cat=104&_nc_oc=AQlSXpIMltMOFXFaw8tLmQNt3SIRe2lbnJC_NrRXgFXrx_p-ZoKjjdkiBO3q_5n87Iw&_nc_ht=scontent.fjrs1-1.fna&oh=c757d5719673216361912f9970578e08&oe=5DF7F8AD");


        mBrand.add("Mercedes");
        mType.add("GLC");
        mPrice.add("360,000");
        mDiscount.add("20000");
        mSpecs.add("Mercedes Benz GLC 250 2020 at Mercedes Benz Palestine");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70592418_1124805387718729_1057330753961984000_o.jpg?_nc_cat=110&_nc_oc=AQl1NeKDezXl4fS1hl6yl7706S2MglD9SZRILI5EHFXpsc71NHvE7ztM3QTTnajd4i8&_nc_ht=scontent.fjrs1-1.fna&oh=664aa6ccbc49e069bc4d9177b6cbcf6e&oe=5DF94B90");


        mBrand.add("Mercedes");
        mType.add("Maybach");
        mPrice.add("2,700,000");
        mDiscount.add("100000");
        mSpecs.add("Mercedes Benz Maybach 2020 at Mercedes Benz Palestine");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69771026_1110327049166563_1226101652772093952_o.jpg?_nc_cat=102&_nc_oc=AQlJsSGBDciPp_E2NVOLzaqqWcf0Cr97QFbjDSmEg7ULDN2OM8xCZPVricuB_jkIk00&_nc_ht=scontent.fjrs1-1.fna&oh=2a64a9d6b454bec91a74f96b31f4a8fa&oe=5DF77493");


        mBrand.add("Mercedes");
        mType.add("GLC");
        mPrice.add("200,000");
        mDiscount.add("5000");
        mSpecs.add("Mercedes Benz GLC 250 2017 at Faqih Auto");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70744560_2475662346025471_4206787657453273088_n.jpg?_nc_cat=108&_nc_oc=AQmnUrx37n0ICEtTvYZp7JtPpY5OmJ1joj6PaPEamCU10Y8rYSIPfrrwbG8keGIAE60&_nc_ht=scontent.fjrs1-1.fna&oh=fa5ae1fdec8656a258cda5c698f3b4ab&oe=5E0E7D13");


        recycleviewcreate2();



    }


    private void recycleviewcreate1(){

        recyclerView = (RecyclerView) findViewById(R.id.rv_latestOffersbuy);
        latestofferbuycardRecycleViewAdapter adapter = new latestofferbuycardRecycleViewAdapter( ID,Brand ,Type,cargoprice ,mileage,model,city,dealer,images, getApplicationContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(),  LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }



    private void recycleviewcreate2(){

        recyclerView = (RecyclerView) findViewById(R.id.rv_specialoffersBuy1);
        SpecialOffersRecycleViewAdaperBuy adapter = new SpecialOffersRecycleViewAdaperBuy( mBrand ,mType,mPrice,mDiscount,mSpecs, mimages1 , getApplicationContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(),  LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }


}

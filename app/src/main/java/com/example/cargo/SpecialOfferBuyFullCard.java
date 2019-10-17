package com.example.cargo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class SpecialOfferBuyFullCard extends AppCompatActivity {


    private ArrayList<String> images = new ArrayList<>();
    String mainimage;
    ImageView ic_favorite,ic_favoritered;



    private RecyclerView recyclerView;

    TextView
            name , price , city , dealer, year , kilometer , fueltype,trasmission ,
            t_year , t_fuel , t_kilometer , t_engine, t_body , t_trasmission , t_color
            , t_payment , t_city , t_dealer, t_extrainfo, phone, city2,dealer2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offer_buy_full_card);


        name = findViewById(R.id.tv_name);
        price = findViewById(R.id.tv_carprice);
        city = findViewById(R.id.tv_city);
        dealer = findViewById(R.id.tv_dealer);
        year = findViewById(R.id.tv_year);
        kilometer = findViewById(R.id.tv_kilometers);
        fueltype = findViewById(R.id.tv_fueltype);
        trasmission = findViewById(R.id.tv_trasmission);
        t_year = findViewById(R.id.table_year);
        t_fuel = findViewById(R.id.table_fueltype);
        t_kilometer = findViewById(R.id.table_kilometers);
        t_engine = findViewById(R.id.table_engine);
        t_body = findViewById(R.id.table_type);
        t_trasmission = findViewById(R.id.table_transmission);
        t_color = findViewById(R.id.table_color);
        t_payment = findViewById(R.id.table_paymentmethod);
        t_city = findViewById(R.id.table_city);
        t_dealer = findViewById(R.id.table_dealer);
        t_extrainfo = findViewById(R.id.table_extrainformation);
        ic_favorite = findViewById(R.id.ic_favorite);
        ic_favoritered = findViewById(R.id.ic_favoritered);




        phone = findViewById(R.id.phonenumber);
        city2 = findViewById(R.id.city2);
        dealer2 = findViewById(R.id.dealer2);


        final TextView fullname = findViewById(R.id.fullname);



        final Bundle extras = getIntent().getExtras();

        final String id = extras.getString("Id");




        ///data base data

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final DocumentReference docRef = db.collection("CarsForSale").document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                fullname.setText(documentSnapshot.getString("Brad") + " " +documentSnapshot.getString("Model"));
                name.setText(documentSnapshot.getString("Brad") + " " +documentSnapshot.getString("Model"));
                price.setText(documentSnapshot.getString("Price") + "₪");
                city.setText(documentSnapshot.getString("City"));
                dealer.setText(documentSnapshot.getString("Dealer"));
                year.setText(documentSnapshot.getString("Year"));
                kilometer.setText(documentSnapshot.getString("Kilometers") + "Km");
                trasmission.setText(documentSnapshot.getString("Trasmission"));
                t_year.setText(documentSnapshot.getString("Year"));
                t_fuel.setText(documentSnapshot.getString("Fuel"));
                t_kilometer.setText(documentSnapshot.getString("Kilometers"));
                t_engine.setText(documentSnapshot.getString("engine"));
                t_body.setText(documentSnapshot.getString("Type"));
                t_trasmission.setText(documentSnapshot.getString("Trasmission"));
                t_color.setText(documentSnapshot.getString("Color"));
                t_city.setText(documentSnapshot.getString("City"));
                t_dealer.setText(documentSnapshot.getString("Dealer"));
                t_extrainfo.setText(documentSnapshot.getString("ExtraInformation"));
                phone.setText(documentSnapshot.getString("Phone"));
                city2.setText(documentSnapshot.getString("City"));
                dealer2.setText(documentSnapshot.getString("Dealer"));





                List<String> paymentmethod = (List<String>) documentSnapshot.get("PaymentMethod");
                t_payment.setText( paymentmethod.get(0) + "  " + paymentmethod.get(1));


                images.add(documentSnapshot.getString("MainPicture"));
                List<String> pictures = (List<String>) documentSnapshot.get("SecondaryPhotos");
                images.addAll(pictures);




                SliderView sliderView = findViewById(R.id.imageSlider);

                SliderAdapterCardBuyFull adapter = new SliderAdapterCardBuyFull(images , getApplicationContext());

                sliderView.setSliderAdapter(adapter);

                sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderView.setIndicatorSelectedColor(Color.WHITE);
                sliderView.setIndicatorUnselectedColor(Color.GRAY);
                sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
                sliderView.startAutoCycle();






                recycleviewcreate3();

            }
        });

        ///databasedata



        //update favorite list
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        



        if(firebaseAuth.getCurrentUser() != null )
        {


            final String userid = firebaseAuth.getUid();
            final DocumentReference FavoriteList = db.collection("Users").document(userid);

        ic_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FavoriteList.update("Favorite", FieldValue.arrayUnion(id));
                ic_favorite.setVisibility(View.GONE);
                ic_favoritered.setVisibility(View.VISIBLE);
                Toast.makeText(SpecialOfferBuyFullCard.this, "Added to Favorite", Toast.LENGTH_SHORT).show();

            }
        });


        ic_favoritered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FavoriteList.update("Favorite", FieldValue.arrayRemove(id));
                ic_favorite.setVisibility(View.VISIBLE);
                ic_favoritered.setVisibility(View.GONE);
                Toast.makeText(SpecialOfferBuyFullCard.this, "Removed from Favortie", Toast.LENGTH_SHORT).show();


            }
        });



        } else {

            ic_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(SpecialOfferBuyFullCard.this, "You need to login first", Toast.LENGTH_LONG).show();

                }
            });


        }
        //update favorite list


        //check favorite list

        if(firebaseAuth.getCurrentUser() != null )

        {
            final String userid = firebaseAuth.getUid();
            final DocumentReference CheckFavorite = db.collection("Users").document(userid);


        CheckFavorite.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {


                List<String> Favorite = (List<String>) documentSnapshot.get("Favorite");

                for(int i=0 ; i<Favorite.size() ; i++)
                {
                    if(Favorite.get(i).equals(id.toString()))
                    {
                        ic_favorite.setVisibility(View.GONE);
                        ic_favoritered.setVisibility(View.VISIBLE);

                    }

                    }
                }


            });

        }
        ///check favorite list





    }


    private void recycleviewcreate3(){
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        rvadapterimagesviewbuy adapter = new rvadapterimagesviewbuy(images, getApplicationContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(),  LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

}
